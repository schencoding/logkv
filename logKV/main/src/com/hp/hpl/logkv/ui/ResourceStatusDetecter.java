package com.hp.hpl.logkv.ui;

import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import com.hp.hpl.logkv.conf.Parameter;
import com.hp.hpl.logkv.coordinator.CoordinatorAccessor;
import com.hp.hpl.logkv.demo.jms.JMSSender;
import com.hp.hpl.logkv.util.Util;
import com.jcraft.jsch.JSchException;

public class ResourceStatusDetecter implements Runnable {

	private static final String RESOURCE_COMMAND = "date +\"date %m-%d-%y %r\"; grep cpu /proc/stat; grep \"MemTotal\\|MemFree\\|Buffers\\|Cached\\|SwapTotal\\|SwapFree\" /proc/meminfo; grep \":\" /proc/net/dev  | sed  's/^/Interface /;s/:/ /'";
	private static final String STORAGE_COMMAND = "du -b -s " + Parameter.KV_STORE_DB_DIR; // workspace/logKV/data

	private static HashMap<String, SSHSession> sessions = new HashMap<String, SSHSession>();
	private static CoordinatorAccessor coordinatorAccessor = new CoordinatorAccessor();
	private static String[] nodes = null;
	private static HashMap<String, Boolean> bFirstDetects = new HashMap<String, Boolean>();

	static {
		try {
			nodes = coordinatorAccessor.getAllLiveNodes();
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}

		SSHOptions sshOptions = new SSHOptions();
		for (int i = 0; i < nodes.length; i++) {
			String node = nodes[i];
			SSHUserInfo sshUserInfo = new SSHUserInfo(node, Parameter.SSH_USER_NAME, Parameter.SSH_PASSWORD);
			SSHSession sshSession = new SSHSession();
			try {
				sshSession.login(sshUserInfo, sshOptions);
			} catch (JSchException e) {
				e.printStackTrace();
			}
			sessions.put(node, sshSession);
		}
	}

	private ResourceStatusRequest rsr = null;
	private static JMSSender sender = null;
	private String requestID = null;

	public ResourceStatusDetecter(ResourceStatusRequest rsr) {
		this.rsr = rsr;
	}

	@Override
	public void run() {
		if (rsr.isbAllNodes()) {
			ResourceStatusResponse response = new ResourceStatusResponse(System.currentTimeMillis());
			for (int i = 0; i < nodes.length; i++) {
				String node = nodes[i];
				getResourceStatus(node, response);
			}
			System.out.println("Send out message: " + response);
			sender.send(requestID, response);
		} else {
			String specificIpStr = rsr.getSpecificIpStr();
			ResourceStatusResponse response = new ResourceStatusResponse(System.currentTimeMillis());
			if (specificIpStr != null) {
				getResourceStatus(specificIpStr, response);
			}
			System.out.println("Send out message: " + response);
			sender.send(requestID, response);
		}
	}

	private static long getStorageSize(ArrayList<String> strs) throws Exception {
		for (String str : strs) {
			String[] tokens = parseString(str);
			//if (tokens[1].compareToIgnoreCase("total") == 0) {
				return Long.parseLong(tokens[0]);
			//}
		}
		return -1;
	}

	private static CPUInfo getCPUInfo(String node, ArrayList<String> strs) throws Exception {
		long timestamp = -1;
		CPUInfo cpuInfo = null;
		for (String str : strs) {

			if (str.startsWith("date")) {

				DateFormat formatter = new SimpleDateFormat("MM-dd-yyyy hh:mm:ss a");
				Date date = (Date) formatter.parse(str.replace("date ", ""));
				Calendar cal = Calendar.getInstance();
				cal.setTime(date);
				timestamp = cal.getTimeInMillis();

			} else if (str.startsWith("cpu")) {

				String[] tokens = parseString(str);

				if ("cpu".equals(tokens[0])) {
					long procUser = Long.parseLong(tokens[1]);
					long procNice = Long.parseLong(tokens[2]);
					long procSystem = Long.parseLong(tokens[3]);
					long procIdle = Long.parseLong(tokens[4]);

					cpuInfo = new CPUInfo(node, timestamp, procUser, procNice, procSystem, procIdle);
				}
			}
		}
		return cpuInfo;
	}

