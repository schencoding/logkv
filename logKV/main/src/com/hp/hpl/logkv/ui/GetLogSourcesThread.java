package com.hp.hpl.logkv.ui;

import java.rmi.RemoteException;
import java.util.Arrays;

import com.hp.hpl.logkv.coordinator.CoordinatorAccessor;
import com.hp.hpl.logkv.demo.jms.JMSSender;
import com.hp.hpl.logkv.logsource.LogSourceCollection;

public class GetLogSourcesThread implements Runnable {
	private static CoordinatorAccessor coordinatorAccessor = new CoordinatorAccessor();

	private JMSSender sender = null;
	private String requestID = null;

	@Override
	public void run() {
		String[] nodes = null;
		try {
			nodes = coordinatorAccessor.getAllLiveNodes();
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
		try {
			LogSourceCollection logSources = coordinatorAccessor.getAllLogSources();
			System.out.println("Send out message: " + logSources);
			sender.send(requestID, new AddLogSourceResponse(System.currentTimeMillis(), logSources, nodes));
		} catch (RemoteException e) {
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
