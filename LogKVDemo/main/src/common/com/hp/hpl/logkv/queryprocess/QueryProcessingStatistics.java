package com.hp.hpl.logkv.queryprocess;

import java.io.Serializable;
import java.util.TreeMap;

public class QueryProcessingStatistics implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4771067019033029820L;
	
	private long runningTime = 0;
	private TreeMap<Long, TRUProcessingStatistics> truStatistics = new TreeMap<Long, TRUProcessingStatistics>();
	private TreeMap<String, IngestKVProcessingStatistics> ingestKVStatistics = new TreeMap<String, IngestKVProcessingStatistics>();

	public long getRunningTime() {
		return runningTime;
	}

	public void setRunningTime(long runningTime) {
		this.runningTime = runningTime;
	}
	
	public void putTruProcessingStatistic(TRUProcessingStatistics statistic){
		truStatistics.put(statistic.truId, statistic);
	}
	
	public TRUProcessingStatistics getTruProcessingStatistic(long truId){
		return truStatistics.get(truId);
	}
	
	public void putIngestKVProcessingStatistics(IngestKVProcessingStatistics statistic){
		this.ingestKVStatistics.put(statistic.getProcessingNodeStr(), statistic);
	}
	
	public IngestKVProcessingStatistics getIngestKVProcessingStatistic(String nodeIpStr){
		return this.ingestKVStatistics.get(nodeIpStr);
	}
	
	
	
	public TreeMap<Long, TRUProcessingStatistics> getTruStatistics() {
		return truStatistics;
	}

	public TreeMap<String, IngestKVProcessingStatistics> getIngestKVStatistics() {
		return ingestKVStatistics;
	}

	@Override
	public String toString() {
		return "QueryProcessingStatistics [runningTime=" + runningTime + ", truStatistics=" + truStatistics + ", ingestKVStatistics=" + ingestKVStatistics
				+ "]";
	}




	public static class IngestKVProcessingStatistics  implements Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = -2837153484183609985L;
		private String processingNodeStr;
		private long numOfReturnedRecords;
		private long runningTime;
		
		
		public IngestKVProcessingStatistics(String processingNodeStr) {
			super();
			this.processingNodeStr = processingNodeStr;
		}


		public String getProcessingNodeStr() {
			return processingNodeStr;
		}


		public void setProcessingNodeStr(String processingNodeStr) {
			this.processingNodeStr = processingNodeStr;
		}


		public long getNumOfReturnedRecords() {
			return numOfReturnedRecords;
		}


		public void setNumOfReturnedRecords(long numOfReturnedRecords) {
			this.numOfReturnedRecords = numOfReturnedRecords;
		}


		public long getRunningTime() {
			return runningTime;
		}


		public void setRunningTime(long runningTime) {
			this.runningTime = runningTime;
		}


		@Override
		public String toString() {
			return "IngestKVProcessingStatistics [processingNodeStr=" + processingNodeStr + ", numOfReturnedRecords=" + numOfReturnedRecords + ", runningTime="
					+ runningTime + "]";
		}
		
		
	}

	public static class TRUProcessingStatistics implements Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = 2136313008910344510L;
		private long truId;
		private String processingNodeStr;
		private long numOfReturnedRecords;
		private long runningTime;
		
		public TRUProcessingStatistics(long truId, String processingNodeStr){
			this.truId = truId;
			this.processingNodeStr = processingNodeStr;
		}
		
		public long getTruId() {
			return truId;
		}
		public void setTruId(long truId) {
			this.truId = truId;
		}
		public String getProcessingNodeStr() {
			return processingNodeStr;
		}
		public void setProcessingNodeStr(String processingNodeStr) {
			this.processingNodeStr = processingNodeStr;
		}
		public long getNumOfReturnedRecords() {
			return numOfReturnedRecords;
		}
		public void setNumOfReturnedRecords(long numOfReturnedRecords) {
			this.numOfReturnedRecords = numOfReturnedRecords;
		}
		public long getRunningTime() {
			return runningTime;
		}
		public void setRunningTime(long runningTime) {
			this.runningTime = runningTime;
		}
		
		@Override
		public String toString() {
			return "TRUProcessingStatistics [truId=" + truId + ", processingNodeStr=" + processingNodeStr + ", numOfReturnedRecords=" + numOfReturnedRecords
					+ ", runningTime=" + runningTime + "]";
		}
		
		
	}

}
