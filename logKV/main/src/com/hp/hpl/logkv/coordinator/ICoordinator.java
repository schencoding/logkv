package com.hp.hpl.logkv.coordinator;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.security.AccessControlException;

import com.hp.hpl.logkv.ha.HAServiceStatus;
import com.hp.hpl.logkv.ha.HealthCheckFailedException;
import com.hp.hpl.logkv.ingestkv.ShuffleStatus;
import com.hp.hpl.logkv.logsource.LogSourceCollection;
import com.hp.hpl.logkv.logsource.LogSourceDescriptor;
import com.hp.hpl.logkv.model.Schema;

public interface ICoordinator extends Remote{
	
	public String join(String logSrcHost, Integer throughput) throws RemoteException;	
	public String[] getDuplicateReceivers(String ingestSvrHost) throws RemoteException;
	public Long getNextTRU(String ipStr) throws RemoteException;
	public Boolean notifyShuffleFinished(Long truId) throws RemoteException;
	public Boolean checkIfShuffled(Long truId) throws RemoteException;
	public LogSourceDescriptor addLogSource(LogSourceDescriptor logSource) throws RemoteException;
	public LogSourceCollection getAllLogSources() throws RemoteException;
	
	public void setServiceStatus(HAServiceStatus status) throws AccessControlException, RemoteException;
	public HAServiceStatus getServiceStatus() throws AccessControlException, RemoteException;
	public void monitorHealth() throws HealthCheckFailedException, RemoteException;
	public Boolean becomeActive() throws RemoteException;
	public Boolean becomeStandby() throws RemoteException;
	public void fenceOldActive(byte[] data) throws RemoteException;
	public String[] getAllLiveNodes()throws RemoteException;
	public void addNumOfEvents(String node, long num) throws RemoteException;
	public void addShuffleStatus(ShuffleStatus ss) throws RemoteException;
	public ShuffleStatus[] getShuffleStatuses() throws RemoteException;
	public long getNumOfEvents(String node)throws RemoteException;
	public Schema getSchema() throws RemoteException;
	public void setSchema(Schema schema) throws RemoteException;
	public Boolean addTRU(String senderIpStr, Long truId) throws RemoteException;
	
	public Long getStartupTRU() throws RemoteException;
	public Long[] getIngestedTRUs() throws RemoteException;

}
