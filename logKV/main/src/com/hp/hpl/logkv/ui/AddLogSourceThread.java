package com.hp.hpl.logkv.ui;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.rmi.RemoteException;

import com.hp.hpl.logkv.conf.Parameter;
import com.hp.hpl.logkv.coordinator.CoordinatorAccessor;
import com.hp.hpl.logkv.demo.jms.JMSSender;
import com.hp.hpl.logkv.logsource.LogSourceCollection;
import com.hp.hpl.logkv.logsource.LogSourceDescriptor;
import com.hp.hpl.logkv.util.Util;

public class AddLogSourceThread implements Runnable{
	private AddLogSourceRequest request = null;
	private static CoordinatorAccessor coordinatorAccessor = new CoordinatorAccessor();
	
	private JMSSender sender = null;
	private String requestID = null;
	
	public AddLogSourceThread(AddLogSourceRequest alsr){
		this.request = alsr;
	}

	@Override
	public void run() {	
		LogSourceDescriptor logSource = new LogSourceDescriptor();
		logSource.setSourceIp(request.getSourceIp());
		logSource.setLogSourceId(request.getLogSourceId());
		logSource.setbSplitable(request.isbSplitable());
		logSource.setThroughput(request.getThroughput());
		String[] nodes = null;
		try {
			nodes = coordinatorAccessor.getAllLiveNodes();
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
		try {
			/**
			 * Add the LogSource to CoordinatorServer, and get the mapping
			 */
			coordinatorAccessor.addLogSource(logSource);			
			LogSourceCollection logSources = coordinatorAccessor.getAllLogSources();
			
			/**
			 * Start a LogSourceAgent / update every log source
			 */
			LogSourceDescriptor[] logSourceDescriptors = logSources.getLogSources();
			for(int i = 0; i < logSourceDescriptors.length; i++){
				LogSourceDescriptor descriptor = logSourceDescriptors[i];
				//if(descriptor.getLogSourceId() == logSource.getLogSourceId()){					
					sendToLoggSourceRunner(descriptor.getSourceIp(), descriptor);
					
					Util.log("LogSource (re-)started: \n" + descriptor, this.getClass());
				//}
			}
			/**
			 * Update the mapping of all the LogSourceAgent
			 */ 
			//for(int i = 0; i < nodes.length; i++){
			//	sendToLoggSourceRunner(nodes[i], logSources);
			//}			
			
			sender.send(requestID, new AddLogSourceResponse(System.currentTimeMillis(), logSources, nodes));
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public void sendToLoggSourceRunner(String ipStr, Object obj){
		try {
			System.out.println("connecting to log source: " + ipStr);
			Socket socket = new Socket(ipStr, Parameter.LOG_SOURCE_RUNNER_PORT);
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
			oos.writeObject(obj);
			oos.flush();
			oos.close();
			socket.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setSender(JMSSender sender) {
		this.sender = sender;		
	}

	public void setRequestId(String requestID) {
		this.requestID = requestID;		
	}

}
