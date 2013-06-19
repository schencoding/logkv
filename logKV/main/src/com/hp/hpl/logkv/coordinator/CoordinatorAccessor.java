package com.hp.hpl.logkv.coordinator;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import com.hp.hpl.logkv.conf.Parameter;
import com.hp.hpl.logkv.ingestkv.ShuffleStatus;
import com.hp.hpl.logkv.logsource.LogSourceCollection;
import com.hp.hpl.logkv.logsource.LogSourceDescriptor;
import com.hp.hpl.logkv.model.Schema;

public class CoordinatorAccessor {
	private String[] coordinatorIPs = Parameter.COORDINATOR_IPs;

	private ICoordinator coordinator = null;
	private int currentCoordinatorId = 0;

	public CoordinatorAccessor() {
		if (coordinatorIPs.length > 0) {
			String coordinatorURL = "rmi://" + coordinatorIPs[0] + ":"
					+ Parameter.COORDINATOR_PORT + "/CoordinatorServer";
			currentCoordinatorId = 0;
			try {
				coordinator = (ICoordinator) Naming.lookup(coordinatorURL);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (NotBoundException e) {
				e.printStackTrace();
			}
		}
	}

	public String join(String logSrcHost, Integer throughput)
			throws RemoteException {
		String res = null;
		boolean bSuccess = false;
		/**
		 * access current coordinator
		 */
		int retry = 0;
		do {
			try {
				coordinator.join(logSrcHost, throughput);
				bSuccess = true;
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		} while (!bSuccess && retry < 3);
		/**
		 * try to access other coordinator(s)
		 */
		if (!bSuccess) {
			if (connectOthers()) {
				retry = 0;
				do {
					try {
						coordinator.join(logSrcHost, throughput);
						bSuccess = true;
						break;
					} catch (RemoteException e) {
						e.printStackTrace();
					}
				} while (!bSuccess && retry < 3);
			}

		}
		return res;
	}
	
	public Long getNextTRUGroup(String ipStr){
		try {
			return coordinator.getNextTRU(ipStr);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean addTRUGroup(String senderIpStr, Long truId){
		try {
			return coordinator.addTRU(senderIpStr, truId);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public String[] getAllLiveNodes() throws RemoteException{
		return coordinator.getAllLiveNodes();
	}

	public String[] getDuplicateReceivers(String ingestSvrHost) {
		String[] res = null;
		boolean bSuccess = false;
		/**
		 * access current coordinator
		 */
		int retry = 0;
		do {
			try {
				res = coordinator.getDuplicateReceivers(ingestSvrHost);
				bSuccess = true;
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		} while (!bSuccess && retry < 3);

		/**
		 * try to access other coordinator(s)
		 */
		if (!bSuccess) {
			if (connectOthers()) {
				retry = 0;
				do {
					try {
						res = coordinator.getDuplicateReceivers(ingestSvrHost);
						bSuccess = true;
						break;
					} catch (RemoteException e) {
						e.printStackTrace();
					}
				} while (!bSuccess && retry < 3);
			}

		}
		return res;
	}

	public boolean connectOthers() {
		for (int i = 0; i < coordinatorIPs.length; i++) {
			if (i != currentCoordinatorId) {
				String coordinatorURL = "rmi://" + coordinatorIPs[i] + ":"
						+ Parameter.COORDINATOR_PORT + "/CoordinatorServer";
				try {
					coordinator = (ICoordinator) Naming.lookup(coordinatorURL);
					currentCoordinatorId = i;
					return true;
				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (RemoteException e) {
					e.printStackTrace();
				} catch (NotBoundException e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}

	public void addTRU(String senderIpStr, long truId) {
		try {
			coordinator.addTRU(senderIpStr, truId);
		} catch (RemoteException e) {
			e.printStackTrace();
		}		
	}
	

	public Long getNextTRU(String ipStr) throws RemoteException {
		return coordinator.getNextTRU(ipStr);
	}
	
	public Boolean notifyShuffleFinished(Long truId) throws RemoteException {
		return coordinator.notifyShuffleFinished(truId);
	}
	
	
	public Boolean checkIfShuffled(Long truId) throws RemoteException {
		return coordinator.checkIfShuffled(truId);
	}
	
	public LogSourceDescriptor addLogSource(LogSourceDescriptor logSource) throws RemoteException{
		return coordinator.addLogSource(logSource);
	}
	
	public LogSourceCollection getAllLogSources() throws RemoteException{
		return coordinator.getAllLogSources();
	}
	
	public void addNumOfEvents(String node, long num) throws RemoteException{
		coordinator.addNumOfEvents(node, num);
	}
	
	public void addShuffleStatus(ShuffleStatus ss) throws RemoteException{
		coordinator.addShuffleStatus(ss);
	}
	
	public ShuffleStatus[] getShuffleStatuses() throws RemoteException{
		return coordinator.getShuffleStatuses();
	}

	public long getNumOfEvents(String node) throws RemoteException{
		return coordinator.getNumOfEvents(node);
	}
	
	public void setSchema(Schema schema) throws RemoteException{
		coordinator.setSchema(schema);
	}
	
	public Schema getSchema() throws RemoteException{
		return coordinator.getSchema();
	}
	
	public long getStartupTRU() throws RemoteException{
		return coordinator.getStartupTRU();
	}
	
	public synchronized Long[] getIngestedTRUs() throws RemoteException{
		return coordinator.getIngestedTRUs();
	}

}
