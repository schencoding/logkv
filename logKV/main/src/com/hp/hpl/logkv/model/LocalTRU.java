package com.hp.hpl.logkv.model;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.hp.hpl.logkv.conf.Parameter;

public class LocalTRU {
	private long truId;
	private Schema schema;
	private int numOfEvent = 0;
	private FileOutputStream fos = null;
	private FileInputStream fis = null;
	private DataInputStream dis = null;

	public LocalTRU(long truId, Schema schema) {
		this.truId = truId;
		this.schema = schema;
	}

	public void writeEvent(byte[] bytes) {
		try {
			if (fos == null) {
				fos = new FileOutputStream(Parameter.LOCAL_TRU_DIR + truId);
			}
			numOfEvent++;
			fos.write(bytes);
			fos.flush();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public Event readNextEvent() {
		Event event = new Event(schema);
		try {
			if (fis == null) {
				fis = new FileInputStream(Parameter.LOCAL_TRU_DIR + truId);
				dis = new DataInputStream(fis);				
			}
			event.read(dis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return event;
	}
	
	public void reset(){
		if (fis != null) {
			try {
				fis.close();
				fis = new FileInputStream(Parameter.LOCAL_TRU_DIR + truId);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(dis != null){
			try {
				dis.close();
				dis = new DataInputStream(fis);				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public long getTruId() {
		return truId;
	}

	public void setTruId(long truId) {
		this.truId = truId;
	}

	public int getNumOfEvent() {
		return numOfEvent;
	}
	
	public String toString(){
		StringBuffer sb = new StringBuffer();
		sb.append("[" + "truId=" + truId + " # of events=" + numOfEvent + "\n");
		for(int i=0; i<numOfEvent; i++){
			Event event = this.readNextEvent();
			sb.append("\t" + event.toString() + "\n");
		}
		sb.append("]");
		return sb.toString();
	}

	public String getFileName() {
		return Parameter.LOCAL_TRU_DIR + truId;
	}

	public void close() {
		if (fos != null) {
			
			try {
				fos.flush();
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

}
