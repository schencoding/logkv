package com.hp.hpl.logkv.logsource;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Random;

import com.hp.hpl.logkv.conf.Parameter;
import com.hp.hpl.logkv.coordinator.CoordinatorAccessor;
import com.hp.hpl.logkv.eventsource.EventGenerator;
import com.hp.hpl.logkv.logsource.LogSourceToWorkerNodeMapper.WorkerNode;
import com.hp.hpl.logkv.model.Event;
import com.hp.hpl.logkv.model.Schema;

public class LogSourceAgent extends Thread{
	private LogSourceDescriptor logSource = null;
	private Schema schema = null;
	private EventGenerator eventGen = null;
	private HashMap<Integer, WorkerNode> workerNodes = new HashMap<Integer, WorkerNode>();
	private HashMap<Integer, Socket> sockets = new HashMap<Integer, Socket>();
	private HashMap<Integer, DataOutputStream> outputs = new HashMap<Integer, DataOutputStream>();
	private CoordinatorAccessor coordinatorAccessor = new CoordinatorAccessor();
	long eventId = 0;

	public LogSourceAgent(LogSourceDescriptor logSource, Schema schema) {
		this.logSource = logSource;
		this.setSchema(schema);
		eventGen = new EventGenerator(schema);
		updateMapping();
	}
	
	public synchronized void updateLogSource(LogSourceDescriptor logSource){
		this.logSource = logSource;
		updateMapping();
	}
	
	public synchronized void updateMapping(){
		sockets.clear();
		outputs.clear();
		
		updateWorkerNode();
		
		System.out.println("===========new mapping:\n" + logSource);
		
		Iterator<Entry<Integer, Double>> it = logSource.getMapping().entrySet().iterator();
		while (it.hasNext()) {
			Entry<Integer, Double> entry = it.next();
			int workerNodeIndex = entry.getKey();
			try {
				Socket socket = new Socket(workerNodes.get(workerNodeIndex).getIpStr(), Parameter.INGEST_RECEIVER_PORT);
				sockets.put(workerNodeIndex, socket);
				DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
				outputs.put(workerNodeIndex, dos);
				
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void updateWorkerNode() {
		workerNodes.clear();
		try {
			String[] nodes = coordinatorAccessor.getAllLiveNodes();
			for(int i = 0; i < nodes.length; i++){
				WorkerNode workerNode = new WorkerNode(i);
				workerNode.setIpStr(nodes[i]);
				workerNodes.put(i, workerNode);
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		Random rand = new Random();
		boolean bEnd = false;		
		
		while (!bEnd) {
			bEnd = generateAnEvent(rand);
			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public synchronized boolean generateAnEvent(Random rand){
		boolean bEnd = false;
		long prefix = 100000000L * this.logSource.getLogSourceId();
		Event event = eventGen.generateEvent(eventId + prefix);
		eventId++;
		
		double r = rand.nextDouble();
		double percentage = 0.0;
		Iterator<Entry<Integer, Double>> it = logSource.getMapping().entrySet().iterator();
		while (it.hasNext()) {
			Entry<Integer, Double> entry = it.next();
			int workerNodeIndex = entry.getKey();
			percentage += entry.getValue();

			if (percentage >= r) {
				try {
		//			System.out.println("Send out to: " + workerNodeIndex);
					event.write(outputs.get(workerNodeIndex));
					break;
				} catch (IOException e) {
					bEnd = true;
					e.printStackTrace();
				}
			}
		}
		
		return bEnd;
	}
	


	public void close() {
		/**
		 * close DataOutputStream
		 */
		try {
			Iterator<Entry<Integer, DataOutputStream>> it = outputs.entrySet().iterator();
			while (it.hasNext()) {
				Entry<Integer, DataOutputStream> entry = it.next();
				DataOutputStream dos = entry.getValue();
				if (dos != null) {
					dos.flush();
					dos.close();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		/***
		 * close socket
		 */
		try {
			Iterator<Entry<Integer, Socket>> it = sockets.entrySet().iterator();
			while (it.hasNext()) {
				Entry<Integer, Socket> entry = it.next();
				Socket socket = entry.getValue();
				if (socket != null) {
					socket.close();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Schema getSchema() {
		return schema;
	}

	public void setSchema(Schema schema) {
		this.schema = schema;
	}
}
