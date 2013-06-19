package com.hp.hpl.logkv.test.model;

import com.hp.hpl.logkv.eventsource.EventGenerator;
import com.hp.hpl.logkv.model.Event;
import com.hp.hpl.logkv.model.Field;
import com.hp.hpl.logkv.model.FieldType;
import com.hp.hpl.logkv.model.LocalTRU;
import com.hp.hpl.logkv.model.Schema;

public class LocalTRUReadWriteTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Schema schema = new Schema("Synthetic event");
		schema.addField(0, new Field("Field1", FieldType.INT));
		schema.addField(1, new Field("Field2", FieldType.INT));
		schema.addField(2, new Field("Field3", FieldType.STRING));
		schema.addField(3, new Field("Field4", FieldType.LONG));
		schema.addField(4, new Field("Field5", FieldType.STRING));
		
		LocalTRU tru = new LocalTRU(0L, schema);
		
		EventGenerator eventGen = new EventGenerator(schema);
		for(long i = 0; i < 5L; i++){
			Event event = eventGen.generateEvent(i);
			System.out.println(event);
			tru.writeEvent(event.serialize());
		}
		System.out.println("----------- read events out---------");
		int numOfEvent = tru.getNumOfEvent();
		for(int i = 0; i < numOfEvent; i++){
			Event event = tru.readNextEvent();
			System.out.println(event);
		}
	}

}
