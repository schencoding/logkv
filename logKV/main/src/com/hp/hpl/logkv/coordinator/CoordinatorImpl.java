package com.hp.hpl.logkv.coordinator;

import java.nio.ByteBuffer;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.security.AccessControlException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import java.util.Vector;

import org.apache.cassandra.dht.BigIntegerToken;
import org.apache.cassandra.dht.RandomPartitioner;
import org.apache.cassandra.thrift.Cassandra;
import org.apache.cassandra.thrift.InvalidRequestException;
import org.apache.cassandra.thrift.TokenRange;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransportException;

import com.hp.hpl.logkv.conf.Parameter;
import com.hp.hpl.logkv.ha.HAServiceStatus;
import com.hp.hpl.logkv.ha.HAServiceStatus.HAServiceState;
import com.hp.hpl.logkv.ha.HealthCheckFailedException;
import com.hp.hpl.logkv.ingestkv.ShuffleStatus;
import com.hp.hpl.logkv.logsource.LogSourceCollection;
import com.hp.hpl.logkv.logsource.LogSourceDescriptor;
import com.hp.hpl.logkv.logsource.LogSourceToWorkerNodeMapper;
import com.hp.hpl.logkv.model.Schema;
import com.hp.hpl.logkv.util.Util;

public class CoordinatorImpl extends UnicastRemoteObject implements ICoordinator {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2026730714586306917L;
	public static List<TokenRangeDescriptor> trds = new ArrayList<TokenRangeDescriptor>();
	private static RandomPartitioner partitioner = new RandomPartitioner();
	public static String serverIp = Parameter.INITIAL_KV_ACCESS;

	private LogSourceToWorkerNodeMapper mapper = new LogSourceToWorkerNodeMapper();

	private transient HAServiceStatus haServiceStatus = new HAServiceStatus(HAServiceState.INITIALIZING);
	private transient boolean bActive = false;
	private transient  TreeMap<String, TreeMap<Long, Integer>> waittingTRUs = new TreeMap<String, TreeMap<Long, Integer>>();
	private transient long mostRecentTRU = -1L;
	private transient TreeMap<Long, Boolean> shuffledTRU = new TreeMap<Long, Boolean>();
	private transient HashMap<String, Long> numOfEvents = new HashMap<String, Long>();
	private transient Vector<ShuffleStatus> shuffleStatuses = new Vector<ShuffleStatus>();
	private transient String[] nodes = Parameter.WORKER_NODES;
	private transient Schema schema = null;
	private transient Random rand = new Random();
	private transient static Long startTRU = -1L;
	int M = 4;
	private transient int remainedTRUsInCurrentRound = 0;
	private transient Vector<Long> ingestedTRUs = new Vector<Long>();

	static {
		
		startTRU = Util.getTruId(System.currentTimeMillis() - 5 * 60 * 1000);
		Util.log("========build token range=======", CoordinatorImpl.class);

		int port = Parameter.KV_STORE_PORT;
		String keyspaceName = Parameter.KV_STORE_SPACENAME;

		TFramedTransport tr = new TFramedTransport(new TSocket(serverIp, port));
		TBinaryProtocol pr = new TBinaryProtocol(tr);
		Cassandra.Client cli = new Cassandra.Client(pr);
		try {
			tr.open();
			cli.set_keyspace(keyspaceName);
		} catch (TTransportException e1) {
			e1.printStackTrace();
		} catch (InvalidRequestException e) {
			e.printStackTrace();
		} catch (TException e) {
			e.printStackTrace();
		}

		try {
			List<TokenRange> list = cli.describe_ring(keyspaceName);
			Iterator<TokenRange> it = list.iterator();
			while (it.hasNext()) {
				TokenRange range = it.next();
				String startTokenStr = range.getStart_token();
				BigIntegerToken startToken = new BigIntegerToken(startTokenStr);
				String endTokenStr = range.getEnd_token();
				BigIntegerToken endToken = new BigIntegerToken(endTokenStr);
				String endPoint = range.getEndpoint_details().get(0).getHost();
				trds.add(new TokenRangeDescriptor(startToken, endToken, endPoint));
			}
			Collections.sort(trds);
			for (int i = 0; i < trds.size(); i++) {
				TokenRangeDescriptor trd = trds.get(i);
				Util.log(trd.toString(), CoordinatorImpl.class);
			}
		} catch (InvalidRequestException e) {
			e.printStackTrace();
		} catch (TException e) {
			e.printStackTrace();
		}

		Util.log("========END build token range=======", CoordinatorImpl.class);
	}

