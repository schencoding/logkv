package com.hp.hpl.logkv.model;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.hp.hpl.logkv.conf.Parameter;

public class GlobalTRU {
	private long truId;
	private Schema schema;
	private ArrayList<Event> events = new ArrayList<Event>();
	public int currentIndex = -1;

	public GlobalTRU(Schema schema, Long truId) {
		this.truId = truId;
		this.schema = schema;
	}

	public void addEvent(Event event) {
		if(currentIndex == -1){
			events.add(0, event);
			currentIndex = 0;
			return;
		}
		Event currentEvent = events.get(currentIndex);

		if (event.getTimestamp() < currentEvent.getTimestamp()) {
			currentIndex = 0;
		}
		boolean bStop = false;
		while (!bStop) {
			if (event.getTimestamp() > currentEvent.getTimestamp()) {
				currentIndex++;
			} else {
				bStop = true;
			}
			if(currentIndex < events.size()){
				currentEvent = events.get(currentIndex);
			}else{
				break;
			}
		}
		events.add(currentIndex, event);
		
	}
	
	public long getTruId(){
		return this.truId;
	}
	
	public void writeOut(){
		try {
			FileOutputStream fos = new FileOutputStream(Parameter.GLOBAL_TRU_DIR + this.truId);
			DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(fos));
			dos.writeLong(this.truId);
			Iterator<Event> it = events.iterator();
			while(it.hasNext()){
				Event event = it.next();
				event.write(dos);
			}
			dos.flush();
			fos.flush();
			dos.close();
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public List<Event> getEvents() {
		return this.events;
	}

}
