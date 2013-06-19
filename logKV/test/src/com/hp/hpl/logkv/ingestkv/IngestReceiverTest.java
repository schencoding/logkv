package com.hp.hpl.logkv.ingestkv;


import com.hp.hpl.logkv.conf.Parameter;
import com.hp.hpl.logkv.eventsource.EventGenerator;
import com.hp.hpl.logkv.ingestkv.IngestKVReplicaReceiver;
import com.hp.hpl.logkv.ingestkv.IngestReceiver;
import com.hp.hpl.logkv.ingestkv.ShuffleReceiver;
import com.hp.hpl.logkv.model.Event;
import com.hp.hpl.logkv.model.Field;
import com.hp.hpl.logkv.model.FieldType;
import com.hp.hpl.logkv.model.Schema;
import com.hp.hpl.logkv.util.Util;

public class IngestReceiverTest {

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

		ShuffleReceiver shuffleReceiver = new ShuffleReceiver(schema);
		shuffleReceiver.start();
		Util.log("ShuffleReceiver started.", IngestReceiverTest.class);

		IngestKVReplicaReceiver duplicateReceiver = new IngestKVReplicaReceiver();
		duplicateReceiver.start();
		Util.log("DuplicateReceiver started.", IngestReceiverTest.class);

		IngestReceiver ingestReceiver = new IngestReceiver(schema);
		ingestReceiver.start();

		EventGenerator eventGen = new EventGenerator(schema);
		Util.log("[" + System.currentTimeMillis() + "]", IngestReceiverTest.class);
		long totalEvents = 30 * Parameter.TRU_SIZE;
		for (long l = 0; l < totalEvents; l++) {
			if (l % Parameter.TRU_SIZE == 0) {
				Util.log(l + " events ingested.", IngestReceiverTest.class);
			}
			Event event = eventGen.generateEvent(l);
			ingestReceiver.addEvent(event);
		}
		Util.log("Ingested " + totalEvents + " events.", IngestReceiverTest.class);
	}

}
