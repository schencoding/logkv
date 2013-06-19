package com.hp.hpl.logkv.ha;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.hp.hpl.logkv.coordinator.CoordinatorServer;
import com.hp.hpl.logkv.coordinator.ICoordinator;
import com.hp.hpl.logkv.ha.ActiveStandbyElector.ActiveStandbyElectorCallback;
import com.hp.hpl.logkv.ha.HealthMonitor.State;
import com.hp.hpl.logkv.util.Util;

public class ZKFailoverController {

	private HealthMonitor healthMonitor;
	private ActiveStandbyElector elector;
	private ICoordinator localCS;
	
	 /** Set if a fatal error occurs */
	  private String fatalError = null;
	  
	  private State lastHealthState = State.INITIALIZING;
	  
	  /**
	   * A future nanotime before which the ZKFC will not join the election.
	   * This is used during graceful failover.
	   */
	  private long delayJoiningUntilNanotime = 0;
	  private ScheduledExecutorService delayExecutor =
			    Executors.newScheduledThreadPool(1,
			        new ThreadFactoryBuilder().setDaemon(true)
			            .setNameFormat("ZKFC Delay timer #%d")
			            .build());
	  
	  private Object activeAttemptRecordLock = new Object();
	  private ActiveAttemptRecord lastActiveAttemptRecord;
	
	protected ZKFailoverController(ICoordinator localCS) {
	    this.localCS = localCS;
	}
	
	public ICoordinator getLocalCS() {
	    return localCS;
	}
	
	private int doRun(String[] args) throws InterruptedException{
		initHM();
		mainLoop();
		return 0;
	}
	
	private void initHM() {
	    healthMonitor = new HealthMonitor(localCS);
	    healthMonitor.addCallback(new HealthCallbacks());
	    healthMonitor.start();
	  }
	
	private synchronized void mainLoop() throws InterruptedException {
	    while (fatalError == null) {
	      wait();
	    }
	    assert fatalError != null; // only get here on fatal
	    throw new RuntimeException(
	        "ZK Failover Controller failed: " + fatalError);
	  }

	public void becomeActive() throws Exception {
		Util.log("Trying to make " + localCS + " active...", this.getClass());
	    try {
	      localCS.becomeActive();
	      String msg = "Successfully transitioned " + localCS +
	          " to active state";
	      Util.log(msg, this.getClass());
	      recordActiveAttempt(new ActiveAttemptRecord(true, msg));

	    } catch (Throwable t) {
	      String msg = "Couldn't make " + localCS + " active";
	      System.err.println(msg + t);
	      
	      recordActiveAttempt(new ActiveAttemptRecord(false, msg + "\n" +
	          t.getMessage()));

	      if (t instanceof Exception) {
	        throw (Exception)t;
	      } else {
	        throw new Exception("Couldn't transition to active",
	            t);
	      }
	/*
	* TODO:
	* we need to make sure that if we get fenced and then quickly restarted,
	* none of these calls will retry across the restart boundary
	* perhaps the solution is that, whenever the nn starts, it gets a unique
	* ID, and when we start becoming active, we record it, and then any future
	* calls use the same ID
	*/
	      
	    }
	  }

	  /**
	   * Store the results of the last attempt to become active.
	   * This is used so that, during manually initiated failover,
	   * we can report back the results of the attempt to become active
	   * to the initiator of the failover.
	   */
	  private void recordActiveAttempt(
	      ActiveAttemptRecord record) {
	    synchronized (activeAttemptRecordLock) {
	      lastActiveAttemptRecord = record;
	      activeAttemptRecordLock.notifyAll();
	    }
	  }

	public void becomeStandby() {

	}

	public void fatalError(String msgStr) {
		System.err.println(msgStr);
	}

	public void fenceOldActive(byte[] data) {
		// TODO Auto-generated method stub

	}
	
