package com.hp.hpl.logkv.ui;

import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.TreeMap;

import com.hp.hpl.logkv.conf.Parameter;
import com.hp.hpl.logkv.coordinator.CoordinatorAccessor;
import com.hp.hpl.logkv.demo.jms.JMSSender;
import com.hp.hpl.logkv.query.Query;
import com.hp.hpl.logkv.queryprocess.QueryProcessingStatistics.TRUProcessingStatistics;
import com.hp.hpl.logkv.queryprocess.QueryProxy;
import com.hp.hpl.logkv.queryprocess.QueryProcessingStatistics;
import com.hp.hpl.logkv.queryprocess.ResultSet.Record;


public class QueryProcessThread implements Runnable {

	private JMSSender sender = null;
	private String requestID = null;

	private QueryRequest request = null;
	
	private static CoordinatorAccessor coordinatorAccessor = new CoordinatorAccessor();
	private static long startTRU = -1L;	

	static{
		try {
			startTRU = coordinatorAccessor.getStartupTRU();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		// TODO ..............
		Query query = new Query(request.getQuery());
		//query.startTime = System.currentTimeMillis() - Parameter.TRU_SIZE * 5;
		//query.endTime = query.startTime + 1 * Parameter.TRU_SIZE;
		
		query.startTime = request.getStartTime();
		query.endTime = request.getEndTime();

		QueryProxy queryProxy = new QueryProxy(query);
		Record[] resultSet = queryProxy.execute();
		int length = resultSet.length;
		length = (length > 100? 100: length);
		Record[] resultSetSendOut = new Record[length];
		System.arraycopy(resultSet, 0, resultSetSendOut, 0, length);
		String[] columnNames = queryProxy.getColumnNames();
		QueryProcessingStatistics runningStatistics = queryProxy.getQueryProcessingStatistics();

		int[] columnIds = null;
		if (query.bSelfJoin) {
			columnIds = new int[columnNames.length];
			for (int i = 0; i < columnNames.length; i++) {
				columnIds[i] = i;
			}
		} else {
			columnIds = query.getColumnIds();
		}
		
		System.out.println("ColumnIds: " + Arrays.toString(columnIds));
		
		TreeMap<Long, TRUProcessingStatistics> updatedTruStatistics = new TreeMap<Long, TRUProcessingStatistics>();
		
		Iterator<Entry<Long, TRUProcessingStatistics>> it = runningStatistics.getTruStatistics().entrySet().iterator();
		while(it.hasNext()){
			Entry<Long, TRUProcessingStatistics> entry = it.next();
			TRUProcessingStatistics statistic = entry.getValue();
			long updatedTruId = statistic.getTruId() - this.startTRU;
			statistic.setTruId(updatedTruId);
			updatedTruStatistics.put(updatedTruId, statistic);
		}
		
		runningStatistics.setTruStatistics(updatedTruStatistics);
		
		QueryResponse queryResponse = new QueryResponse(System.currentTimeMillis(), resultSetSendOut, columnNames, columnIds, runningStatistics);
		sender.send(requestID, queryResponse);
	}

	public void setSender(JMSSender sender) {
		this.sender = sender;
	}

	public void setRequestId(String requestID) {
		this.requestID = requestID;
	}

	public QueryRequest getRequest() {
		return request;
	}

	public void setRequest(QueryRequest request) {
		this.request = request;
	}
}
