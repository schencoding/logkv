package com.hp.hpl.logkv.demo.model;


/**
 * Worker node and Worker node edge.
 * @author Edmond
 */
public class WorkerNodeWorkerNodeEdge extends LogKVEdge {

	/**
	 * worker node 1.
	 */
	private WorkerNode workerNode1;

	/**
	 * worker node 2.
	 */
	private WorkerNode workerNode2;

	/**
	 * time range unit id.
	 */
	private long truId;

	/**
	 * size.
	 */
	private long size;

	/**
	 * constructor method.
	 * @param workerNode1	worker node 1
	 * @param workerNode2	worker node 1
	 * @param truId			time range unit Id
	 * @param size			size
	 */
	public WorkerNodeWorkerNodeEdge(WorkerNode workerNode1, WorkerNode workerNode2, long truId, long size) {
		this.workerNode1 = workerNode1;
		this.workerNode2 = workerNode2;
		this.truId = truId;
		this.size = size;
	}

	/**
	 * @return the workerNode1
	 */
	public WorkerNode getWorkerNode1() {
		return workerNode1;
	}

	/**
	 * @return the workerNode2
	 */
	public WorkerNode getWorkerNode2() {
		return workerNode2;
	}

	/**
	 * @return the truId
	 */
	public long getTruId() {
		return truId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((workerNode1 == null) ? 0 : workerNode1.hashCode());
		result = prime * result
				+ ((workerNode2 == null) ? 0 : workerNode2.hashCode());
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
		WorkerNodeWorkerNodeEdge other = (WorkerNodeWorkerNodeEdge) obj;
		if (workerNode1 == null) {
			if (other.workerNode1 != null) {
				return false;
			}
		} else if (!workerNode1.equals(other.workerNode1)) {
			return false;
		}
		if (workerNode2 == null) {
			if (other.workerNode2 != null) {
				return false;
			}
		} else if (!workerNode2.equals(other.workerNode2)) {
			return false;
		}

		return true;
	}

	@Override
	public String toString() {
		return "TRU-" + this.truId + " [" + size + "]";
	}

}
