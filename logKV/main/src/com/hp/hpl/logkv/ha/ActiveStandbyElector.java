package com.hp.hpl.logkv.ha;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.KeeperException.Code;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.AsyncCallback.*;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Stat;

import com.hp.hpl.logkv.coordinator.ICoordinator;
import com.hp.hpl.logkv.util.Util;

public class ActiveStandbyElector implements StatCallback, StringCallback {

	protected static final String LOCK_FILENAME = "ActiveStandbyElectorLock";
	protected static final String BREADCRUMB_FILENAME = "ActiveBreadCrumb";

	private static final int SLEEP_AFTER_FAILURE_TO_BECOME_ACTIVE = 1000;

	private ZooKeeper zkClient;
	private String zkHostPort;
	private final int zkSessionTimeout;
	private final List<ACL> zkAcl;
	private byte[] appData;
	private final String zkLockFilePath;
	private final String znodeWorkingDir;
	private final String zkBreadCrumbPath;

	private boolean wantToBeInElection;
	public static int NUM_RETRIES = 3;
	private int createRetryCount = 0;
	private int statRetryCount = 0;
	private State state = State.INIT;
	private ConnectionState zkConnectionState = ConnectionState.TERMINATED;
	private ICoordinator appClient;

	private Lock sessionReestablishLockForTests = new ReentrantLock();

	private static enum ConnectionState {
		DISCONNECTED, CONNECTED, TERMINATED
	};

	static enum State {
		INIT, ACTIVE, STANDBY, NEUTRAL
	};

	public ActiveStandbyElector(String zookeeperHostPorts,
			int zookeeperSessionTimeout, String parentZnodeName, List<ACL> acl, ICoordinator appClient)
			throws Exception {
		if (acl == null || parentZnodeName == null
				|| zookeeperHostPorts == null || zookeeperSessionTimeout <= 0) {
			throw new Exception("null argument");
		}
		zkHostPort = zookeeperHostPorts;
		zkSessionTimeout = zookeeperSessionTimeout;
		zkAcl = acl;
		znodeWorkingDir = parentZnodeName;
		zkLockFilePath = znodeWorkingDir + "/" + LOCK_FILENAME;
		zkBreadCrumbPath = znodeWorkingDir + "/" + BREADCRUMB_FILENAME;
		this.appClient = appClient;

		// createConnection for future API calls
		createConnection();
	}

	private void createConnection() throws IOException {
		if (zkClient != null) {
			try {
				zkClient.close();
			} catch (InterruptedException e) {
				throw new IOException("Interrupted while closing ZK", e);
			}
			zkClient = null;
		}
		zkClient = getNewZooKeeper();
		Util.log("Created new connection for " + this, this.getClass());
	}

	protected synchronized ZooKeeper getNewZooKeeper() throws IOException {
		ZooKeeper zk = new ZooKeeper(zkHostPort, zkSessionTimeout, null);
		zk.register(new WatcherWithClientRef(zk));
		return zk;
	}

	public synchronized void joinElection(byte[] data) {

		if (data == null) {
			System.err.println("data cannot be null");
		}

		appData = new byte[data.length];
		System.arraycopy(data, 0, appData, 0, data.length);

		Util.log("Attempting active election for " + this, this.getClass());
		joinElectionInternal();
	}

	private void joinElectionInternal() {
		if (zkClient == null) {
			if (!reEstablishSession()) {
				System.err
						.println("Failed to reEstablish connection with ZooKeeper");
				return;
			}
		}

		createRetryCount = 0;
		wantToBeInElection = true;
		createLockNodeAsync();
	}

	public synchronized void quitElection(boolean needFence) {
		Util.log("Yielding from election", this.getClass());
		if (!needFence && state == State.ACTIVE) {
			// If active is gracefully going back to standby mode, remove
			// our permanent znode so no one fences us.
			tryDeleteOwnBreadCrumbNode();
		}
		reset();
		wantToBeInElection = false;
	}

	/**
	 * We failed to become active. Re-join the election, but sleep for a few
	 * seconds after terminating our existing session, so that other nodes have
	 * a chance to become active. The failure to become active is already logged
	 * inside becomeActive().
	 */
	private void reJoinElectionAfterFailureToBecomeActive() {
		reJoinElection(SLEEP_AFTER_FAILURE_TO_BECOME_ACTIVE);
	}

	private void reJoinElection(int sleepTime) {
		Util.log("Trying to re-establish ZK session", this.getClass());

		// Some of the test cases rely on expiring the ZK sessions and
		// ensuring that the other node takes over. But, there's a race
		// where the original lease holder could reconnect faster than the other
		// thread manages to take the lock itself. This lock allows the
		// tests to block the reconnection. It's a shame that this leaked
		// into non-test code, but the lock is only acquired here so will never
		// be contended.
		sessionReestablishLockForTests.lock();
		try {
			terminateConnection();
			sleepFor(sleepTime);

			joinElectionInternal();
		} finally {
			sessionReestablishLockForTests.unlock();
		}
	}

