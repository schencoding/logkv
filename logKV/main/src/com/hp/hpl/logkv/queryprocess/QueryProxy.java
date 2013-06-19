package com.hp.hpl.logkv.queryprocess;

import java.rmi.RemoteException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Vector;


import com.hp.hpl.logkv.coordinator.CoordinatorAccessor;
import com.hp.hpl.logkv.coordinator.CoordinatorImpl;
import com.hp.hpl.logkv.model.Schema;
import com.hp.hpl.logkv.query.Query;
import com.hp.hpl.logkv.queryprocess.ResultSet.Record;
import com.hp.hpl.logkv.util.Util;

public class QueryProxy {

	private CoordinatorAccessor coordinatorAccessor = new CoordinatorAccessor();
	private Query query;
	private QueryProcessingStatistics qpStatistics = new QueryProcessingStatistics();

	public QueryProxy(Query query) {
		this.query = query;
	}

	public Record[] execute() {
		long startTime = System.currentTimeMillis();
		
		List<Record> finalResults = new LinkedList<Record>();
		Vector<ResultSet> timeRangeKVResultSets = new Vector<ResultSet>();
		int numOfTrusInTimeRangeKV = 0;
		
		long startTruId = Util.getTruId(query.startTime);
		long endTruId = Util.getTruId(query.endTime);
		for (long truId = startTruId; truId <= endTruId; truId++) {
			Query subQuery = new Query(query);
			subQuery.subQueryTruId = truId;
			try {
				/**
				 * in time range KV
				 */
				if (coordinatorAccessor.checkIfShuffled(truId)) {
					String endPoint = CoordinatorImpl.getEndPoint(truId);
					if (endPoint != null) {
						numOfTrusInTimeRangeKV ++;
						TimeRangeKVQueryProxyThread thread = new TimeRangeKVQueryProxyThread(endPoint, subQuery, timeRangeKVResultSets, qpStatistics);
						thread.start();
					}
				}
				/**
				 * in each worker node (ingest kv)
				 */
				else {
					String[] nodes = coordinatorAccessor.getAllLiveNodes();
					Vector<ResultSet> resultSets = new Vector<ResultSet>(nodes.length);
					for(int i = 0; i<nodes.length; i++){
						IngestKVQueryProxyThread task = new IngestKVQueryProxyThread(nodes[i], subQuery, resultSets, qpStatistics);
						task.run();
					}
					
					while(resultSets.size() < nodes.length){
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					
					int count = 0;
					for(int i = 0; i<resultSets.size(); i++){
						ResultSet rs = resultSets.get(i);
						count += rs.size();
						Iterator<Entry<Long, Record>> it = rs.getRecords().entrySet().iterator();
						while(it.hasNext()){
							finalResults.add(it.next().getValue());
						}
					}
					Util.log("Retured " + count + " events from IngestKV", this.getClass());

				}
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		
		if(numOfTrusInTimeRangeKV > 0){
			/**
			 * waiting for the result from all the time ranges
			 */
			while(timeRangeKVResultSets.size() < numOfTrusInTimeRangeKV){
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			/**
			 * Merge the results of all the time ranges together
			 */
			int count = 0;
			for(int i = 0; i<timeRangeKVResultSets.size(); i++){
				ResultSet rs = timeRangeKVResultSets.get(i);
				count += rs.size();
				Iterator<Entry<Long, Record>> it = rs.getRecords().entrySet().iterator();
				while(it.hasNext()){
					finalResults.add(it.next().getValue());
				}
			}
			Util.log("Retured " + count + " events from TimeRangeKV", this.getClass());
		}
		
		long endTime = System.currentTimeMillis();
		long runningTime = endTime - startTime;
		
		qpStatistics.setRunningTime(runningTime);
		
		return finalResults.toArray(new Record[]{});
	}

	public String[] getColumnNames() {
		String[] a = new String[query.columnNamesInSelect.size()];		
		query.columnNamesInSelect.toArray(a);
		return a;
	}

	public QueryProcessingStatistics getQueryProcessingStatistics() {
		return this.qpStatistics;
	}
	
}