	 private byte[] targetToData(ICoordinator localCS2) {
			// TODO Auto-generated method stub
			return null;
		}

	
	
	private synchronized void setLastHealthState(HealthMonitor.State newState) {
		Util.log("Local service " + localCS +
	        " entered state: " + newState, this.getClass());
	    lastHealthState = newState;
	  }
	
	/**
	   * Check the current state of the service, and join the election
	   * if it should be in the election.
	   */
	  private void recheckElectability() {
	    // Maintain lock ordering of elector -> ZKFC
	    synchronized (elector) {
	      synchronized (this) {
	        boolean healthy = lastHealthState == State.SERVICE_HEALTHY;
	    
	        long remainingDelay = delayJoiningUntilNanotime - System.nanoTime(); 
	        if (remainingDelay > 0) {
	          if (healthy) {
	        	  Util.log("Would have joined master election, but this node is " +
	                "prohibited from doing so for " +
	                TimeUnit.NANOSECONDS.toMillis(remainingDelay) + " more ms", this.getClass());
	          }
	          scheduleRecheck(remainingDelay);
	          return;
	        }
	    
	        switch (lastHealthState) {
	        case SERVICE_HEALTHY:
	          elector.joinElection(targetToData(localCS));
	          break;
	          
	        case INITIALIZING:
	        	Util.log("Ensuring that " + localCS + " does not " +
	              "participate in active master election", this.getClass());
	          elector.quitElection(false);
	          break;
	    
	        case SERVICE_UNHEALTHY:
	        case SERVICE_NOT_RESPONDING:
	        	Util.log("Quitting master election for " + localCS +
	              " and marking that fencing is necessary", this.getClass());
	          elector.quitElection(true);
	          break;
	          
	        case HEALTH_MONITOR_FAILED:
	          fatalError("Health monitor failed!");
	          break;
	          
	        default:
	          throw new IllegalArgumentException("Unhandled state:" + lastHealthState);
	        }
	      }
	    }
	  }
	  
	 
	/**
	   * Schedule a call to {@link #recheckElectability()} in the future.
	   */
	  private void scheduleRecheck(long whenNanos) {
	    delayExecutor.schedule(
	        new Runnable() {
	          @Override
	          public void run() {
	            try {
	              recheckElectability();
	            } catch (Throwable t) {
	              t.printStackTrace();
	            	//fatalError("Failed to recheck electability: " +
	               //   StringUtils.stringifyException(t));
	            }
	          }
	        },
	        whenNanos, TimeUnit.NANOSECONDS);
	  }

	/**
	 * Callbacks from elector
	 */
	class ElectorCallbacks implements ActiveStandbyElectorCallback {
		@Override
		public void becomeActive() throws Exception {
			ZKFailoverController.this.becomeActive();
		}

		@Override
		public void becomeStandby() {
			ZKFailoverController.this.becomeStandby();
		}

		@Override
		public void enterNeutralMode() {
		}

		@Override
		public void notifyFatalError(String errorMessage) {
			fatalError(errorMessage);
		}

		@Override
		public void fenceOldActive(byte[] data) {
			ZKFailoverController.this.fenceOldActive(data);
		}

		@Override
		public String toString() {
			synchronized (ZKFailoverController.this) {
				return "Elector callbacks for " + localCS;
			}
		}
	}
	
	/**
	   * Callbacks from HealthMonitor
	   */
	  class HealthCallbacks implements HealthMonitor.Callback {
	    @Override
	    public void enteredState(HealthMonitor.State newState) {
	      setLastHealthState(newState);
	      recheckElectability();
	    }
	  }
	  
	  private static class ActiveAttemptRecord {
		    private final boolean succeeded;
		    private final String status;
		    private final long nanoTime;
		    
		    public ActiveAttemptRecord(boolean succeeded, String status) {
		      this.succeeded = succeeded;
		      this.status = status;
		      this.nanoTime = System.nanoTime();
		    }
		  }

}