	private boolean becomeActive() {
		assert wantToBeInElection;
		if (state == State.ACTIVE) {
			// already active
			return true;
		}
		try {
			Stat oldBreadcrumbStat = fenceOldActive();
			writeBreadCrumbNode(oldBreadcrumbStat);

			Util.log("Becoming active for " + this, this.getClass());
			appClient.becomeActive();
			state = State.ACTIVE;
			return true;
		} catch (Exception e) {
			System.err
					.println("Exception handling the winning of election" + e);
			// Caller will handle quitting and rejoining the election.
			return false;
		}
	}
	
	
	
	
	/**
	   * If there is a breadcrumb node indicating that another node may need
	   * fencing, try to fence that node.
	   * @return the Stat of the breadcrumb node that was read, or null
	   * if no breadcrumb node existed
	   */
	  private Stat fenceOldActive() throws InterruptedException, KeeperException {
	    final Stat stat = new Stat();
	    byte[] data;
	    Util.log("Checking for any old active which needs to be fenced...", this.getClass());
	    try {
	      data = zkDoWithRetries(new ZKAction<byte[]>() {
	        @Override
	        public byte[] run() throws KeeperException, InterruptedException {
	          return zkClient.getData(zkBreadCrumbPath, false, stat);
	        }
	      });
	    } catch (KeeperException ke) {
	      if (isNodeDoesNotExist(ke.code())) {
	    	  Util.log("No old node to fence", this.getClass());
	        return null;
	      }
	      
	      // If we failed to read for any other reason, then likely we lost
	      // our session, or we don't have permissions, etc. In any case,
	      // we probably shouldn't become active, and failing the whole
	      // thing is the best bet.
	      throw ke;
	    }

	    //Util.log("Old node exists: " + StringUtils.byteToHexString(data));
	    Util.log("Old node exists: " + data, this.getClass());
	    if (Arrays.equals(data, appData)) {
	    	Util.log("But old node has our own data, so don't need to fence it.", this.getClass());
	    } else {
	      try {
			appClient.fenceOldActive(data);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    }
	    return stat;
	  }
	  
	  
	  /**
	   * Write the "ActiveBreadCrumb" node, indicating that this node may need
	   * to be fenced on failover.
	   * @param oldBreadcrumbStat 
	   */
	  private void writeBreadCrumbNode(Stat oldBreadcrumbStat)
	      throws KeeperException, InterruptedException {
		  Util.log("Writing znode " + zkBreadCrumbPath +
	        " to indicate that the local node is the most recent active...", this.getClass());
	    if (oldBreadcrumbStat == null) {
	      // No previous active, just create the node
	      createWithRetries(zkBreadCrumbPath, appData, zkAcl,
	        CreateMode.PERSISTENT);
	    } else {
	      // There was a previous active, update the node
	      setDataWithRetries(zkBreadCrumbPath, appData, oldBreadcrumbStat.getVersion());
	    }
	  }
	  
	  private String createWithRetries(final String path, final byte[] data,
		      final List<ACL> acl, final CreateMode mode)
		      throws InterruptedException, KeeperException {
		    return zkDoWithRetries(new ZKAction<String>() {
		      @Override
		      public String run() throws KeeperException, InterruptedException {
		        return zkClient.create(path, data, acl, mode);
		      }
		    });
		  }
	  
	  private byte[] getDataWithRetries(final String path, final boolean watch,
		      final Stat stat) throws InterruptedException, KeeperException {
		    return zkDoWithRetries(new ZKAction<byte[]>() {
		      @Override
		      public byte[] run() throws KeeperException, InterruptedException {
		        return zkClient.getData(path, watch, stat);
		      }
		    });
		  }

		  private Stat setDataWithRetries(final String path, final byte[] data,
		      final int version) throws InterruptedException, KeeperException {
		    return zkDoWithRetries(new ZKAction<Stat>() {
		      @Override
		      public Stat run() throws KeeperException, InterruptedException {
		        return zkClient.setData(path, data, version);
		      }
		    });
		  }
		  

	private void createLockNodeAsync() {
		zkClient.create(zkLockFilePath, appData, zkAcl, CreateMode.EPHEMERAL,
				this, zkClient);
	}

	private boolean reEstablishSession() {
		int connectionRetryCount = 0;
		boolean success = false;
		while (!success && connectionRetryCount < NUM_RETRIES) {
			Util.log("Establishing zookeeper connection for " + this, this.getClass());
			try {
				createConnection();
				success = true;
			} catch (IOException e) {
				System.err.println(e);
				sleepFor(5000);
			}
			++connectionRetryCount;
		}
		return success;
	}

	protected void sleepFor(int sleepMs) {
		if (sleepMs > 0) {
			try {
				Thread.sleep(sleepMs);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
	}

	private void tryDeleteOwnBreadCrumbNode() {
		assert state == State.ACTIVE;
		Util.log("Deleting bread-crumb of active node...", this.getClass());

		// Sanity check the data. This shouldn't be strictly necessary,
		// but better to play it safe.
		Stat stat = new Stat();
		byte[] data = null;
		try {
			data = zkClient.getData(zkBreadCrumbPath, false, stat);

			if (!Arrays.equals(data, appData)) {
				/*
				 * throw new IllegalStateException(
				 * "We thought we were active, but in fact " +
				 * "the active znode had the wrong data: " +
				 * StringUtils.byteToHexString(data) + " (stat=" + stat + ")");
				 */
			}

			deleteWithRetries(zkBreadCrumbPath, stat.getVersion());
		} catch (Exception e) {
			System.err
					.println("Unable to delete our own bread-crumb of being active at "
							+ zkBreadCrumbPath
							+ ": "
							+ e.getLocalizedMessage()
							+ ". "
							+ "Expecting to be fenced by the next active.");
		}
	}

	private void deleteWithRetries(final String path, final int version)
			throws KeeperException, InterruptedException {
		zkDoWithRetries(new ZKAction<Void>() {
			@Override
			public Void run() throws KeeperException, InterruptedException {
				zkClient.delete(path, version);
				return null;
			}
		});
	}

	private void reset() {
		state = State.INIT;
		terminateConnection();
	}

	void terminateConnection() {
		if (zkClient == null) {
			return;
		}
		Util.log("Terminating ZK connection for " + this, this.getClass());
		ZooKeeper tempZk = zkClient;
		zkClient = null;
		try {
			tempZk.close();
		} catch (InterruptedException e) {
			System.err.println(e);
		}
		zkConnectionState = ConnectionState.TERMINATED;
	}

	/**
	 * interface implementation of Zookeeper callback for create
	 */
	@Override
	public void processResult(int rc, String path, Object ctx, String name) {
		// TODO Auto-generated method stub

	}

	/**
	 * interface implementation of Zookeeper callback for monitor (exists)
	 */
	@Override
	public void processResult(int rc, String path, Object ctx, Stat stat) {
		// TODO Auto-generated method stub

	}

	/**
	 * interface implementation of Zookeeper watch events (connection and node),
	 * proxied by {@link WatcherWithClientRef}.
	 */
	public void processWatchEvent(ZooKeeper zk, WatchedEvent event) {
		// TODO Auto-generated method stub

	}

	/**
	 * Watcher implementation which keeps a reference around to the original ZK
	 * connection, and passes it back along with any events.
	 */
	private final class WatcherWithClientRef implements Watcher {
		private final ZooKeeper zk;

		private WatcherWithClientRef(ZooKeeper zk) {
			this.zk = zk;
		}

		@Override
		public void process(WatchedEvent event) {
			try {
				ActiveStandbyElector.this.processWatchEvent(zk, event);
			} catch (Throwable t) {
				t.printStackTrace();
			}
		}
	}

	private static boolean isSuccess(Code code) {
		return (code == Code.OK);
	}

	private static boolean isNodeExists(Code code) {
		return (code == Code.NODEEXISTS);
	}

	private static boolean isNodeDoesNotExist(Code code) {
		return (code == Code.NONODE);
	}

	private static boolean isSessionExpired(Code code) {
		return (code == Code.SESSIONEXPIRED);
	}

	private static boolean shouldRetry(Code code) {
		switch (code) {
		case CONNECTIONLOSS:
		case OPERATIONTIMEOUT:
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "elector id=" + System.identityHashCode(this) + " appData="
				+ ((appData == null) ? "null" : appData);
		// StringUtils.byteToHexString(appData)) + " cb=" + appClient;
	}

	private static <T> T zkDoWithRetries(ZKAction<T> action)
			throws KeeperException, InterruptedException {
		int retry = 0;
		while (true) {
			try {
				return action.run();
			} catch (KeeperException ke) {
				if (shouldRetry(ke.code()) && ++retry < NUM_RETRIES) {
					continue;
				}
				throw ke;
			}
		}
	}

	private interface ZKAction<T> {
		T run() throws KeeperException, InterruptedException;
	}

	public interface ActiveStandbyElectorCallback {

		void becomeActive() throws Exception;

		void becomeStandby();

		void enterNeutralMode();

		void notifyFatalError(String errorMessage);

		void fenceOldActive(byte[] oldActiveData);
	}

}
