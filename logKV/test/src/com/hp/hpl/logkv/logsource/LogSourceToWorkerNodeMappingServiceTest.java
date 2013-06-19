package com.hp.hpl.logkv.logsource;

import java.rmi.RemoteException;

import com.hp.hpl.logkv.coordinator.CoordinatorAccessor;

public class LogSourceToWorkerNodeMappingServiceTest {

	/**
	 * @param args
	 * @throws RemoteException
	 */
	public static void main(String[] args) throws RemoteException {
		CoordinatorAccessor coordinatorAccessor = new CoordinatorAccessor();

		// 0
		LogSourceDescriptor logSource0 = new LogSourceDescriptor();
		logSource0.setSourceIp("ip-0");
		logSource0.setLogSourceId(0);
		logSource0.setbSplitable(false);
		logSource0.setThroughput(10);
		coordinatorAccessor.addLogSource(logSource0);
		LogSourceToWorkerNodeMappingServiceTest.printMapping(coordinatorAccessor.getAllLogSources());

		// 1
		LogSourceDescriptor logSource1 = new LogSourceDescriptor();
		logSource1.setSourceIp("ip-1");
		logSource1.setLogSourceId(1);
		logSource1.setbSplitable(false);
		logSource1.setThroughput(15);
		coordinatorAccessor.addLogSource(logSource1);
		LogSourceToWorkerNodeMappingServiceTest.printMapping(coordinatorAccessor.getAllLogSources());

		// 2
		LogSourceDescriptor logSource2 = new LogSourceDescriptor();
		logSource2.setSourceIp("ip-2");
		logSource2.setLogSourceId(2);
		logSource2.setbSplitable(true);
		logSource2.setThroughput(30);
		coordinatorAccessor.addLogSource(logSource2);
		LogSourceToWorkerNodeMappingServiceTest.printMapping(coordinatorAccessor.getAllLogSources());

		// 3
		LogSourceDescriptor logSource3 = new LogSourceDescriptor();
		logSource3.setSourceIp("ip-3");
		logSource3.setLogSourceId(3);
		logSource3.setbSplitable(true);
		logSource3.setThroughput(20);
		coordinatorAccessor.addLogSource(logSource3);
		LogSourceToWorkerNodeMappingServiceTest.printMapping(coordinatorAccessor.getAllLogSources());

	}

	public static void printMapping(LogSourceCollection logSourceCollection) {
		System.out.println("--------------------------------------");
		System.out.println(logSourceCollection.toString());
		System.out.println("--------------------------------------");
	}

}
