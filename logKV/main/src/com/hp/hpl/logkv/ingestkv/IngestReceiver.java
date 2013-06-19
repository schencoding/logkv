package com.hp.hpl.logkv.ingestkv;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.RemoteException;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TreeMap;
import java.util.Vector;

import com.hp.hpl.logkv.conf.Parameter;
import com.hp.hpl.logkv.coordinator.CoordinatorAccessor;
import com.hp.hpl.logkv.model.Event;
import com.hp.hpl.logkv.model.LocalTRU;
import com.hp.hpl.logkv.model.ModelUtil;
import com.hp.hpl.logkv.model.Schema;
import com.hp.hpl.logkv.ui.GetShuffleStatusThread.CheckRequestLiveStatusTask;
import com.hp.hpl.logkv.util.SerializeHelper;
import com.hp.hpl.logkv.util.Util;

public class IngestReceiver extends Thread {
	public static Schema schema = null;
	private ServerSocket socketServer = null;
	private TreeMap<Long, LocalTRU> localTRUs = new TreeMap<Long, LocalTRU>();
	private long prevTRUId = -1;
	private LocalTRU currentTRU = null;
	private IngestKVReplicaSender dupSender = null;
	private ShuffleSender shuffleSender = null;
	private ByteArrayOutputStream baos = new ByteArrayOutputStream();
	private int bufferedBytesCounter = 0;
	private String[] dupReceiverIps;
	public static String myIpStr;
	private long numOfEvents = 0;

	private TreeMap<Long, LocalTRU> tentativeTRUs = new TreeMap<Long, LocalTRU>();

	public boolean bSendReplica = false;

	public static CoordinatorAccessor coordinatorAccessor = new CoordinatorAccessor();

	static {
		myIpStr = Util.getMyIpStr();
	}