	public void getResourceStatus(String node, ResourceStatusResponse response) {
		SSHSession sshSession = sessions.get(node);
		if (sshSession != null) {
			try {
				ArrayList<String> resourceStrs = sshSession.getStandardOutput(RESOURCE_COMMAND);

				StringBuffer sb = new StringBuffer();
				Iterator<String> it = resourceStrs.iterator();
				while (it.hasNext()) {
					String str = it.next();
					sb.append(str);
					sb.append("\n");
				}

				CPUInfo cpuInfo = getCPUInfo(node, resourceStrs);

				long loadPercent = CPUInfo.calculateCPULoad(cpuInfo);

				long usedPercent = getMemoryInfo(node, resourceStrs);
				ResourceStatusResponse.MemoryStatus ms = new ResourceStatusResponse.MemoryStatus();
				ms.setPercentage(usedPercent);

				ResourceStatusResponse.NetworkStatus nsG = getNetworkInfo(node, resourceStrs);
				ResourceStatusResponse.NetworkStatus nsL = ResourceStatusResponse.NetworkStatus.getNetworkStatus(node, nsG);

				ArrayList<String> storageStrs = sshSession.getStandardOutput(STORAGE_COMMAND);

				StringBuffer sbStorage = new StringBuffer();
				Iterator<String> itStorage = storageStrs.iterator();
				while (itStorage.hasNext()) {
					String str = itStorage.next();
					sbStorage.append(str);
					sbStorage.append("\n");
				}

				long storageSize = getStorageSize(storageStrs);

				response.setCPUStatus(node, loadPercent);
				response.setMemoryStatus(node, ms);
				response.setNetworkStatus(node, nsL);
				response.setStorageCost(node, storageSize);
				response.setLogSize(node, coordinatorAccessor.getNumOfEvents(node));

				System.out.println("Checked status of node " + node);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static long getMemoryInfo(String node, ArrayList<String> strs) throws Exception {
		long memTotal = 0;
		long memFree = 0;
		long buffers = 0;
		long cached = 0;
		long swapTotal = 0;
		long swapFree = 0;
		String unit = null;

		for (String str : strs) {

			if (str.startsWith("date")) {

				DateFormat formatter = new SimpleDateFormat("MM-dd-yyyy hh:mm:ss a");
				Date date = (Date) formatter.parse(str.replace("date ", ""));
				Calendar cal = Calendar.getInstance();
				cal.setTime(date);
				// mhyp.setTimeStamp(cal);

			} else if (str.startsWith("MemTotal")) {

				String[] tokens = parseString(str);
				memTotal = Long.parseLong(tokens[1]);
				unit = tokens[2];

			} else if (str.startsWith("MemFree")) {

				String[] tokens = parseString(str);
				memFree = Long.parseLong(tokens[1]);

			} else if (str.startsWith("Buffers")) {

				String[] tokens = parseString(str);
				buffers = Long.parseLong(tokens[1]);

			} else if (str.startsWith("Cached")) {

				String[] tokens = parseString(str);
				cached = Long.parseLong(tokens[1]);

			} else if (str.startsWith("SwapTotal")) {

				String[] tokens = parseString(str);
				swapTotal = Long.parseLong(tokens[1]);

			} else if (str.startsWith("SwapFree")) {

				String[] tokens = parseString(str);
				swapFree = Long.parseLong(tokens[1]);

			}
		}

		Long freeMemory = memFree + buffers + cached;
		Long usedMemory = memTotal - freeMemory;

		// usedMemory = swapTotal - swapFree;

		return 100 * usedMemory / (usedMemory + freeMemory);
	}

	private ResourceStatusResponse.NetworkStatus getNetworkInfo(String node, ArrayList<String> strs) throws Exception {
		long receiving = 0;
		long sending = 0;
		
		for (String str : strs) {

			if (str.startsWith("Interface")) {

				String[] tokens = parseString(str);

				// do not add in loopback device
				if (!tokens[1].toLowerCase().startsWith("lo")) {
					receiving += Long.parseLong(tokens[2]);
					sending += Long.parseLong(tokens[10]);
				}
			}
		}
		
		return new ResourceStatusResponse.NetworkStatus(receiving, sending);
		
	}

	private static String[] parseString(String str) throws Exception {

		try {
			String delims = "[ ]+";
			String[] strs = str.replaceAll("\t", " ").replace("  ", " ").split(delims, 20);

			return strs;

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void setSender(JMSSender sender) {
		this.sender = sender;

	}

	public void setRequestId(String requestID) {
		this.requestID = requestID;

	}

}
