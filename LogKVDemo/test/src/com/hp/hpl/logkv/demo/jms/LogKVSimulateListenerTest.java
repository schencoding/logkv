package com.hp.hpl.logkv.demo.jms;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Random;

import javax.jms.DeliveryMode;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import com.hp.hpl.logkv.demo.util.Constants;
import com.hp.hpl.logkv.demo.util.RandomNumberGenerator;
import com.hp.hpl.logkv.ingestkv.ShuffleStatus;
import com.hp.hpl.logkv.logsource.LogSourceCollection;
import com.hp.hpl.logkv.logsource.LogSourceDescriptor;
import com.hp.hpl.logkv.queryprocess.QueryProcessingStatistics;
import com.hp.hpl.logkv.queryprocess.QueryProcessingStatistics.IngestKVProcessingStatistics;
import com.hp.hpl.logkv.queryprocess.QueryProcessingStatistics.TRUProcessingStatistics;
import com.hp.hpl.logkv.queryprocess.ResultSet.Record;
import com.hp.hpl.logkv.ui.AddLogSourceRequest;
import com.hp.hpl.logkv.ui.AddLogSourceResponse;
import com.hp.hpl.logkv.ui.GetLogSourcesRequest;
import com.hp.hpl.logkv.ui.GetNodesRequest;
import com.hp.hpl.logkv.ui.QueryRequest;
import com.hp.hpl.logkv.ui.QueryResponse;
import com.hp.hpl.logkv.ui.ResourceStatusRequest;
import com.hp.hpl.logkv.ui.ResourceStatusResponse;
import com.hp.hpl.logkv.ui.ResourceStatusResponse.MemoryStatus;
import com.hp.hpl.logkv.ui.ResourceStatusResponse.NetworkStatus;
import com.hp.hpl.logkv.ui.ShuffleDestroyRequest;
import com.hp.hpl.logkv.ui.ShuffleStatusRequest;
import com.hp.hpl.logkv.ui.ShuffleStatusResponse;

/**
 * JMS listener test.
 * @author Edmond
 */
public final class LogKVSimulateListenerTest {

	/**
	 * prevent to instance.
	 */
	private LogKVSimulateListenerTest() {
	}

