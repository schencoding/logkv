package com.hp.hpl.logkv.ui;

import java.util.HashMap;
import java.util.Map;

import javax.jms.DeliveryMode;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import com.hp.hpl.logkv.conf.Parameter;
import com.hp.hpl.logkv.demo.jms.JMSListener;
import com.hp.hpl.logkv.demo.jms.JMSSender;
import com.hp.hpl.logkv.demo.util.Constants;
import com.hp.hpl.logkv.util.Util;
import com.hp.hpl.logkv.util.WorkQueue;

public class UIRequestProcessor extends Thread {

	private JMSSender sender = new JMSSender("LogKV.response", DeliveryMode.PERSISTENT, 60 * 1000);
	private WorkQueue processorThreads = new WorkQueue(Parameter.NUM_OF_THRES_FOR_UI_REQUEST);
	
	private Map<String, GetShuffleStatusThread> shuffleStatusThreads = new HashMap<String, GetShuffleStatusThread>();
	private Map<String, Long> lastHeartBeatTimes = new HashMap<String, Long>();

	public UIRequestProcessor() {

	}

	public void run() {

		JMSListener listener = new JMSListener("LogKV.request");

		listener.listen(new MessageListener() {

			@Override
			public void onMessage(Message message) {

				try {
					if (message instanceof ObjectMessage) {

						String requestID = message.getStringProperty(Constants.JMS_REQUEST_CLIENT_ID);

						Object obj = ((ObjectMessage) message).getObject();
						System.out.println("Receive message: " + obj);
						if (obj instanceof ResourceStatusRequest) {
							ResourceStatusDetecter resourceStatusDetecter = new ResourceStatusDetecter((ResourceStatusRequest) obj);
							resourceStatusDetecter.setSender(sender);
							resourceStatusDetecter.setRequestId(requestID);
							processorThreads.execute(resourceStatusDetecter);
						} else if (obj instanceof AddLogSourceRequest) {
							AddLogSourceThread addLogSourceThread = new AddLogSourceThread((AddLogSourceRequest) obj);
							addLogSourceThread.setSender(sender);
							addLogSourceThread.setRequestId(requestID);
							processorThreads.execute(addLogSourceThread);
						} else if (obj instanceof ShuffleStatusRequest) {							
							GetShuffleStatusThread getShuffleStatusThread = shuffleStatusThreads.get(requestID);
							Long lashheartBeatTime = lastHeartBeatTimes.get(requestID);
							if(getShuffleStatusThread == null){
								getShuffleStatusThread = new GetShuffleStatusThread();
								getShuffleStatusThread.setSender(sender);
								getShuffleStatusThread.setRequestId(requestID);
								getShuffleStatusThread.setLastHeartBeatTimes(lastHeartBeatTimes);
								processorThreads.execute(getShuffleStatusThread);
								
								shuffleStatusThreads.put(requestID, getShuffleStatusThread);
							}
							if(lashheartBeatTime == null){
								lastHeartBeatTimes.put(requestID, System.currentTimeMillis());
							}						
							
						} else if (obj instanceof ShuffleDestroyRequest) {		
							GetShuffleStatusThread getShuffleStatusThread = shuffleStatusThreads.get(requestID);
							if(getShuffleStatusThread != null){
								getShuffleStatusThread.bEnd = true;
								System.out.println("getShuffleStatusThread: " + requestID + " is destroyed.");
							}
						}
						else if (obj instanceof GetNodesRequest) {
							GetNodesThread getNodesThread = new GetNodesThread();
							getNodesThread.setSender(sender);
							getNodesThread.setRequestId(requestID);
							processorThreads.execute(getNodesThread);
						} else if (obj instanceof GetLogSourcesRequest) {
							GetLogSourcesThread getLogSourcesThread = new GetLogSourcesThread();
							getLogSourcesThread.setSender(sender);
							getLogSourcesThread.setRequestId(requestID);
							processorThreads.execute(getLogSourcesThread);
						} else if (obj instanceof QueryRequest) {
							QueryProcessThread queryProcessThread = new QueryProcessThread();
							queryProcessThread.setSender(sender);
							queryProcessThread.setRequestId(requestID);
							queryProcessThread.setRequest((QueryRequest) obj);
							processorThreads.execute(queryProcessThread);
						}else {
							System.out.println("message type wrong : " + message);
						}
					}
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
		});
	}

}
