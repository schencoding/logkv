package com.hp.hpl.logkv.queryprocess;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import org.apache.cassandra.thrift.Cassandra;
import org.apache.cassandra.thrift.Column;
import org.apache.cassandra.thrift.ColumnOrSuperColumn;
import org.apache.cassandra.thrift.ColumnParent;
import org.apache.cassandra.thrift.ConsistencyLevel;
import org.apache.cassandra.thrift.InvalidRequestException;
import org.apache.cassandra.thrift.SlicePredicate;
import org.apache.cassandra.thrift.SliceRange;
import org.apache.cassandra.thrift.TimedOutException;
import org.apache.cassandra.thrift.UnavailableException;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransportException;

import com.hp.hpl.logkv.conf.Parameter;
import com.hp.hpl.logkv.ingestkv.IngestReceiver;
import com.hp.hpl.logkv.model.KVColumnValue;
import com.hp.hpl.logkv.model.Schema;
import com.hp.hpl.logkv.query.FieldFilter;
import com.hp.hpl.logkv.query.Query;
import com.hp.hpl.logkv.queryprocess.ResultSet.Record;
import com.hp.hpl.logkv.util.CompareHelper;
import com.hp.hpl.logkv.util.Util;
import com.hp.hpl.logkv.util.WorkQueue;

public class TimeRangeKVQueryProcessor extends Thread {

	private WorkQueue qpThreads = new WorkQueue(Parameter.NUMBER_OF_QP_THREADS);
	private ServerSocket socketServer = null;
	private Schema schema = null;

	/**
	 * Variables for Cassandra connection
	 */
	TFramedTransport tr = null;
	TBinaryProtocol pr = null;
	Cassandra.Client cli = null;
	ColumnParent cf = null;
	SlicePredicate sp = null;
	SliceRange sr = null;

	public TimeRangeKVQueryProcessor(Schema schema) {
		this.schema = schema;
	}

