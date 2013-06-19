package com.hp.hpl.logkv.logsource;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import com.hp.hpl.logkv.conf.Parameter;

public class LogSourceToWorkerNodeMapper {
	private HashMap<String, LogSourceDescriptor> logSources = new HashMap<String, LogSourceDescriptor>();
	private List<WorkerNode> workerNodes = new ArrayList<WorkerNode>();

	public LogSourceToWorkerNodeMapper() {
		for (int i = 0; i < Parameter.NUM_OF_INGEST_RECEIVER; i++) {
			workerNodes.add(new WorkerNode(i));
		}
	}

	public void addLogSource(LogSourceDescriptor logSource) {
		this.clearMapping();
		logSources.put(logSource.getSourceIp() + ":" + logSource.getLogSourceId(), logSource);

		this.assignIndividableSources();
		this.assignDividableSources();

	}

	public List<LogSourceDescriptor> getAllDividableSources() {
		List<LogSourceDescriptor> list = new ArrayList<LogSourceDescriptor>();

		Iterator<Entry<String, LogSourceDescriptor>> it = logSources.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, LogSourceDescriptor> entry = it.next();
			LogSourceDescriptor source = entry.getValue();
			if (source.isbSplitable()) {
				list.add(source);
			}
		}

		return list;
	}

	public List<LogSourceDescriptor> getAllIndividableSources() {
		List<LogSourceDescriptor> list = new ArrayList<LogSourceDescriptor>();

		Iterator<Entry<String, LogSourceDescriptor>> it = logSources.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, LogSourceDescriptor> entry = it.next();
			LogSourceDescriptor source = entry.getValue();
			if (!source.isbSplitable()) {
				list.add(source);
			}
		}

		return list;
	}

	public void assignDividableSources() {
		List<LogSourceDescriptor> dividableSources = this.getAllDividableSources();
		if(dividableSources.size() <= 0){
			return;
		}

		//1
		int length = dividableSources.size();
		int divTotal = 0;
		for (int i = 0; i < length; i++) {
			LogSourceDescriptor logSource = dividableSources.get(i);
			divTotal += logSource.getThroughput();
		}
		//2
		Collections.sort(workerNodes, new WorkerNodeComparable());

		//3
		double t = divTotal;
		//4
		double targetMin = 0;
		int N = Parameter.NUM_OF_INGEST_RECEIVER;
		int k = 0;
		for (k = 0; k < N; k++) {
			t += workerNodes.get(k).totalThroughput;
			if ((k == N - 1) || (k!= 0 && (t / k <= workerNodes.get(k + 1).totalThroughput))) {
				targetMin = t / (k+1);
				break;
			}
		}
		//9
		int i = 0;
		
		LogSourceDescriptor logSource = dividableSources.get(i);
		double li = logSource.getThroughput();
		//10
		for (int j = 0; j <= k; j++) {
			double diff = targetMin - workerNodes.get(j).totalThroughput;
			while (diff > 0) {
				if (diff >= li) {
					dividableSources.get(i).addMapping(workerNodes.get(j).index, ((double)li / (double)dividableSources.get(i).getThroughput()));
					workerNodes.get(j).totalThroughput += li;
					diff -= li;
					i++;
					if(i>= dividableSources.size()){
						break;
					}
					logSource = dividableSources.get(i);
					li = logSource.getThroughput();
				} else {
					dividableSources.get(i).addMapping(workerNodes.get(j).index, ((double)diff / (double)dividableSources.get(i).getThroughput()));
					workerNodes.get(j).totalThroughput += diff;
					li -= diff;
					diff = 0;
				}
			}
			workerNodes.get(j).totalThroughput = targetMin;
		}
	}

	public void assignIndividableSources() {
		List<LogSourceDescriptor> individableSources = this.getAllIndividableSources();

		Collections.sort(individableSources, new LogSourceComparable());

		int numOfWorkerNode = Parameter.NUM_OF_INGEST_RECEIVER;
		for (int i = 0; i < individableSources.size(); i++) {
			LogSourceDescriptor logSource = individableSources.get(i);
			int workNodeIndex = i % numOfWorkerNode;
			logSource.addMapping(workNodeIndex, 1);
			workerNodes.get(workNodeIndex).totalThroughput += logSource.getThroughput();
		}

	}
	
	public void clearMapping(){
		Iterator<Entry<String, LogSourceDescriptor>> it = this.logSources.entrySet().iterator();
		while(it.hasNext()){
			LogSourceDescriptor logSource  = it.next().getValue();
			logSource.clearMapping();
		}
		
		Iterator<WorkerNode> wnIt = workerNodes.iterator();
		while(wnIt.hasNext()){
			WorkerNode workerNode = wnIt.next();
			workerNode.clear();
		}
	}

	public static class WorkerNode {
		public int index;
		public String ipStr = null;
		public List<LogSourceDescriptor> logSources = new ArrayList<LogSourceDescriptor>();
		public double totalThroughput = 0;

		public WorkerNode(int index) {
			this.index = index;
		}
		
		

		public String getIpStr() {
			return ipStr;
		}



		public void setIpStr(String ipStr) {
			this.ipStr = ipStr;
		}



		public void clear() {
			this.totalThroughput = 0;
			this.logSources.clear();			
		}
	}

	public class WorkerNodeComparable implements Comparator<WorkerNode> {
		@Override
		public int compare(WorkerNode w1, WorkerNode w2) {
			double t1 = w1.totalThroughput;
			double t2 = w2.totalThroughput;
			return (t1 > t2 ? -1 : (t1 == t2 ? 0 : 1));
		}
	}

	public class LogSourceComparable implements Comparator<LogSourceDescriptor> {

		@Override
		public int compare(LogSourceDescriptor s1, LogSourceDescriptor s2) {
			int t1 = s1.getThroughput();
			int t2 = s2.getThroughput();
			return (t1 > t2 ? -1 : (t1 == t2 ? 0 : 1));
		}
	}

	public void printMapping() {
		System.out.println("----------------------------------------");
		Iterator<Entry<String, LogSourceDescriptor>> it = logSources.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, LogSourceDescriptor> entry = it.next();
			LogSourceDescriptor source = entry.getValue();
			HashMap<Integer, Double> mapping = source.getMapping();
			System.out.println(entry.getKey() + " : " + mapping.toString());
		}
		System.out.println("----------------------------------------");
		
	}

	public Collection<LogSourceDescriptor> getAllLogSources() {
		return this.logSources.values();
	}

}
