package com.hp.hpl.logkv.test.model;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.hp.hpl.logkv.model.Event;
import com.hp.hpl.logkv.model.Field;
import com.hp.hpl.logkv.model.Schema;
import com.hp.hpl.logkv.model.FieldType;

public class EventSerializationTest {

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
		
		Event event1 = new Event(schema);
		event1.setEventId(12345678L);
		event1.setTimestamp(123456789L);
		event1.addValue(0, 1);
		event1.addValue(2, "String2");
		event1.addValue(3, 987654321L);
		
		System.out.println(event1);
		
		try {
			FileOutputStream fos = new FileOutputStream("output/EventSerializationTest");
			DataOutput out = new DataOutputStream(fos);
			event1.write(out);
			fos.flush();
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			FileInputStream fis = new FileInputStream("output/EventSerializationTest");
			DataInput in = new DataInputStream(fis);
			Event event2 = new Event(schema);
			event2.read(in);
			fis.close();
			System.out.println(event2);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
