package com.hp.hpl.logkv.ha;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.hp.hpl.logkv.coordinator.CoordinatorServer;
import com.hp.hpl.logkv.coordinator.ICoordinator;
import com.hp.hpl.logkv.ha.HAServiceStatus.HAServiceState;
import com.hp.hpl.logkv.ha.ZKFailoverController.HealthCallbacks;
import com.hp.hpl.logkv.util.Util;

public class HealthMonitor {

	/** The HA service to monitor */
	private final ICoordinator targetToMonitor;

	private Daemon daemon;
	private long checkIntervalMillis = 1 * 1000;
	private long sleepAfterDisconnectMillis = 2 * 1000;

	private volatile boolean shouldRun = true;
	private State state = State.INITIALIZING;

	/**
	 * Listeners for state changes
	 */
	private List<Callback> callbacks = Collections
			.synchronizedList(new LinkedList<Callback>());

	private HAServiceStatus lastServiceState = new HAServiceStatus(
			HAServiceState.INITIALIZING);

	public enum State {
		/**
		 * The health monitor is still starting up.
		 */
		INITIALIZING,

		/**
		 * The service is not responding to health check RPCs.
		 */
		SERVICE_NOT_RESPONDING,

		/**
		 * The service is connected and healthy.
		 */
		SERVICE_HEALTHY,

		/**
		 * The service is running but unhealthy.
		 */
		SERVICE_UNHEALTHY,

		/**
		 * The health monitor itself failed unrecoverably and can no longer
		 * provide accurate information.
		 */
		HEALTH_MONITOR_FAILED;
	}

	public HealthMonitor(ICoordinator cs) {
		this.targetToMonitor = cs;
	}

	private void doHealthChecks() throws InterruptedException {
		while (shouldRun) {
			HAServiceStatus status = null;
			boolean healthy = false;
			try {
				status = targetToMonitor.getServiceStatus();
				targetToMonitor.monitorHealth();
				healthy = true;
			} catch (HealthCheckFailedException e) {
				System.err.println("Service health check failed for "
						+ targetToMonitor + ": " + e.getMessage());
				enterState(State.SERVICE_UNHEALTHY);
			} catch (Throwable t) {
				System.err
						.println("Transport-level exception trying to monitor health of "
								+ targetToMonitor
								+ ": "
								+ t.getLocalizedMessage());

				enterState(State.SERVICE_NOT_RESPONDING);
				Thread.sleep(sleepAfterDisconnectMillis);
				return;
			}

			if (status != null) {
				setLastServiceStatus(status);
			}
			if (healthy) {
				enterState(State.SERVICE_HEALTHY);
			}

			Thread.sleep(checkIntervalMillis);
		}
	}

	private synchronized void setLastServiceStatus(HAServiceStatus status) {
		this.lastServiceState = status;
	}

	private synchronized void enterState(State newState) {
		if (newState != state) {
			Util.log("Entering state " + newState, this.getClass());
			state = newState;
			synchronized (callbacks) {
				for (Callback cb : callbacks) {
					cb.enteredState(newState);
				}
			}
		}
	}

	synchronized State getHealthState() {
		return state;
	}

	synchronized HAServiceStatus getLastServiceStatus() {
		return lastServiceState;
	}

	boolean isAlive() {
		return daemon.isAlive();
	}

	void join() throws InterruptedException {
		daemon.join();
	}

	void start() {
		daemon.start();
	}

	private class MonitorDaemon extends Daemon {
		private MonitorDaemon() {
			super();
			setName("Health Monitor for " + targetToMonitor);
			setUncaughtExceptionHandler(new UncaughtExceptionHandler() {
				@Override
				public void uncaughtException(Thread t, Throwable e) {
					System.err.println("Health monitor failed" + e);
					enterState(HealthMonitor.State.HEALTH_MONITOR_FAILED);
				}
			});
		}

		@Override
		public void run() {
			while (shouldRun) {
				try {
					doHealthChecks();
				} catch (InterruptedException ie) {
					ie.printStackTrace();
					// Preconditions.checkState(!shouldRun,
					// "Interrupted but still supposed to run");
				}
			}
		}
	}

	/**
	 * Callback interface for state change events.
	 * 
	 * This interface is called from a single thread which also performs the
	 * health monitoring. If the callback processing takes a long time, no
	 * further health checks will be made during this period, nor will other
	 * registered callbacks be called.
	 * 
	 * If the callback itself throws an unchecked exception, no other callbacks
	 * following it will be called, and the health monitor will terminate,
	 * entering HEALTH_MONITOR_FAILED state.
	 */
	static interface Callback {
		void enteredState(State newState);
	}

	public void addCallback(Callback cb) {
		this.callbacks.add(cb);
	  }
	  
	  public void removeCallback(Callback cb) {
	    callbacks.remove(cb);
	  }
	  
}
