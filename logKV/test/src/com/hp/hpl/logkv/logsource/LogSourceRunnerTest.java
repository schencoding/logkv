package com.hp.hpl.logkv.logsource;

import com.hp.hpl.logkv.model.Field;
import com.hp.hpl.logkv.model.FieldType;
import com.hp.hpl.logkv.model.Schema;

public class LogSourceRunnerTest {

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

		LogSourceRunner logSourceRunner = new LogSourceRunner(schema);
		logSourceRunner.start();
		System.out.println("LogSourceRunner started.");
	}

}
