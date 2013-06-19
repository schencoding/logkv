package com.hp.hpl.logkv.logsource;

public class LogSourceToWorkerNodeMapperTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LogSourceToWorkerNodeMapper mapper = new LogSourceToWorkerNodeMapper();
		// 0
		LogSourceDescriptor logSource0 = new LogSourceDescriptor();
		logSource0.setSourceIp("ip-0");
		logSource0.setbSplitable(false);
		logSource0.setThroughput(10);
		mapper.addLogSource(logSource0);
		mapper.printMapping();

		// 1
		LogSourceDescriptor logSource1 = new LogSourceDescriptor();
		logSource1.setSourceIp("ip-1");
		logSource1.setbSplitable(false);
		logSource1.setThroughput(15);
		mapper.addLogSource(logSource1);
		mapper.printMapping();

		// 2
		LogSourceDescriptor logSource2 = new LogSourceDescriptor();
		logSource2.setSourceIp("ip-2");
		logSource2.setbSplitable(true);
		logSource2.setThroughput(30);
		mapper.addLogSource(logSource2);
		mapper.printMapping();

		// 3
		LogSourceDescriptor logSource3 = new LogSourceDescriptor();
		logSource3.setSourceIp("ip-3");
		logSource3.setbSplitable(true);
		logSource3.setThroughput(20);
		mapper.addLogSource(logSource3);
		mapper.printMapping();

	}

}
