package com.hp.hpl.logkv.ui;


public class ShuffleStatusRequest extends Request {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -7357015536563527420L;
	
	private boolean bAllNodes = true;
	private String specificIpStr;
	
	public ShuffleStatusRequest(boolean b, String specificIpStr){
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
		return "ShuffleStatusRequest [bAllNodes=" + bAllNodes + ", specificIpStr=" + specificIpStr + "]";
	}
	
}
