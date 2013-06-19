package com.hp.hpl.logkv.ui;

import java.rmi.RemoteException;

import com.hp.hpl.logkv.coordinator.CoordinatorAccessor;
import com.hp.hpl.logkv.demo.jms.JMSSender;

public class GetNodesThread implements Runnable {

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

		sender.send(requestID, nodes);
	}

	public void setSender(JMSSender sender) {
		this.sender = sender;
	}

	public void setRequestId(String requestID) {
		this.requestID = requestID;
	}

}
