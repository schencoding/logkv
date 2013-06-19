package com.hp.hpl.logkv.ui;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;

public class ResourceStatusResponse extends Response {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 7454599353984126183L;

	private HashMap<String, Long> logSizes = new HashMap<String, Long>();
	private HashMap<String, Double> cpuStatuses = new HashMap<String, Double>();
	private HashMap<String, MemoryStatus> memoryStatuses = new HashMap<String, MemoryStatus>();
	private HashMap<String, NetworkStatus> networkStatuses = new HashMap<String, NetworkStatus>();
	private HashMap<String, Long> storageCosts = new HashMap<String, Long>();

	public Iterator<String> getKeyIterator() {
		return logSizes.keySet().iterator();
	}

	public ResourceStatusResponse(long timestamp) {
		super(timestamp);
	}

	public void setLogSize(String ipStr, long size) {
		logSizes.put(ipStr, size);
	}

	public long getLogSize(String ipStr) {
		return logSizes.get(ipStr);
	}

	public void setCPUStatus(String ipStr, double percentage) {
		this.cpuStatuses.put(ipStr, percentage);
	}

	public double getCPUStatus(String ipStr) {
		return cpuStatuses.get(ipStr);
	}

	public void setMemoryStatus(String ipStr, MemoryStatus ms) {
		this.memoryStatuses.put(ipStr, ms);
	}

	public MemoryStatus getMemoryStatus(String ipStr) {
		return this.memoryStatuses.get(ipStr);
	}

	public void setNetworkStatus(String ipStr, NetworkStatus ns) {
		this.networkStatuses.put(ipStr, ns);
	}

	public void setStorageCost(String ipStr, long size) {
		this.storageCosts.put(ipStr, size);
	}

	public long getStorageCost(String ipStr) {
		return this.storageCosts.get(ipStr);
	}

	public NetworkStatus getNetworkStatus(String ipStr) {
		return this.networkStatuses.get(ipStr);
	}

	@Override
	public String toString() {
		return "ResourceStatusResponse [logSizes=" + logSizes + ", cpuStatuses=" + cpuStatuses + ", memoryStatuses=" + memoryStatuses + ", networkStatuses="
				+ networkStatuses + ", storageCosts=" + storageCosts + "]";
	}

	public static class MemoryStatus implements Serializable {
		/**
		 * serialVersionUID
		 */
		private static final long serialVersionUID = 3530817960654855979L;
		private double percentage = 0.0;
		private double size = 0;

		public double getPercentage() {
			return percentage;
		}

		public void setPercentage(double percentage) {
			this.percentage = percentage;
		}

		public double getSize() {
			return size;
		}

		public void setSize(double size) {
			this.size = size;
		}

		@Override
		public String toString() {
			return "MemoryStatus [percentage=" + percentage + ", size=" + size + "]";
		}

	}

	public static class NetworkStatus implements Serializable {
		/**
		 * serialVersionUID
		 */
		private static final long serialVersionUID = 5972670903477180832L;
		private long sizeIn = 0;
		private long sizeOut = 0;

		private static HashMap<String, NetworkStatus> oldNetworkInfos = new HashMap<String, NetworkStatus>();

		public NetworkStatus(long sizeIn, long sizeOut) {
			this.sizeIn = sizeIn;
			this.sizeOut = sizeOut;
		}

		public long getSizeIn() {
			return sizeIn;
		}

		public void setSizeIn(long sizeIn) {
			this.sizeIn = sizeIn;
		}

		public long getSizeOut() {
			return sizeOut;
		}

		public void setSizeOut(long sizeOut) {
			this.sizeOut = sizeOut;
		}

		@Override
		public String toString() {
			return "NetworkStatus [sizeIn=" + sizeIn + ", sizeOut=" + sizeOut + "]";
		}

		public static NetworkStatus getNetworkStatus(String node, NetworkStatus currentNetworkStatus) {
			NetworkStatus oldNetworkStatus = oldNetworkInfos.get(node);
			oldNetworkInfos.put(node, currentNetworkStatus);
			if (oldNetworkStatus != null) {
				long sizeIn1 = currentNetworkStatus.getSizeIn() - oldNetworkStatus.getSizeIn();
				long sizeOut1 = currentNetworkStatus.getSizeOut() - oldNetworkStatus.getSizeOut();
				return new NetworkStatus(sizeIn1, sizeOut1);
			}
			return null;
		}

	}

}