	public IngestReceiver(Schema schema) {
		this.schema = schema;
		try {
			if (coordinatorAccessor.getSchema() == null) {
				coordinatorAccessor.setSchema(schema);
			}
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}

		Timer timer = new Timer("Move LocalTRU from tentative to localTRUs");
		timer.schedule(new TentativeTRUChecker(), 0, Parameter.TRU_SIZE);

		if (bSendReplica) {
			dupReceiverIps = coordinatorAccessor.getDuplicateReceivers(myIpStr);
			if (dupReceiverIps.length < 1) {
				System.err.println("No duplicate reciever for log sources in " + myIpStr);
			}
			dupSender = new IngestKVReplicaSender(dupReceiverIps[0]);
			dupSender.start();
		}

		shuffleSender = new ShuffleSender(localTRUs);
		shuffleSender.start();

		try {
			socketServer = new ServerSocket(Parameter.INGEST_RECEIVER_PORT);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		if (bSendReplica) {
			IngestKVReplicaSenderThread duplicateSenderThread = new IngestKVReplicaSenderThread();
			duplicateSenderThread.start();
		}

		while (!this.isInterrupted()) {
			try {
				Socket socket = socketServer.accept();

				Util.log("One source from " + socket.getRemoteSocketAddress().toString() + "is accepted.", this.getClass());

				IngestReceiverTask task = new IngestReceiverTask();
				task.setSocket(socket);
				task.start();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public synchronized void addEvent(Event event) {
		
		numOfEvents ++;
		if(numOfEvents%1000 == 0){
			try {
				coordinatorAccessor.addNumOfEvents(myIpStr, 1000);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		/*
		 * Be cautious This is used for synchronizing the time of different
		 * servers
		 */
		// event.setTimestamp(System.currentTimeMillis());
		long truId = ModelUtil.getTRUId(event.getTimestamp());

		LocalTRU tru2AddEvent = tentativeTRUs.get(truId);
		if (tru2AddEvent == null) {
			tru2AddEvent = new LocalTRU(truId, schema);
			tentativeTRUs.put(truId, tru2AddEvent);
		}

		
		/*if (truId != prevTRUId) {
			if (bSendReplica) {
				sendBytesOut();
			}
			if (currentTRU != null) {
				localTRUs.put(prevTRUId, currentTRU);
				if (prevTRUId != -1) {
					coordinatorAccessor.addTRU(this.myIpStr, prevTRUId); // currentTRU.getNumOfEvent();
					currentTRU.close();
				}
			}
			try {
				coordinatorAccessor.addNumOfEvents(this.myIpStr, numOfEvents);
				Util.log(("" + numOfEvents + " events ingested for TRU " + prevTRUId + " at server " + this.myIpStr), this.getClass());
			} catch (RemoteException e) {
				e.printStackTrace();
			}

			currentTRU = new LocalTRU(truId, schema);
			prevTRUId = truId;

			numOfEvents = 0;

			if (bSendReplica) {
				dupSender.close();
				dupSender = new IngestKVReplicaSender(dupReceiverIps[0]);
				sendTRUIdOut(truId);
				dupSender.start();
			}
		}*/
		 

		byte[] bytes = event.serialize();
		tru2AddEvent.writeEvent(bytes);
		bufferedBytesCounter += bytes.length;

		baos.write(bytes, 0, bytes.length);
		try {
			baos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void sendTRUIdOut(long truId) {
		byte[] bytes = SerializeHelper.longToByte(truId);
		dupSender.addBytes(bytes);
	}

	public void sendBytesOut() {
		if (bufferedBytesCounter != 0) {
			dupSender.addBytes(baos.toByteArray());
			baos.reset();
			bufferedBytesCounter = 0;
		}
	}

	public void sendFinishOut() {
		dupSender.addBytes(new byte[0]);
	}

	public LocalTRU getLocalTRU(long index) {
		return localTRUs.get(index);
	}

	public int getNumOfLocalTRU() {
		return localTRUs.size();
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		Iterator<Long> it = localTRUs.keySet().iterator();
		while (it.hasNext()) {
			Long truId = it.next();
			LocalTRU tru = localTRUs.get(truId);
			sb.append(tru.toString() + "\n");
		}
		return sb.toString();
	}

	public void printCurrentLocalTRUs() {
		Iterator<Entry<Long, LocalTRU>> it = this.localTRUs.entrySet().iterator();
		while (it.hasNext()) {
			Entry<Long, LocalTRU> entry = it.next();
			System.out.println(entry.getKey());
		}

	}

	public class IngestReceiverTask extends Thread {

		private Socket socket = null;

		@Override
		public void run() {
			try {
				DataInputStream dis = new DataInputStream(new BufferedInputStream(socket.getInputStream()));

				while (!this.isInterrupted()) {
					Event event = new Event(schema);
					event.read(dis);
					addEvent(event);
				}

			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		public void setSocket(Socket socket) {
			this.socket = socket;
		}

	}

	public class IngestKVReplicaSenderThread extends Thread {

		@Override
		public void run() {
			while (true) {
				try {
					Thread.sleep(Parameter.INGEST_KV_REPLICA_INTERVAL);
					sendBytesOut();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}

	}

	public class TentativeTRUChecker extends TimerTask {

		public void run() {

			long current = System.currentTimeMillis();
			long latestTruId = Util.getTruId(current);
			
			Vector<Long> trus2Move = new Vector<Long>();
			
			Iterator<Long> it = tentativeTRUs.keySet().iterator();
			while(it.hasNext()){
				Long truId = it.next();
				if(latestTruId - truId > Parameter.TENTATIVE_TIMERANGE){
					trus2Move.add(truId);
				}
			}
			
			int length = trus2Move.size();
			for(int i=0; i<length; i++){
				Long truId = trus2Move.get(i);
				LocalTRU localTRU = tentativeTRUs.remove(truId);
				localTRUs.put(truId, localTRU);
				
				coordinatorAccessor.addTRU(myIpStr, truId); // currentTRU.getNumOfEvent();
				localTRU.close();
				long numOfEvents = localTRU.getNumOfEvent();					
				Util.log(("" + numOfEvents + " events ingested for TRU " + truId + " at server " + myIpStr), this.getClass());
				
			}

		}
	}

}
