package com.hp.hpl.logkv.qp;


import com.hp.hpl.logkv.conf.Parameter;
import com.hp.hpl.logkv.eventsource.EventGenerator;
import com.hp.hpl.logkv.ingestkv.IngestKVReplicaReceiver;
import com.hp.hpl.logkv.ingestkv.IngestReceiver;
import com.hp.hpl.logkv.ingestkv.ShuffleReceiver;
import com.hp.hpl.logkv.logsource.LogSourceRunner;
import com.hp.hpl.logkv.model.Event;
import com.hp.hpl.logkv.model.Field;
import com.hp.hpl.logkv.model.FieldType;
import com.hp.hpl.logkv.model.Schema;
import com.hp.hpl.logkv.queryprocess.IngestKVQueryProcessor;
import com.hp.hpl.logkv.queryprocess.TimeRangeKVQueryProcessor;
import com.hp.hpl.logkv.ui.UIRequestProcessor;
import com.hp.hpl.logkv.util.Util;

public class StartServers4QueryProcessTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/**
		 * Build the Schema for event
		 */
		Schema schema = new Schema("Synthetic event");
		schema.addField(0, new Field("Field1", FieldType.INT));
		schema.addField(1, new Field("Field2", FieldType.INT));
		schema.addField(2, new Field("Field3", FieldType.STRING));
		schema.addField(3, new Field("Field4", FieldType.LONG));
		schema.addField(4, new Field("Field5", FieldType.STRING));
		
		/**
		 * Start the ShuffleReceiver
		 */
		ShuffleReceiver shuffleReceiver = new ShuffleReceiver(schema);
		shuffleReceiver.start();
		Util.log("ShuffleReceiver started.", StartServers4QueryProcessTest.class);

		/**
		 * Start the DuplicateReceiver
		 */
		IngestKVReplicaReceiver duplicateReceiver = new IngestKVReplicaReceiver();
		duplicateReceiver.start();
		Util.log("DuplicateReceiver started.", StartServers4QueryProcessTest.class);

		/**
		 * Start the IngestReceiver
		 */
		IngestReceiver ingestReceiver = new IngestReceiver(schema);
		ingestReceiver.start();
		Util.log("IngestReceiver started.", StartServers4QueryProcessTest.class);
		
		/**
		 * Start the TimeRangeKVQueryProcessor
		 */
		TimeRangeKVQueryProcessor trServer = new TimeRangeKVQueryProcessor(schema);
		trServer.start();
		Util.log("TimeRangeKV Query processing server started.", StartServers4QueryProcessTest.class);
		
		/**
		 * Start IngestKVQPServer
		 */
		IngestKVQueryProcessor inServer = new IngestKVQueryProcessor(ingestReceiver);
		inServer.start();
		Util.log("IngestKV Query processing server started.", StartServers4QueryProcessTest.class);
		
		/**
		 * Start RequestProcessor
		 */
		//UIRequestProcessor requestProcessor = new UIRequestProcessor();
		//requestProcessor.start();
		//Util.log("RequestProcessor started.", StartServers4QueryProcessTest.class);
		
		/**
		 * Start LogSourceRunner
		 */
		LogSourceRunner logSourceRunner = new LogSourceRunner(schema);
		logSourceRunner.start();
		Util.log("LogSourceRunner started.", StartServers4QueryProcessTest.class);
		
		/**
		 * Generate and ingest event to IngestReceiver
		 */
		EventGenerator eventGen = new EventGenerator(schema);
		Util.log("[" + System.currentTimeMillis() + "]", StartServers4QueryProcessTest.class);
		long totalEvents = 500 * Parameter.TRU_SIZE-2;
		for (long l = 0; l < totalEvents; l++) {
			if (l % (100 * Parameter.TRU_SIZE) == 0) {
				Util.log(l + " events ingested.", StartServers4QueryProcessTest.class);
			}
			Event event = eventGen.generateEvent(l); //System.currentTimeMillis()
			ingestReceiver.addEvent(event);
			if(l < 200){
				System.out.println(event);
			}
		}
		Util.log("Ingested " + totalEvents + " events.", StartServers4QueryProcessTest.class);	
		
	}

}
