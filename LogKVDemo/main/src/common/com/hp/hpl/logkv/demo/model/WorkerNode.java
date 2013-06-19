package com.hp.hpl.logkv.demo.model;

/**
 * Worker node.
 * @author Edmond
 */
public class WorkerNode extends Node {

	/**
	 * icon path.
	 */
	public static final String ICON_PATH = "/com/hp/hpl/logkv/demo/resource/WorkerNode.png";

	/**
	 * constructor method.
	 */
	public WorkerNode() {
		super();
	}

	/**
	 * constructor method.
	 * @param name	name
	 */
	public WorkerNode(String name) {
		super(name);
	}

	/**
	 * constructor method.
	 * @param id	id
	 */
	public WorkerNode(int id) {
		super(id);
	}

	/**
	 * constructor method.
	 * @param id	id
	 * @param name	name
	 */
	public WorkerNode(int id, String name) {
		super(id, name);
	}

}
