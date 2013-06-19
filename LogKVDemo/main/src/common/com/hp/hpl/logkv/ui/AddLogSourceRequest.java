package com.hp.hpl.logkv.ui;

public class AddLogSourceRequest extends Request {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6898688242939804787L;
	
	private String sourceIp;
	private int logSourceId = 0;;
	private boolean bSplitable = false;
	private int throughput = 1;
	
	public AddLogSourceRequest(String sourceIp, int logSourceId, boolean bSplitable, int throughput){
		this.sourceIp = sourceIp;
		this.logSourceId = logSourceId;
		this.bSplitable = bSplitable;
		this.throughput = throughput;
		
	}

	public String getSourceIp() {
		return sourceIp;
	}

	public void setSourceIp(String sourceIp) {
		this.sourceIp = sourceIp;
	}

	public int getLogSourceId() {
		return logSourceId;
	}

	public void setLogSourceId(int logSourceId) {
		this.logSourceId = logSourceId;
	}

	public boolean isbSplitable() {
		return bSplitable;
	}

	public void setbSplitable(boolean bSplitable) {
		this.bSplitable = bSplitable;
	}

	public int getThroughput() {
		return throughput;
	}

	public void setThroughput(int throughput) {
		this.throughput = throughput;
	}

	@Override
	public String toString() {
		return "AddLogSourceRequest [sourceIp=" + sourceIp + ", logSourceId=" + logSourceId + ", bSplitable=" + bSplitable + ", throughput=" + throughput + "]";
	}
	
	

}
