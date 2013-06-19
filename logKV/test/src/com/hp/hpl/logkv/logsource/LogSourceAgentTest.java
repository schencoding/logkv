package com.hp.hpl.logkv.logsource;

import java.rmi.RemoteException;

import com.hp.hpl.logkv.coordinator.CoordinatorAccessor;
import com.hp.hpl.logkv.ingestkv.IngestKVReplicaReceiver;
import com.hp.hpl.logkv.ingestkv.IngestReceiver;
import com.hp.hpl.logkv.ingestkv.IngestReceiverTest;
import com.hp.hpl.logkv.ingestkv.ShuffleReceiver;
import com.hp.hpl.logkv.model.Field;
import com.hp.hpl.logkv.model.FieldType;
import com.hp.hpl.logkv.model.Schema;
import com.hp.hpl.logkv.util.Util;

public class LogSourceAgentTest {

	/**
	 * @param args
	 * @throws RemoteException
	 */
	public static void main(String[] args) throws RemoteException {

		Schema schema = new Schema("Synthetic event");
		schema.addField(0, new Field("Field1", FieldType.INT));
		schema.addField(1, new Field("Field2", FieldType.INT));
		schema.addField(2, new Field("Field3", FieldType.STRING));
		schema.addField(3, new Field("Field4", FieldType.LONG));
		schema.addField(4, new Field("Field5", FieldType.STRING));

		ShuffleReceiver shuffleReceiver = new ShuffleReceiver(schema);
		shuffleReceiver.start();
		Util.log("ShuffleReceiver started.", LogSourceAgentTest.class);

		IngestKVReplicaReceiver duplicateReceiver = new IngestKVReplicaReceiver();
		duplicateReceiver.start();
		Util.log("DuplicateReceiver started.", LogSourceAgentTest.class);

		IngestReceiver ingestReceiver = new IngestReceiver(schema);
		ingestReceiver.start();
		Util.log("IngestReceiver started.", LogSourceAgentTest.class);

		CoordinatorAccessor coordinatorAccessor = new CoordinatorAccessor();

		// 0
		LogSourceDescriptor logSource0 = new LogSourceDescriptor();
		logSource0.setSourceIp("ip-0");
		logSource0.setLogSourceId(0);
		logSource0.setbSplitable(true);
		logSource0.setThroughput(10);
		logSource0 = coordinatorAccessor.addLogSource(logSource0);
		LogSourceToWorkerNodeMappingServiceTest.printMapping(coordinatorAccessor.getAllLogSources());

		LogSourceAgent agent = new LogSourceAgent(logSource0, schema);
		agent.start();
		
		try {
			Thread.sleep(20 * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// 1
		LogSourceDescriptor logSource1 = new LogSourceDescriptor();
		logSource1.setSourceIp("ip-1");
		logSource1.setLogSourceId(1);
		logSource1.setbSplitable(false);
		logSource1.setThroughput(15);
		logSource1 = coordinatorAccessor.addLogSource(logSource1);
		LogSourceToWorkerNodeMappingServiceTest.printMapping(coordinatorAccessor.getAllLogSources());
		
		agent.updateLogSource(logSource1);

	}

}