	public CoordinatorImpl() throws RemoteException {
		super();
	}

	@Override
	public String join(String logSrcHost, Integer throughput) throws RemoteException {
		// TODO: cautious, this is temp.
		return nodes[0];
	}

	@Override
	public String[] getDuplicateReceivers(String ingestSvrHost) throws RemoteException {
		return new String[] { nodes[rand.nextInt(nodes.length)] };
	}

	@Override
	public String[] getAllLiveNodes() throws RemoteException {
		return nodes;
	}

	public Schema getSchema() throws RemoteException {
		return schema;
	}

	public void setSchema(Schema schema) throws RemoteException {
		this.schema = schema;
	}
	
	public boolean checkM(){
	
		int count = 0;
		Iterator<Entry<String, TreeMap<Long, Integer>>> ipIt = waittingTRUs.entrySet().iterator();
		while(ipIt.hasNext()){
			Entry<String, TreeMap<Long, Integer>> ipEntry = ipIt.next();
			Iterator<Entry<Long, Integer>> truIt = ipEntry.getValue().entrySet().iterator();
			while(truIt.hasNext()){
				Entry<Long, Integer> truEntry = truIt.next();
				if(truEntry.getValue() >= this.nodes.length){
					count ++;
					if(count >= M){
						remainedTRUsInCurrentRound = M;
						return true;
					}
				}
			}
			
		}
		return false;
	}

	@Override
	public synchronized Long getNextTRU(String ipStr) throws RemoteException {
		
		System.out.println("getTRU " + ipStr);
		if(remainedTRUsInCurrentRound > 0){
			
		}else{
			if(!checkM()){
				return null;
			}
		}
		TreeMap<Long, Integer> waittingTRUs4ThisIP = waittingTRUs.get(ipStr);

		if (waittingTRUs4ThisIP != null) {
			
				Long retTruId = new Long(-1);
				Iterator<Entry<Long, Integer>> it = waittingTRUs4ThisIP.entrySet().iterator();
				while (it.hasNext()) {
					Entry<Long, Integer> entry = it.next();
					Long truId = entry.getKey();
					Integer value = entry.getValue();
					if (value == nodes.length) {
						retTruId = truId;
						System.out.println(value + " " + nodes.length);
						System.out.println(Arrays.toString(waittingTRUs4ThisIP.entrySet().toArray()));
						break;
					}
				}
				if (retTruId != -1) {
					waittingTRUs4ThisIP.remove(retTruId);
					System.out.println("getTRU return: " + retTruId + " " + ipStr);
					remainedTRUsInCurrentRound --;
					return retTruId;
				}
			}


		return null;
	}
	
	public synchronized Long[] getIngestedTRUs() throws RemoteException{
		Vector<Long> newIngestedTrus = new Vector<Long>();
		Long[] ret = this.ingestedTRUs.toArray(new Long[]{});
		this.ingestedTRUs = newIngestedTrus;
		return ret;
	}

	@Override
	public synchronized Boolean addTRU(String senderIpStr, Long truId) throws RemoteException {
		System.out.println("addTRU " + senderIpStr + "   " + truId);
		
		ingestedTRUs.add(truId);

		mostRecentTRU = truId.longValue();
		String receiverIpStr = getReceiverNodeIPStr(truId);
		TreeMap<Long, Integer> waittingTRUs4ThisIP = waittingTRUs.get(receiverIpStr);
		if (waittingTRUs4ThisIP == null) {
			waittingTRUs4ThisIP = new TreeMap<Long, Integer>();
			waittingTRUs4ThisIP.put(truId, 1);
			waittingTRUs.put(receiverIpStr, waittingTRUs4ThisIP);
		} else {
			Integer count = waittingTRUs4ThisIP.get(truId);
			if (count == null) {
				waittingTRUs4ThisIP.put(truId, 1);
			} else {
				count = count + 1;
				waittingTRUs4ThisIP.put(truId, count);
			}
		}

		return true;
	}