	/**
	 * main method.
	 * @param args	arguments array
	 */
	public static void main(String[] args) {

		JMSListener listener = new JMSListener("LogKV.request");

		listener.listen(new MessageListener() {

			JMSSender sender = new JMSSender("LogKV.response", DeliveryMode.PERSISTENT, 60 * 1000);

			@Override
			public void onMessage(Message message) {

				try {
					if (message instanceof ObjectMessage) {
						System.out.println("receive message : " + ((ObjectMessage) message).getObject());
						Object obj = ((ObjectMessage) message).getObject();
						if (obj instanceof ResourceStatusRequest) {
							ResourceStatusResponse responseObj = generateResourceStatusResponse();
							String requestClientID = message.getStringProperty(Constants.JMS_REQUEST_CLIENT_ID);
							sender.send(requestClientID, responseObj);
						} else if (obj instanceof GetLogSourcesRequest) {
							AddLogSourceResponse addLogSourceResponse = generateGetLogSourcesResponseNew();
							String requestClientID = message.getStringProperty(Constants.JMS_REQUEST_CLIENT_ID);
							sender.send(requestClientID, addLogSourceResponse);
						} else if (obj instanceof AddLogSourceRequest) {
							AddLogSourceResponse addLogSourceResponse = generateAddLogSourceResponse((AddLogSourceRequest) obj);
							String requestClientID = message.getStringProperty(Constants.JMS_REQUEST_CLIENT_ID);
							sender.send(requestClientID, addLogSourceResponse);
						} else if (obj instanceof GetNodesRequest) {
							String[] ipStrArray = generateGetNodesResponse();
							String requestClientID = message.getStringProperty(Constants.JMS_REQUEST_CLIENT_ID);
							sender.send(requestClientID, ipStrArray);
						} else if (obj instanceof ShuffleStatusRequest) {
							String requestClientID = message.getStringProperty(Constants.JMS_REQUEST_CLIENT_ID);
							for (int i = 0; i <= 20; i++) {
								ShuffleStatusResponse response = generateShuffleStatusResponse();
								sender.send(requestClientID, response);
							}
						} else if (obj instanceof QueryRequest) {
							QueryResponse queryResponse = generateQueryResponse();
							String requestClientID = message.getStringProperty(Constants.JMS_REQUEST_CLIENT_ID);
							sender.send(requestClientID, queryResponse);
						} else if (obj instanceof ShuffleDestroyRequest) {
							System.err.println("destroy shuffling id : " + ((ShuffleDestroyRequest) obj).getRequestID());
						} else {
							System.err.println("message object type wrong : " + obj);
						}
					} else {
						System.err.println("message type wrong : " + message);
					}
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
		});
	}

	/**
	 * generate resource status response.
	 * @return	resource status response
	 */
	public static ResourceStatusResponse generateResourceStatusResponse() {

		ResourceStatusResponse response = new ResourceStatusResponse(new Date().getTime());

		String[] ipStrArray = new String[] {"192.168.1.1", "192.168.1.2", "192.168.1.3"};

		for (String ipStr : ipStrArray) {
			response.setLogSize(ipStr, RandomNumberGenerator.getRandomLong());
			response.setCPUStatus(ipStr, RandomNumberGenerator.getRandomUsage());
			MemoryStatus memoryStatus = new MemoryStatus();
			memoryStatus.setPercentage(RandomNumberGenerator.getRandomUsage());
			response.setMemoryStatus(ipStr, memoryStatus);
			NetworkStatus networkStatus = new NetworkStatus(RandomNumberGenerator.getRandomLong(), RandomNumberGenerator.getRandomLong());
			response.setNetworkStatus(ipStr, networkStatus);
			response.setStorageCost(ipStr, RandomNumberGenerator.getRandomLong());
		}

		return response;
	}

	/**
	 * generate get nodes response.
	 * @return node IP string array
	 */
	public static String[] generateGetNodesResponse() {
		return new String[] {"192.168.1.1", "192.168.1.2", "192.168.1.3"};
	}


	/**
	 * generate get log source response.
	 * @return log source response
	 */
	public static LogSourceCollection generateGetLogSourcesResponse() {

		Collection<LogSourceDescriptor> logSourceList = new ArrayList<LogSourceDescriptor>();

		LogSourceDescriptor d1 = new LogSourceDescriptor();
		d1.setSourceIp("192.168.1.1");
		d1.setLogSourceId(1);
		d1.setbSplitable(true);
		d1.setThroughput(100);
		d1.addMapping(0, 0.1);
		d1.addMapping(1, 0.4);
		d1.addMapping(2, 0.5);

		LogSourceDescriptor d2 = new LogSourceDescriptor();
		d2.setSourceIp("192.168.1.2");
		d2.setLogSourceId(2);
		d2.setbSplitable(true);
		d2.setThroughput(100);
		d2.addMapping(0, 1);

		logSourceList.add(d1);
		logSourceList.add(d2);

		LogSourceCollection collection = new LogSourceCollection(logSourceList);
		return collection;
	}

	/**
	 * generate get log source response.
	 * @return add log source response
	 */
	public static AddLogSourceResponse generateGetLogSourcesResponseNew() {

		AddLogSourceResponse r = new AddLogSourceResponse(System.currentTimeMillis(), generateGetLogSourcesResponse(), generateGetNodesResponse());
		return r;
	}

	/**
	 * collection cache.
	 */
	private static LogSourceCollection collection = new LogSourceCollection(new ArrayList<LogSourceDescriptor>());

	static {

		Collection<LogSourceDescriptor> logSourceList = new ArrayList<LogSourceDescriptor>();

		LogSourceDescriptor d1 = new LogSourceDescriptor();
		d1.setSourceIp("192.168.1.1");
		d1.setLogSourceId(1);
		d1.setbSplitable(true);
		d1.setThroughput(100);
		d1.addMapping(0, 0.1);
		d1.addMapping(1, 0.4);
		d1.addMapping(2, 0.5);

		LogSourceDescriptor d2 = new LogSourceDescriptor();
		d2.setSourceIp("192.168.1.2");
		d2.setLogSourceId(2);
		d2.setbSplitable(true);
		d2.setThroughput(100);
		d2.addMapping(0, 1);

		logSourceList.add(d1);
		logSourceList.add(d2);

		collection = new LogSourceCollection(logSourceList);
	}

	/**
	 * generate get log source response.
	 * @param requestObj	request object
	 * @return add log source response
	 */
	public static AddLogSourceResponse generateAddLogSourceResponse(AddLogSourceRequest requestObj) {

		Collection<LogSourceDescriptor> logSourceList = new ArrayList<LogSourceDescriptor>();

		for (LogSourceDescriptor ld : collection.getLogSources()) {
			logSourceList.add(ld);
		}

		LogSourceDescriptor d1 = new LogSourceDescriptor();
		d1.setSourceIp(requestObj.getSourceIp());
		d1.setLogSourceId(requestObj.getLogSourceId());
		d1.setbSplitable(requestObj.isbSplitable());
		d1.setThroughput(requestObj.getThroughput());
		d1.addMapping(0, 0.1);
		d1.addMapping(1, 0.4);
		d1.addMapping(2, 0.5);

		logSourceList.add(d1);

		collection = new LogSourceCollection(logSourceList);

		AddLogSourceResponse r = new AddLogSourceResponse(System.currentTimeMillis(), collection, generateGetNodesResponse());
		return r;
	}

	/**
	 * generate shuffle status response.
	 * @return shuffle status response
	 */
	public static ShuffleStatusResponse generateShuffleStatusResponse() {

		Random random = new Random();

		ShuffleStatusResponse response = new ShuffleStatusResponse(System.currentTimeMillis());

		ShuffleStatus[] shuffleStatusArray = new ShuffleStatus[1];

		String[] ipStrArray = generateGetNodesResponse();

		ShuffleStatus shuffleStatus = new ShuffleStatus();
		shuffleStatus.setbStart(random.nextInt(2) == 0 ? true : false);
		shuffleStatus.setSrcIpStr(ipStrArray[random.nextInt(ipStrArray.length)]);
		shuffleStatus.setDestIpStr(ipStrArray[random.nextInt(ipStrArray.length)]);
		shuffleStatus.setSize(random.nextInt(5000));

		shuffleStatus.setTruId(1);

		shuffleStatusArray[0] = shuffleStatus;

		response.setShuffleStatuses(shuffleStatusArray);
		response.setIngestedTRUs(new Long[] {1L, 2L, 3L, 4L});

		return response;
	}

	/**
	 * generate query response.
	 * @return	query response
	 */
	public static QueryResponse generateQueryResponse() {

		long timestamp = System.currentTimeMillis();

		Record r1 = new Record();
		r1.setFieldValue(0, "1");
		r1.setFieldValue(1, "2");
		r1.setFieldValue(2, "3");

		Record r2 = new Record();
		r2.setFieldValue(0, "4");
		r2.setFieldValue(1, "5");
		r2.setFieldValue(2, "6");

		Record[] resultSet = new Record[] {r1, r2};

		String[] columnNames = new String[] {"field1", "field2", "field3"};

		int[] columnIds = new int[] {0, 1, 2};

		QueryProcessingStatistics runningStatistics = new QueryProcessingStatistics();

		runningStatistics.setRunningTime(100);

		TRUProcessingStatistics tru1 = new TRUProcessingStatistics(1, "192.168.1.1");
		tru1.setNumOfReturnedRecords(101);
		tru1.setRunningTime(102);
		runningStatistics.putTruProcessingStatistic(tru1);

		TRUProcessingStatistics tru2 = new TRUProcessingStatistics(2, "192.168.1.2");
		tru2.setNumOfReturnedRecords(201);
		tru2.setRunningTime(202);
		runningStatistics.putTruProcessingStatistic(tru2);

		IngestKVProcessingStatistics ingest1 = new IngestKVProcessingStatistics("192.168.1.1");
		ingest1.setNumOfReturnedRecords(1101);
		ingest1.setRunningTime(1102);
		runningStatistics.putIngestKVProcessingStatistics(ingest1);

		IngestKVProcessingStatistics ingest2 = new IngestKVProcessingStatistics("192.168.1.2");
		ingest2.setNumOfReturnedRecords(2201);
		ingest2.setRunningTime(2202);
		runningStatistics.putIngestKVProcessingStatistics(ingest2);

		QueryResponse queryResponse = new QueryResponse(timestamp, resultSet, columnNames, columnIds, runningStatistics);

		return queryResponse;
	}

}
