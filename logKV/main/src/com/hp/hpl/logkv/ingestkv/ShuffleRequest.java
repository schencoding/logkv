package com.hp.hpl.logkv.ingestkv;

import java.io.Serializable;

public class ShuffleRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2628892154470196419L;
	
	
	private long truId = -1;
	
	public ShuffleRequest(long truId){
		this.truId = truId;
	}


	public long getTruId() {
		return truId;
	}
	
	public void setTruId(long truId){
		this.truId = truId;
	}

}
