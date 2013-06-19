package com.hp.hpl.logkv.queryprocess;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import com.hp.hpl.logkv.conf.Parameter;
import com.hp.hpl.logkv.ingestkv.IngestReceiver;
import com.hp.hpl.logkv.model.Event;
import com.hp.hpl.logkv.model.LocalTRU;
import com.hp.hpl.logkv.model.Schema;
import com.hp.hpl.logkv.query.FieldFilter;
import com.hp.hpl.logkv.query.Query;
import com.hp.hpl.logkv.queryprocess.ResultSet.Record;
import com.hp.hpl.logkv.util.CompareHelper;
import com.hp.hpl.logkv.util.Util;
import com.hp.hpl.logkv.util.WorkQueue;

public class IngestKVQueryProcessor extends Thread{
	
	
	private WorkQueue qpThreads = new WorkQueue(Parameter.NUMBER_OF_QP_THREADS);
	private IngestReceiver ingestReceiver = null;
	private ServerSocket socketServer = null;	
	
	
	public IngestKVQueryProcessor(IngestReceiver ingestReceiver){
		this.ingestReceiver = ingestReceiver;
	}

	@Override
	public void run() {
		try {
			socketServer = new ServerSocket(Parameter.INGEST_KV_QP_PORT);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}  
		while (!interrupted()) {
			try {
				Socket socket = socketServer.accept();
				
				ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
				Query query = (Query) ois.readObject();
				ObjectOutputStream dos = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
				
				IngestKVQueryProcessTask task = new IngestKVQueryProcessTask(query);
				task.setSocket(socket);
				task.setInputStream(ois);
				task.setOutputStream(dos);
				qpThreads.execute(task);
				
			}catch(Exception e){
				e.printStackTrace();
			}	
			
		}
	}
	
	
	
	public class IngestKVQueryProcessTask implements Runnable{
		
		private Query query;
		private Socket socket;
		private InputStream is;
		private OutputStream os;
		
		@Override
		public void run() {
			Util.log("Start query processing.", this.getClass());
			
			ResultSet resultSet = processQuery();
			
			Util.log("Finished fetching data.", this.getClass());
			
			ObjectOutputStream oos = (ObjectOutputStream) os;
			try {
				oos.writeObject(resultSet);
				oos.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
			this.close();
			Util.log("Finished query processing.", this.getClass());
		}
		
		private ResultSet processQuery() {
			List<FieldFilter> ffs = query.getFilters();
			Schema schema = ingestReceiver.schema;
			List<Integer> columnIds2Remove = new ArrayList<Integer>(schema.getNumOfField());
			for(int i = 0; i < schema.getNumOfField(); i++){
				columnIds2Remove.add(i);
			}
			int[] returnColumnIds = query.getColumnIds();
			for(int i = 0; i< returnColumnIds.length; i++){
				columnIds2Remove.remove(new Integer(returnColumnIds[i]));
			}
			if(columnIds2Remove.contains(query.joinLeftAttribute)){
				columnIds2Remove.remove(new Integer(query.joinLeftAttribute));
			}
			if(columnIds2Remove.contains(query.joinRightAttribute)){
				columnIds2Remove.remove(new Integer(query.joinRightAttribute));
			}
			int numOfColumns2Remove = columnIds2Remove.size();
			
			ResultSet resultSet = new ResultSet();
			long truId = query.subQueryTruId;
			LocalTRU localTru = ingestReceiver.getLocalTRU(truId);
			if (localTru != null) {
				int numOfEvent = localTru.getNumOfEvent();
				for (int i = 0; i < numOfEvent; i++) {
					Event event = localTru.readNextEvent();
					Record record = event.toRecord(i);
					if (checkFilters(ffs, record)) {
						for (int j = 0; j < numOfColumns2Remove; j++) {
							int fieldId = columnIds2Remove.get(j);
							record.content.remove(fieldId);
						}
						resultSet.addRecord(record);
					}
				}
				localTru.reset();
			}
			return resultSet;
		}
		
		public boolean checkFilters(List<FieldFilter> ffs, Record record){
			int numOfFilters = ffs.size();
			for(int i = 0; i < numOfFilters; i++){
				FieldFilter ff = ffs.get(i);
				int fieldId = ff.getFieldId();
				Object filterValue = ff.getValue();
				Object objectValue = record.getFieldValue(fieldId);
				if(CompareHelper.compare(ingestReceiver.schema.getField(fieldId).getFieldType(), filterValue, objectValue) != 0){
					return false;
				}
			}
			return true;
		}

		public IngestKVQueryProcessTask(Query query){
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
		
		public void close(){
			if(is != null){
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(os != null){
				try {
					os.flush();
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			if(socket != null){
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}
		
	}
	
	
	

}
