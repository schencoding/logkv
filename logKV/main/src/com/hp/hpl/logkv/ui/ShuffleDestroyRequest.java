package com.hp.hpl.logkv.ui;

public class ShuffleDestroyRequest extends Request {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4944442210708213474L;
	
	private String requestID = null;

	public String getRequestID() {
		return requestID;
	}

	public void setRequestID(String requestID) {
		this.requestID = requestID;
	}	

}
