package com.hp.hpl.logkv.logsource;

import java.io.Serializable;
import java.util.HashMap;


public class LogSourceDescriptor implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7389748990074850350L;
	private String sourceIp;
	private int logSourceId = -1;;
	private boolean bSplitable = false;
	private int throughput = 1;
	private HashMap<Integer, Double> mapping = new HashMap<Integer, Double>();

	public String getSourceIp() {
		return sourceIp;
	}

	public void setSourceIp(String sourceIp) {
		this.sourceIp = sourceIp;
	}

	public boolean isbSplitable() {
		return bSplitable;
	}

	public void setbSplitable(boolean bSplitable) {
		this.bSplitable = bSplitable;
	}

	public int getThroughput() {
		return throughput;
	}

	public void setThroughput(int throughput) {
		this.throughput = throughput;
	}

	public void addMapping(int destinationIngestReceiver, double percentage) {
		mapping.put(destinationIngestReceiver, percentage);
	}

	public void clearMapping() {
		mapping.clear();
	}

	public double getPercentage(String destinationIp) {
		return mapping.get(destinationIp);
	}

	public HashMap<Integer, Double> getMapping() {
		return mapping;
	}

	public int getLogSourceId() {
		return logSourceId;
	}

	public void setLogSourceId(int logSourceId) {
		this.logSourceId = logSourceId;
	}

	@Override
	public String toString() {
		return "\nLogSourceDescriptor [sourceIp=" + sourceIp + ", logSourceId=" + logSourceId + ", bSplitable=" + bSplitable + ", throughput=" + throughput
				+ ", mapping=" + mapping + "]";
	}
	
	

}
