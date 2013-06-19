package com.hp.hpl.logkv.ingestkv;

import java.io.Serializable;

public class ShuffleStatus implements Serializable{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 6471929351402890261L;
	private String srcIpStr;
	private String destIpStr;
	private long truId;
	private boolean bStart = true;
	private long size;
	
	public ShuffleStatus(){
		
	}
	
	public ShuffleStatus(String srcIpStr, String destIpStr, long truId, boolean bStart) {
		super();
		this.srcIpStr = srcIpStr;
		this.destIpStr = destIpStr;
		this.truId = truId;
		this.bStart = bStart;
	}


	public String getSrcIpStr() {
		return srcIpStr;
	}


	public void setSrcIpStr(String srcIpStr) {
		this.srcIpStr = srcIpStr;
	}


	public String getDestIpStr() {
		return destIpStr;
	}


	public void setDestIpStr(String destIpStr) {
		this.destIpStr = destIpStr;
	}
	
	


	public long getTruId() {
		return truId;
	}

	public void setTruId(long truId) {
		this.truId = truId;
	}

	public boolean isbStart() {
		return bStart;
	}


	public void setbStart(boolean bStart) {
		this.bStart = bStart;
	}
	
	

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	@Override
	public String toString() {
		return "ShuffleStatus [srcIpStr=" + srcIpStr + ", destIpStr=" + destIpStr + ", truId=" + truId + ", bStart=" + bStart + ", size=" + size + "]";
	}

}
