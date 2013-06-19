package com.hp.hpl.logkv.ui;

import java.io.Serializable;





public class Response implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3547417948053014623L;
	
	private long timestamp = -1;
	
	public Response(long timestamp){
		this.timestamp = timestamp;
	}
	
	public long getTimestamp() {
		return timestamp;
	}



	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

}
