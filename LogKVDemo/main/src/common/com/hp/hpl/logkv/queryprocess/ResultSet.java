package com.hp.hpl.logkv.queryprocess;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class ResultSet implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6651684861422528810L;
	private Map<Long, Record> records = new TreeMap<Long, Record>();
	
	public ResultSet(){
		
	}
	
	public void addRecord(Record record){
		this.records.put(record.recordId, record);
	}
	
	public Record getRecord(long index){
		return records.get(index);
	}
	
	public int size(){
		return records.size();
	}
	
	public Map<Long, Record> getRecords(){
		return records;
	}
	
	
	public String toString(){
		StringBuffer sb = new StringBuffer();
		
		sb.append(records.size() + "records {");
		Iterator<Entry<Long,Record>> it = records.entrySet().iterator();
		while(it.hasNext()){
			Record record = it.next().getValue();
			sb.append("\t");
			sb.append(record);
			sb.append("\n");
		}
		sb.append("}");
		
		return sb.toString();
	}
	
	
	public static class Record implements Serializable{		
		/**
		 * 
		 */
		private static final long serialVersionUID = -6953132545870245550L;
		public long recordId = -1L;
		public Map<Integer, Object> content = new HashMap<Integer, Object>();
		
		public Record(){
			
		}
		
		public Record(long recordId){
			this.recordId = recordId;
		}
		
		public void setFieldValue(int fieldId, Object value){
			this.content.put(fieldId, value);
		}
		
		public Object getFieldValue(int fieldId){
			return this.content.get(fieldId);
		}

		@Override
		public String toString() {
			StringBuffer sb = new StringBuffer();
			
			sb.append(recordId + " : ");
			Iterator<Entry<Integer, Object>> it = content.entrySet().iterator();
			while(it.hasNext()){
				Entry<Integer, Object> entry = it.next();
				sb.append(entry.getKey() + "=" + entry.getValue() + "   ");
			}
			
			return sb.toString();
		}
		
		
	}
	

}
