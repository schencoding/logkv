package com.hp.hpl.logkv.eventsource;

import java.util.Random;

import com.hp.hpl.logkv.conf.Parameter;
import com.hp.hpl.logkv.model.Event;
import com.hp.hpl.logkv.model.FieldType;
import com.hp.hpl.logkv.model.Schema;

public class EventGenerator {
	private Schema schema;
	private Random random = new Random();
	

	public EventGenerator(Schema schema){
		this.schema = schema;
	}
	
	public Event generateEvent(long eventId){
		Event event = new Event(schema);
		event.setEventId(eventId);
		event.setTimestamp(System.currentTimeMillis());
		
		int numColumn = schema.getNumOfField();
		for(int i=0; i<numColumn; i++){
			double p = random.nextDouble();
			if(p > Parameter.FIELD_FILL_RATIO){
				continue;
			}
			FieldType fieldType = schema.getField(i).getFieldType();
			Object value = EventGeneratorHelper.generate(random, fieldType);
			event.addValue(i, value);
		}		
		return event;
	}
}
