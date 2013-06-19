package com.hp.hpl.logkv.model;

import java.io.ByteArrayOutputStream;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.hp.hpl.logkv.queryprocess.ResultSet.Record;
import com.hp.hpl.logkv.util.SerializeHelper;

public class Event {
	private Schema schema;
	private long eventId;
	private long timestamp;
	private Map<Integer, Object> content = new HashMap<Integer, Object>();
	
	public Event(Schema schema){
		this.schema = schema;
	}
	
	public Event(Schema schema, long eventId, long timestamp){
		this.schema = schema;
		this.eventId = eventId;
		this.timestamp = timestamp;
	}	
	
	public Schema getSchema() {
		return schema;
	}

	public void setSchema(Schema schema) {
		this.schema = schema;
	}

	public long getEventId() {
		return eventId;
	}

	public void setEventId(long eventId) {
		this.eventId = eventId;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public void addValue(int i, Object value){
		content.put(i, value);
	}
	
	public Object getValue(int i){
		return content.get(i);
	}
	
	public Map<Integer, Object> getContent(){
		return content;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Event [eventId=" + eventId + ", timestamp=" + timestamp);
		Set<Entry<Integer, Object>> entries = content.entrySet();
		Iterator<Entry<Integer, Object>> it = entries.iterator();
		while(it.hasNext()){
			Entry<Integer, Object> entry = it.next();
			sb.append(", F" + entry.getKey() + "=" + entry.getValue());
		}
		sb.append("]");
		return sb.toString();
	}
	
	public void read(DataInput in) throws IOException {
		/*
		 * Before deserialize the event, clear the content
		 */
		content.clear();
		/******** begin deserialize ****************/
		this.eventId = in.readLong();
		this.timestamp = in.readLong();
		int size = in.readInt();
		for(int i=0; i<size; i++){
			int field = in.readInt();
			FieldType fieldType = schema.getField(field).getFieldType();
			Object value = SerializeHelper.read(in, fieldType);
			content.put(field, value);
		}
	}
	
	public void write(DataOutput out) throws IOException {
		out.writeLong(eventId);
		out.writeLong(timestamp);
		out.writeInt(content.size());
		
		Set<Entry<Integer, Object>> entries = content.entrySet();
		Iterator<Entry<Integer, Object>> it = entries.iterator();
		while(it.hasNext()){
			Entry<Integer, Object> entry = it.next();
			int field = entry.getKey();
			out.writeInt(field);
			FieldType fieldType = schema.getField(field).getFieldType();
			SerializeHelper.write(out, entry.getValue(), fieldType);
		}
	}
	
	public byte[] serialize(){
		byte[] bytes = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutput out = new DataOutputStream(baos);
		try {
			this.write(out);
			baos.flush();
			bytes = baos.toByteArray();
			baos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return bytes;
	}

	public Record toRecord(int localId) {
		Record record = new Record(localId);
		record.content = this.getContent();
		return record;
	}
}
