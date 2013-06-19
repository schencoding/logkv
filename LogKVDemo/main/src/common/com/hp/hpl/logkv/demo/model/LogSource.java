package com.hp.hpl.logkv.demo.model;

import com.hp.hpl.logkv.logsource.LogSourceDescriptor;


/**
 * log source.
 * @author Edmond
 */
public class LogSource extends Node {

	/**
	 * icon path.
	 */
	public static final String ICON_PATH = "/com/hp/hpl/logkv/demo/resource/LogSource.png";

	/**
	 * log source descriptor.
	 */
	public LogSourceDescriptor logSourceDescriptor;

	/**
	 * constructor method.
	 */
	public LogSource() {
		super();
	}

	/**
	 * constructor method.
	 * @param name	name
	 */
	public LogSource(String name) {
		super(name);
	}

	/**
	 * constructor method.
	 * @param logSourceDescriptor	log source descriptor
	 */
	public LogSource(LogSourceDescriptor logSourceDescriptor) {
		super(logSourceDescriptor.getLogSourceId(), "" + logSourceDescriptor.getLogSourceId());
	}

	/**
	 * constructor method.
	 * @param id	id
	 */
	public LogSource(int id) {
		super(id);
	}

	/**
	 * constructor method.
	 * @param id	id
	 * @param name	name
	 */
	public LogSource(int id, String name) {
		super(id, name);
	}

}
