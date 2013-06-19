package com.hp.hpl.logkv.ui;

import com.hp.hpl.logkv.logsource.LogSourceCollection;

public class AddLogSourceResponse extends Response {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6243825979556881234L;
	private LogSourceCollection logSources ;
	private String[] nodes = null;
	
	public AddLogSourceResponse(long timestamp, LogSourceCollection logSources, String[] nodes){
		super(timestamp);
		this.logSources = logSources;
		this.nodes = nodes;
	}
	

	public LogSourceCollection getLogSources() {
		return logSources;
	}

	public void setLogSources(LogSourceCollection logSources) {
		this.logSources = logSources;
	}


	public String[] getNodes() {
		return nodes;
	}


	public void setNodes(String[] nodes) {
		this.nodes = nodes;
	}		
	

}
