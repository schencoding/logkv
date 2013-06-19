package com.hp.hpl.logkv.ha;



public class HAServiceStatus {
	public enum HAServiceState {
	    INITIALIZING("initializing"),
	    ACTIVE("active"),
	    STANDBY("standby");

	    private String name;

	    HAServiceState(String name) {
	      this.name = name;
	    }

	    @Override
	    public String toString() {
	      return name;
	    }
	  }
	
  private HAServiceState state;
  private boolean readyToBecomeActive;
  private String notReadyReason;
  
  public HAServiceStatus(HAServiceState state) {
    this.state = state;
  }

  public HAServiceState getState() {
    return state;
  }

  public HAServiceStatus setReadyToBecomeActive() {
    this.readyToBecomeActive = true;
    this.notReadyReason = null;
    return this;
  }
  
  public HAServiceStatus setNotReadyToBecomeActive(String reason) {
    this.readyToBecomeActive = false;
    this.notReadyReason = reason;
    return this;
  }

  public boolean isReadyToBecomeActive() {
    return readyToBecomeActive;
  }

  public String getNotReadyReason() {
    return notReadyReason;
  }
}
