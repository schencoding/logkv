package com.hp.hpl.logkv.ingestkv;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.util.Random;
import java.util.Vector;

import com.hp.hpl.logkv.conf.Parameter;
import com.hp.hpl.logkv.coordinator.CoordinatorAccessor;
import com.hp.hpl.logkv.model.Event;
import com.hp.hpl.logkv.model.GlobalTRU;
import com.hp.hpl.logkv.model.LocalTRU;
import com.hp.hpl.logkv.model.Schema;
import com.hp.hpl.logkv.util.Util;
import com.hp.hpl.logkv.util.WorkQueue;

public class ShuffleReceiver extends Thread {

	private Schema schema;
	private GlobalTRU currentGlobalTRU = null;
	private Vector<String> todoList = new Vector<String>();
	private CoordinatorAccessor coordinatorAccessor = new CoordinatorAccessor();
	private WorkQueue converterThreads = new WorkQueue(Parameter.NUMBER_OF_KV_CONVERTERS);
	private String[] allLiveNodes = null;

	/**
	 * Get the IP address of this node
	 */
	public static String ipMyself = null;
	static {
		ipMyself = Util.getMyIpStr();
	}

	/***
	 * 
	 * @param schema
	 *            : the schema of event. As we need to deserialize the received
	 *            bytes to Event instance, the schema is required.
	 */
	public ShuffleReceiver(Schema schema) {
		this.schema = schema;

		/*
		 * Get all the live nodes IP string from Coordinator Server
		 */
		try {
			allLiveNodes = coordinatorAccessor.getAllLiveNodes();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		if (allLiveNodes == null) {
			System.err.println("Live nodes expected.");
		}
	}

	/***
	 * Pull the all the LocalTRUs of a GlobalTRU from the remote node
	 */
	@Override
	public void run() {
		Random rand = new Random();
		while (!interrupted()) {
			/*
			 * Try to find the next TRU to be shuffled
			 */
			Long nextTRU = null;
			try {
				nextTRU = coordinatorAccessor.getNextTRU(ipMyself);
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
			if (nextTRU == null) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				continue;
			}			
			Util.log("nextTRU = " + nextTRU, this.getClass());
			
			/*
			 * Pull the TRU to be shuffled from each worker nodes one by one
			 */
			if (nextTRU != null) {
				addHosts2TodoList();
			}
			int length = todoList.size();
			for (int i = 0; i < length; i++) {
				int index = rand.nextInt(todoList.size());
				boolean bSuccess = pullTRU(todoList.get(index), nextTRU.longValue());
				if(bSuccess){
					todoList.remove(index);
				}
			}
			
			/*
			 * Start a new thread to: 
			 * (1) convert the TRU to column-oriented storage 
			 * (2)write it to key-value store
			 */
			TimeRangeKVWriter converter = new TimeRangeKVWriter(currentGlobalTRU, schema);
			converterThreads.execute(converter);
			Util.log("Received all the local TRUs: " + this.currentGlobalTRU.getTruId(), this.getClass());

			
			todoList.clear();
			currentGlobalTRU = null;
		}
	}

	private void addHosts2TodoList() {		
		for (int i = 0; i < allLiveNodes.length; i++) {
			todoList.addElement(allLiveNodes[i]);
		}
	}

	public boolean pullTRU(String ipStr, Long truId) {
		Util.log("Pulling TRU " + truId + " from " + ipStr, this.getClass());
		
		
		if (currentGlobalTRU == null) {
			GlobalTRU globalTRU = new GlobalTRU(schema, truId);
			currentGlobalTRU = globalTRU;
		}
		try {
			Util.log("Build connection to pull TRU " + truId + " from " + ipStr, this.getClass());
			Socket sock = new Socket(ipStr, Parameter.SHUFFLE_SENDER_PORT);
			ObjectOutputStream oos = new ObjectOutputStream(sock.getOutputStream());
			ShuffleRequest request = new ShuffleRequest(truId);
			oos.writeObject(request);
			oos.flush();

			DataInputStream dis = new DataInputStream(new BufferedInputStream(sock.getInputStream()));
			int iAvailable = dis.readInt();
			boolean bAvailable = true;
			if (iAvailable == -1) {
				bAvailable = false;
			}
			if (bAvailable) {
				long localTRUId = dis.readLong();
				if (localTRUId != truId.longValue()) {
					System.err.println("Expect " + truId + " but we receive " + localTRUId);
					return false;
				}
				Event event = new Event(schema);
				boolean bEnd = false;
				int count = 0;

				Util.log("Going to read TRU " + truId + " from " + ipStr, this.getClass());
				
				while (!bEnd)
					try {
						event.read(dis);
						currentGlobalTRU.addEvent(event);
						event = new Event(schema);
						count++;
					} catch (Exception e) {
						if (e instanceof EOFException) {
							bEnd = true;
						} else {
							e.printStackTrace();
						}
					}
				Util.log(count + " events of TRU " + truId + " received from " + ipStr, this.getClass());
			} else {
				Util.log(ipStr + " is busy which fetching TRU " + truId, this.getClass());
				return false;
			}
			if (oos != null) {
				oos.close();
			}
			if (dis != null) {
				dis.close();
			}
			if (sock != null) {
				sock.close();
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			coordinatorAccessor.addShuffleStatus(new ShuffleStatus(ipStr, this.ipMyself, truId, false));
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
		return true;
	}

}
