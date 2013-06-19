package com.hp.hpl.logkv.model;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.cassandra.thrift.Cassandra;
import org.apache.cassandra.thrift.Column;
import org.apache.cassandra.thrift.ColumnParent;
import org.apache.cassandra.thrift.ConsistencyLevel;
import org.apache.cassandra.thrift.InvalidRequestException;
import org.apache.cassandra.thrift.TBinaryProtocol;
import org.apache.cassandra.thrift.TimedOutException;
import org.apache.cassandra.thrift.UnavailableException;
import org.apache.thrift.TException;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransportException;

import com.hp.hpl.logkv.conf.Parameter;
import com.hp.hpl.logkv.ingestkv.ShuffleReceiver;
import com.hp.hpl.logkv.util.Util;

public class KVTRU {

	private long truId = -1;
	private KVColumnValue[] columns = null;
	private Schema schema = null;

	public KVTRU(long truId, Schema schema) {
		this.truId = truId;
		this.schema = schema;

		int size = schema.getNumOfField();
		columns = new KVColumnValue[size];
		for (int i = 0; i < size; i++) {
			columns[i] = new KVColumnValue(i, schema.getField(i).getFieldType());
		}
	}

	public void setTruId(long truId) {
		this.truId = truId;
	}

	public long getTruId() {
		return this.truId;
	}

	public void setColumnValue(int columnId, KVColumnValue value) {
		columns[columnId] = value;
	}

	public void addEvent(Event event, int localRowId) {
		Map<Integer, Object> map = event.getContent();
		Iterator<Entry<Integer, Object>> it = map.entrySet().iterator();
		while (it.hasNext()) {
			Entry<Integer, Object> entry = it.next();
			columns[entry.getKey().intValue()].setValue(localRowId, entry.getValue());
		}
	}

	public void addEvents(GlobalTRU globalTru) {
		List<Event> events = globalTru.getEvents();
		for (int i = 0; i < events.size(); i++) {
			Event ep = events.get(i);
			this.addEvent(ep, i);
		}
	}

	public KVColumnValue getColumnValue(int columnId) {
		return columns[columnId];
	}

	public void write2KVStore() {
		for (int column = 0; column < schema.getNumOfField(); column++) {
			KVColumnValue value = this.getColumnValue(column);
			byte[] bbBitMap = value.getCompressedBitMap();
			byte[] bbCompressedNotNullValues = value.compressNotNullValues();

			if (Parameter.IF_INSERT_TO_KV_STORE) {
				writeColumn2KVStore(truId, bbCompressedNotNullValues, bbBitMap, column);
				Util.log("TRU " + truId + "-" + column + " is inserted to KV-Store", this.getClass());
			}
		}
	}

	public void writeColumn2KVStore(long key, byte[] columnValue, byte[] indexValue, int columnId) {
		String host = ShuffleReceiver.ipMyself;
		int port = Parameter.KV_STORE_PORT;
		String keyspaceName = Parameter.KV_STORE_SPACENAME;

		TFramedTransport tr = new TFramedTransport(new TSocket(host, port));
		TBinaryProtocol pr = new TBinaryProtocol(tr);
		Cassandra.Client cli = new Cassandra.Client(pr);
		ColumnParent cf = null;
		try {
			tr.open();
			cli.set_keyspace(keyspaceName);
			String cfName = Parameter.KV_STORE_COLUMN_FAMILY_PREFIX + columnId;
			cf = new ColumnParent(cfName);

			/*
			 * Insert the compressed not null values into KV-store
			 */
			Column valueColumn = new Column(ByteBuffer.wrap(Parameter.KV_STORE_VALUE_COLUMN_NAME.getBytes()));
			valueColumn.setValue(columnValue);
			valueColumn.setTimestamp(System.currentTimeMillis());
			cli.insert(ByteBuffer.wrap(Long.toString(key).getBytes("UTF8")), cf, valueColumn, ConsistencyLevel.ALL);

			/*
			 * Insert the BitMap index into KV-store
			 */
			Column indexColumn = new Column(ByteBuffer.wrap(Parameter.KV_STORE_INDEX_COLUMN_NAME.getBytes()));
			indexColumn.setValue(indexValue);
			indexColumn.setTimestamp(System.currentTimeMillis());
			cli.insert(ByteBuffer.wrap(Long.toString(key).getBytes("UTF8")), cf, indexColumn, ConsistencyLevel.ALL);

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

	public String toString() {
		String result = "";
		result += "TRU-" + truId + ": \n";
		for (int i = 0; i < columns.length; i++) {
			KVColumnValue cv = columns[i];
			result += i + " : " + cv + " ";
			result += "\n";
		}
		return result;
	}

}
