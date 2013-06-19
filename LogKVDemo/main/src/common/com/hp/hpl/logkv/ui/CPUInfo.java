package com.hp.hpl.logkv.ui;

import java.util.HashMap;

public class CPUInfo {

	private static HashMap<String, CPUInfo> oldCPUInfos = new HashMap<String, CPUInfo>();

	private String node;
	private long timestamp;
	protected double loadPercent;
	private long procUser;
	private long procNice;
	private long procSystem;
	private long procIdle;

	public CPUInfo(String node, long timestamp, long procUser, long procNice, long procSystem, long procIdle) {
		super();
		this.node = node;
		this.timestamp = timestamp;
		this.procUser = procUser;
		this.procNice = procNice;
		this.procSystem = procSystem;
		this.procIdle = procIdle;
	}

	/**
	 * 
	 * // http://ubuntuforums.org/showthread.php?t=148781
	 * 
	 * @param currentCPUInfo
	 * @return
	 */
	public static long calculateCPULoad(CPUInfo currentCPUInfo) {
		CPUInfo oldCPUInfo = oldCPUInfos.get(currentCPUInfo.getNode());
		long loadPercent = -1;
		if (oldCPUInfo != null) {
			long usage = (currentCPUInfo.getProcSystem() - oldCPUInfo.getProcSystem()) + (currentCPUInfo.getProcNice() - oldCPUInfo.getProcNice())
					+ (currentCPUInfo.getProcUser() - oldCPUInfo.getProcUser());
			long total = usage + (currentCPUInfo.getProcIdle() - oldCPUInfo.getProcIdle());
			loadPercent = (100 * usage) / total;
		}
		oldCPUInfos.put(currentCPUInfo.getNode(), currentCPUInfo);
		return loadPercent;
	}

	public String getNode() {
		return node;
	}

	public void setNode(String node) {
		this.node = node;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public double getLoadPercent() {
		return loadPercent;
	}

	public void setLoadPercent(double loadPercent) {
		this.loadPercent = loadPercent;
	}

	public long getProcUser() {
		return procUser;
	}

	public void setProcUser(long procUser) {
		this.procUser = procUser;
	}

	public long getProcNice() {
		return procNice;
	}

	public void setProcNice(long procNice) {
		this.procNice = procNice;
	}

	public long getProcSystem() {
		return procSystem;
	}

	public void setProcSystem(long procSystem) {
		this.procSystem = procSystem;
	}

	public long getProcIdle() {
		return procIdle;
	}

	public void setProcIdle(long procIdle) {
		this.procIdle = procIdle;
	}

	@Override
	public String toString() {
		return "CPUInfo [node=" + node + ", timestamp=" + timestamp + ", loadPercent=" + loadPercent + ", procUser=" + procUser + ", procNice=" + procNice
				+ ", procSystem=" + procSystem + ", procIdle=" + procIdle + "]";
	}
	
	

}