	@Override
	public void run() {
		/***
		 * Step 1: create a ServerSocket to listen the query processing request
		 */
		try {
			socketServer = new ServerSocket(Parameter.TIMERANGE_KV_QP_PORT);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		while (!interrupted()) {
			try {
				/**
				 * Step 2: wait for connection
				 */
				Socket socket = socketServer.accept();

				/**
				 * Step 3: Once a query processing request is coming, create a
				 * TimeRangeKVQPTask instance
				 */
				ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
				Query query = (Query) ois.readObject();
				ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));

				TimeRangeKVQueryProcessTask task = new TimeRangeKVQueryProcessTask(query);
				task.setSocket(socket);
				task.setInputStream(ois);
				task.setOutputStream(oos);

				/**
				 * Step 4: add the TimeRangeKVQPTask instance to the task queue
				 */
				qpThreads.execute(task);

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	public class TimeRangeKVQueryProcessTask implements Runnable {

		private Query query;
		private Socket socket;
		private InputStream is;
		private OutputStream os;

		@Override
		public void run() {
			ResultSet resultSet = processQuery();

			ObjectOutputStream oos = (ObjectOutputStream) os;
			try {
				oos.writeObject(resultSet);
				oos.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}

			this.close();
		}

		private ResultSet processQuery() {
			String host = Util.getMyIpStr();

			int[] columnIds = query.getColumnIdsToRetrieve();

			ResultSet intermediateResultSet = new ResultSet();
			ResultSet finalResultSet = new ResultSet();

			/***
			 * Step 1: connect to key-value store server
			 */
			int port = Parameter.KV_STORE_PORT;
			String keyspaceName = Parameter.KV_STORE_SPACENAME;
			tr = new TFramedTransport(new TSocket(host, port));
			pr = new TBinaryProtocol(tr);
			cli = new Cassandra.Client(pr);
			cf = null;
			sp = null;
			sr = null;
			try {
				tr.open();

				sp = new SlicePredicate();
				sr = new SliceRange();
				sr.setStart(new byte[0]);
				sr.setFinish(new byte[0]);
				sr.setReversed(false);
				sr.setCount(1000000);
				sp.setSlice_range(sr);

				cli.set_keyspace(keyspaceName);
			} catch (TTransportException e1) {
				e1.printStackTrace();
			} catch (InvalidRequestException e) {
				e.printStackTrace();
			} catch (TException e) {
				e.printStackTrace();
			}

			/**
			 * Step 2: for each column
			 */
			if (!query.bSelfJoin) {
				retrieveData(query.subQueryTruId, columnIds, finalResultSet);
			} else {
				/**
				 * 
				 */
				long offset = query.within / Parameter.TRU_SIZE;
				long b = query.within % Parameter.TRU_SIZE;
				if (b != 0) {
					offset++;
				}
				offset -= 1;

				/**
				 * Retrieve all the required TRUs
				 */
				for (long i = (query.subQueryTruId - offset); i <= (query.subQueryTruId + offset); i++) {
					retrieveData(i, columnIds, intermediateResultSet);
				}
				/**
				 * Join
				 */
				Util.log("Starting build index. ", this.getClass());
				int rightJoinFieldId = query.joinRightAttribute;
				HashMap<Object, List<ResultSet.Record>> index = new HashMap<Object, List<ResultSet.Record>>();
				Iterator<Entry<Long, ResultSet.Record>> it = intermediateResultSet.getRecords().entrySet().iterator();
				while (it.hasNext()) {
					Entry<Long, ResultSet.Record> entry = it.next();
					ResultSet.Record record = entry.getValue();
					Object joinKey = record.getFieldValue(rightJoinFieldId);
					if (joinKey != null) {
						List<ResultSet.Record> list = index.get(joinKey);
						if (list == null) {
							list = new ArrayList<ResultSet.Record>();
							index.put(joinKey, list);
						}
						list.add(record);
					}
				}

				Util.log("Starting join. ", this.getClass());
				
				System.out.println("Join attribute: " + query.joinLeftAttribute + "   " + query.joinRightAttribute);
				int leftJoinFieldId = query.joinLeftAttribute;
				it = intermediateResultSet.getRecords().entrySet().iterator();
				long recordId = 0;
				while (it.hasNext()) {
					Entry<Long, ResultSet.Record> entry = it.next();
					ResultSet.Record leftRecord = entry.getValue();
					Object joinKey = leftRecord.getFieldValue(leftJoinFieldId);
					if (joinKey != null) {
						List<ResultSet.Record> list = index.get(joinKey);
						if (list != null) {
							Iterator<ResultSet.Record> itJ = list.iterator();
							while (itJ.hasNext()) {
								ResultSet.Record rightRecord = itJ.next();
								System.out.println("Left: " + leftRecord);
								System.out.println("Right: " + rightRecord);
								// sb.append(r.recordId + "  ");

								ResultSet.Record newRecord = new ResultSet.Record(recordId++);

								List<Integer> leftColumnIds = query.leftColumnIds;
								int leftSize = leftColumnIds.size();
								for (int i = 0; i < leftSize; i++) {
									int columnId = leftColumnIds.get(i);
									newRecord.setFieldValue(i, leftRecord.getFieldValue(columnId));
								}

								List<Integer> rightColumnIds = query.rightColumnIds;
								System.out.println("rightColumnIds: " + Arrays.toString(rightColumnIds.toArray(new Integer[]{})));
								for (int j = 0; j < rightColumnIds.size(); j++) {
									int columnId = rightColumnIds.get(j);
									Object obj = rightRecord.getFieldValue(columnId);
									System.out.println("J  " + j + ":" + obj);									newRecord.setFieldValue(leftSize + j, obj);
								}
								System.out.println("newRecord: " + newRecord);
								finalResultSet.addRecord(newRecord);
							}

						}
					}
				}
				Util.log("Finished join. ", this.getClass());
			}
			// System.out.println(finalResultSet);
			return finalResultSet;
		}

		private void retrieveData(long truId, int[] columnIds, ResultSet resultSet) {
			try {
				for (int index = 0; index < columnIds.length; index++) {
					int columnId = columnIds[index];

					String cfName = Parameter.KV_STORE_COLUMN_FAMILY_PREFIX + columnId;
					cf = new ColumnParent(cfName);

					/***
					 * execute the query on one column
					 */
					List<ColumnOrSuperColumn> coscs = cli.get_slice(ByteBuffer.wrap((Long.toString(truId).getBytes("UTF-8"))), cf, sp, ConsistencyLevel.ONE);

					if (coscs.size() == 2) {
						KVColumnValue kvCV = new KVColumnValue(columnId, schema.getField(columnId).getFieldType());
						/**
						 * get non-null value bytes and rebuild non-null values
						 */
						ColumnOrSuperColumn valueCoSC = coscs.get(1);
						Column valueColumn = valueCoSC.getColumn();
						byte[] valueBytes = valueColumn.getValue();
						kvCV.readNotNullValues(valueBytes);
						Util.log("Size of value bytes received : " + valueBytes.length + " for TRU " + truId + " Column " + index, this.getClass());

						/**
						 * get index bytes and rebuild index
						 */
						ColumnOrSuperColumn indexCoSC = coscs.get(0);
						Column indexColumn = indexCoSC.getColumn();
						byte[] indexBytes = indexColumn.getValue();
						kvCV.readBitMap(indexBytes);
						Util.log("Size of index bytes received : " + indexBytes.length + " for TRU " + truId + " Column " + index, this.getClass());

						/**
						 * filter records and compose record
						 */
						boolean bNotFirst = false;
						if (index > 0) {
							bNotFirst = true;
						}
						kvCV.buildRecords(query, resultSet, bNotFirst);
					}
				}
				resultSet = filter(resultSet);

			} catch (TTransportException e1) {
				e1.printStackTrace();
			} catch (InvalidRequestException e) {
				e.printStackTrace();
			} catch (TException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (UnavailableException e) {
				e.printStackTrace();
			} catch (TimedOutException e) {
				e.printStackTrace();
			}
		}

		private ResultSet filter(ResultSet resultSet) {
			ResultSet newResultSet = new ResultSet();
			List<FieldFilter> ffs = query.getFilters();
			List<Integer> field2Remove = query.filterColumnIds;
			if(field2Remove.contains(query.joinLeftAttribute)){
				field2Remove.remove(new Integer(query.joinLeftAttribute));
			}
			if(field2Remove.contains(query.joinRightAttribute)){
				field2Remove.remove(new Integer(query.joinRightAttribute));
			}
			int numOfField2Remove = field2Remove.size();

			Iterator<Entry<Long, Record>> it = resultSet.getRecords().entrySet().iterator();
			while (it.hasNext()) {
				Entry<Long, Record> entry = it.next();
				Record record = entry.getValue();
				if (checkFilters(ffs, record)) {
					for (int i = 0; i < numOfField2Remove; i++) {
						int fieldId = field2Remove.get(i);
						record.content.remove(fieldId);
					}
					// if(record.content.size()>0){
					newResultSet.addRecord(record);
					// }
				}
			}
			resultSet.getRecords().clear();
			resultSet.getRecords().putAll(newResultSet.getRecords());
			return resultSet;

		}

		public boolean checkFilters(List<FieldFilter> ffs, Record record) {
			int numOfFilters = ffs.size();
			for (int i = 0; i < numOfFilters; i++) {
				FieldFilter ff = ffs.get(i);
				int fieldId = ff.getFieldId();
				Object filterValue = ff.getValue();
				Object objectValue = record.getFieldValue(fieldId);
				if (CompareHelper.compare(schema.getField(fieldId).getFieldType(), filterValue, objectValue) != 0) {
					return false;
				}
			}
			return true;
		}

		public TimeRangeKVQueryProcessTask(Query query) {
			this.query = query;
		}

		public void setOutputStream(OutputStream os) {
			this.os = os;
		}

		public void setInputStream(InputStream is) {
			this.is = is;
		}

		public void setSocket(Socket socket) {
			this.socket = socket;
		}

		public void close() {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (os != null) {
				try {
					os.flush();
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}

	}

}
