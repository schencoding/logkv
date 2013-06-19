package com.hp.hpl.logkv.ui;

public class ResourceStatusRequest extends Request {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3597703887381568665L;
	
	private boolean bAllNodes = true;
	private String specificIpStr;
	
	public ResourceStatusRequest(boolean b, String specificIpStr){
		bAllNodes = b;
		this.specificIpStr = specificIpStr;
	}

	public boolean isbAllNodes() {
		return bAllNodes;
	}

	public void setbAllNodes(boolean bAllNodes) {
		this.bAllNodes = bAllNodes;
	}

	public String getSpecificIpStr() {
		return specificIpStr;
	}

	public void setSpecificIpStr(String specificIpStr) {
		this.specificIpStr = specificIpStr;
	}

	@Override
	public String toString() {
		return "ResourceStatusRequest [bAllNodes=" + bAllNodes + ", specificIpStr=" + specificIpStr + "]";
	}
	
	

}
