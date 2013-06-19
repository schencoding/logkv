package com.hp.hpl.logkv.demo.model;

/**
 * Node.
 * @author Edmond
 */
public abstract class Node {

	/**
	 * counter for id.
	 */
	private static int counter = 0;

	/**
	 * id.
	 */
	private int id;

	/**
	 * name.
	 */
	private String name;

	/**
	 * constructor method.
	 */
	public Node() {
		this.id = counter++;
	}

	/**
	 * constructor method.
	 * @param name	name
	 */
	public Node(String name) {
		this();
		this.name = name;
	}

	/**
	 * constructor method.
	 * @param id	id
	 */
	public Node(int id) {
		this.id = id;
	}

	/**
	 * constructor method.
	 * @param id	id
	 * @param name	name
	 */
	public Node(int id, String name) {
		this(id);
		this.name = name;
	}

	/**
	 * @param counter the counter to set
	 */
	public static void setCounter(int counter) {
		Node.counter = counter;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		Node other = (Node) obj;
		if (id != other.id) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {

		if (name == null) {
			return "";
		} else {
			return name;
		}
	}

}