	@Override
	public synchronized Boolean notifyShuffleFinished(Long truId) throws RemoteException {
		this.shuffledTRU.put(truId, Boolean.TRUE);
		return true;
	}

	@Override
	public Boolean checkIfShuffled(Long truId) throws RemoteException {
		Boolean b = this.shuffledTRU.get(truId);
		if (b != null) {
			return true;
		}
		return false;
	}

	public String getReceiverNodeIPStr(Long truId) throws RemoteException {
		return CoordinatorImpl.getEndPoint(truId.longValue());
	}

	public static String getEndPoint(long truId) {
		BigIntegerToken token = partitioner.getToken(ByteBuffer.wrap(Long.toString(truId).getBytes()));
		String endPoint = "";

		Iterator<TokenRangeDescriptor> it = trds.iterator();
		while (it.hasNext()) {
			TokenRangeDescriptor trd = it.next();
			if (trd.getStartToken().compareTo(trd.getEndToken()) < 0) {
				if (trd.getStartToken().compareTo(token) <= 0 && trd.getEndToken().compareTo(token) >= 0) {
					endPoint = trd.getEndPoint();
				}
			} else if (trd.getStartToken().compareTo(trd.getEndToken()) > 0) {
				if (trd.getStartToken().compareTo(token) <= 0 || trd.getEndToken().compareTo(token) >= 0) {
					endPoint = trd.getEndPoint();
				}
			} else {
				endPoint = trd.getEndPoint();
				Util.log("TRU " + truId + " is assigned to " + endPoint, CoordinatorImpl.class);
				return endPoint;
			}
		}
		Util.log("TRU " + truId + " is assigned to " + endPoint, CoordinatorImpl.class);

		return endPoint;
	}

	@Override
	public HAServiceStatus getServiceStatus() throws RemoteException, AccessControlException {
		return haServiceStatus;
	}

	@Override
	public void monitorHealth() throws RemoteException, HealthCheckFailedException {
		// TODO Auto-generated method stub

	}

	@Override
	public Boolean becomeActive() throws RemoteException {
		bActive = true;
		return true;
	}

	@Override
	public Boolean becomeStandby() throws RemoteException {
		this.bActive = false;
		return true;
	}

	@Override
	public void setServiceStatus(HAServiceStatus status) throws RemoteException, AccessControlException {
		this.haServiceStatus = status;
	}

	@Override
	public void fenceOldActive(byte[] data) throws RemoteException {
		// TODO Auto-generated method stub

	}

	public LogSourceDescriptor addLogSource(LogSourceDescriptor logSource) throws RemoteException {
		mapper.addLogSource(logSource);
		return logSource;
	}

	public LogSourceCollection getAllLogSources() throws RemoteException {
		return new LogSourceCollection(mapper.getAllLogSources());
	}

	public synchronized void addNumOfEvents(String node, long num) throws RemoteException {
		Long l = numOfEvents.get(node);
		if (l != null) {
			l += num;
			numOfEvents.put(node, l);
		} else {
			numOfEvents.put(node, num);
		}
	}

	public long getNumOfEvents(String node) throws RemoteException {
		Long l = numOfEvents.get(node);
		if (l != null) {
			return l;
		}
		return 0;
	}

	@Override
	public void addShuffleStatus(ShuffleStatus ss) throws RemoteException {
		shuffleStatuses.add(ss);
		System.out.println("ShuffleStatus added: " + ss);
	}

	@Override
	public ShuffleStatus[] getShuffleStatuses() throws RemoteException {
		
		Vector<ShuffleStatus> old = this.shuffleStatuses;
		shuffleStatuses = new Vector<ShuffleStatus>();
		int size = old.size();
		ShuffleStatus[] statuses = new ShuffleStatus[size];
		for (int i = 0; i < size; i++) {
			statuses[i] = old.get(i);
		}
		System.out.println("ShuffleStatus: " + Arrays.toString(statuses));
		return statuses;
	}
	
	public Long getStartupTRU() throws RemoteException{
		return startTRU;
	}

}
