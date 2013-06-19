package com.hp.hpl.logkv.queryprocess;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Vector;

import com.hp.hpl.logkv.conf.Parameter;
import com.hp.hpl.logkv.query.Query;
import com.hp.hpl.logkv.util.Util;

public class IngestKVQueryProxyThread implements Runnable{
	
	private String ipStr;
	private Query query;
	private Vector<ResultSet> resultSets;
	private QueryProcessingStatistics qpStatistics;
	private QueryProcessingStatistics.IngestKVProcessingStatistics ingestKVStatistic;
	
	public IngestKVQueryProxyThread(String ipStr, Query query, Vector<ResultSet> resultSets, QueryProcessingStatistics qpStatistics){
		this.ipStr = ipStr;
		this.query = query;
		this.resultSets = resultSets;
		ingestKVStatistic = new QueryProcessingStatistics.IngestKVProcessingStatistics(ipStr);
		qpStatistics.putIngestKVProcessingStatistics(ingestKVStatistic);
	}

	@Override
	public void run() {
		long startTime = System.currentTimeMillis();	
		Socket sock;
		try {
			sock = new Socket(ipStr, Parameter.INGEST_KV_QP_PORT);
			ObjectOutputStream oos = new ObjectOutputStream(sock.getOutputStream());
			Util.log("Query is sent out.", this.getClass());
			oos.writeObject(query);
			ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(sock.getInputStream()));
			ResultSet resultSet = (ResultSet) ois.readObject();
			Util.log("Received result.", this.getClass());
			resultSets.add(resultSet);
			
			Util.log("Retured " + resultSet.size() + " events in " + ipStr, this.getClass());
			ingestKVStatistic.setNumOfReturnedRecords(resultSet.size());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		long endTime = System.currentTimeMillis();
		ingestKVStatistic.setRunningTime(endTime - startTime);
		
	}

}
