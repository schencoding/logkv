package com.hp.hpl.logkv.ingestkv;

import com.hp.hpl.logkv.ingestkv.IngestKVReplicaReceiver;
import com.hp.hpl.logkv.util.Util;

public class DuplicateReceiverTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Util.log("Starting duplicate receiver...", DuplicateReceiverTest.class);
		IngestKVReplicaReceiver receiver = new IngestKVReplicaReceiver();
		receiver.start();
		Util.log("Dupliate receiver is running ...", DuplicateReceiverTest.class);
	}

}
