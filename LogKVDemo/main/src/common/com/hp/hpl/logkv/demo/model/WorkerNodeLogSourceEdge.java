package com.hp.hpl.logkv.demo.model;

import com.hp.hpl.logkv.demo.util.NumberUtil;

/**
 * Worker node and log server edge.
 * @author Edmond
 */
public class WorkerNodeLogSourceEdge extends LogKVEdge {

	/**
	 * worker node.
	 */
	private WorkerNode workerNode;

	/**
	 * log source.
	 */
	private LogSource logSource;

	/**
	 * usage percent.
	 */
	private double usagePercent;

	/**
	 * constructor method.
	 * @param workerNode	worker node
	 * @param logSource		log source
	 * @param usagePercent	usage percent
	 */
	public WorkerNodeLogSourceEdge(WorkerNode workerNode, LogSource logSource, double usagePercent) {
		this.workerNode = workerNode;
		this.logSource = logSource;
		this.usagePercent = usagePercent;
	}

	/**
	 * @return the workerNode
	 */
	public WorkerNode getWorkerNode() {
		return workerNode;
	}

	/**
	 * @return the logSource
	 */
	public LogSource getLogSource() {
		return logSource;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((logSource == null) ? 0 : logSource.hashCode());
		result = prime * result
				+ ((workerNode == null) ? 0 : workerNode.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		WorkerNodeLogSourceEdge other = (WorkerNodeLogSourceEdge) obj;
		if (logSource == null) {
			if (other.logSource != null) {
				return false;
			}
		} else if (!logSource.equals(other.logSource)) {
			return false;
		}
		if (workerNode == null) {
			if (other.workerNode != null) {
				return false;
			}
		} else if (!workerNode.equals(other.workerNode)) {
			return false;
		}
		return true;

	}

	@Override
	public String toString() {
		return NumberUtil.getNumberFormatDigitWithTwoFractionDigits(this.usagePercent) + "%";
	}

}
