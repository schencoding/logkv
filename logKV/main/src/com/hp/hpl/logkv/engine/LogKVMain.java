package com.hp.hpl.logkv.engine;

import com.hp.hpl.logkv.conf.Parameter;
import com.hp.hpl.logkv.ingestkv.IngestKVReplicaReceiver;
import com.hp.hpl.logkv.ingestkv.IngestReceiver;
import com.hp.hpl.logkv.ingestkv.ShuffleReceiver;
import com.hp.hpl.logkv.model.Schema;
import com.hp.hpl.logkv.queryprocess.IngestKVQueryProcessor;
import com.hp.hpl.logkv.queryprocess.TimeRangeKVQueryProcessor;
import com.hp.hpl.logkv.util.Util;

public class LogKVMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/**
		 * Get the Schema for event
		 */
		Schema schema = Parameter.schema;

		/**
		 * Start the ShuffleReceiver
		 */
		ShuffleReceiver shuffleReceiver = new ShuffleReceiver(schema);
		shuffleReceiver.start();
		Util.log("ShuffleReceiver started.", LogKVMain.class);

		/**
		 * Start the DuplicateReceiver
		 */
		/*
		IngestKVReplicaReceiver duplicateReceiver = new IngestKVReplicaReceiver();
		duplicateReceiver.start();
		Util.log("IngestKVReplicaReceiver started.", StartServers4QueryProcessTest.class);
		*/

		/**
		 * Start the IngestReceiver
		 */
		IngestReceiver ingestReceiver = new IngestReceiver(schema);
		ingestReceiver.start();
		Util.log("IngestReceiver started.", LogKVMain.class);

		/**
		 * Start the TimeRangeKVQueryProcessor
		 */
		TimeRangeKVQueryProcessor trServer = new TimeRangeKVQueryProcessor(schema);
		trServer.start();
		Util.log("TimeRangeKV Query processing server started.", LogKVMain.class);

		/**
		 * Start IngestKVQPServer
		 */
		IngestKVQueryProcessor inServer = new IngestKVQueryProcessor(ingestReceiver);
		inServer.start();
		Util.log("IngestKV Query processing server started.", LogKVMain.class);


		

	}

}
