package com.hp.hpl.logkv.ui;

import java.util.UUID;

import javax.jms.DeliveryMode;

import com.hp.hpl.logkv.demo.jms.JMSSender;

public class ResourceStatusRequestTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JMSSender sender = new JMSSender("LogKV.request", DeliveryMode.PERSISTENT, 0);
		
		ResourceStatusRequest request = new ResourceStatusRequest(true, null);
		for (int i = 0; i < 10000; i++) {
			//Util.log("" + i, RequestTest.class);
			String requestClientID = UUID.randomUUID().toString();
			sender.send(requestClientID, request);
			try {
				Thread.sleep(3 * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//Util.log("" + i, RequestTest.class);
		}
		sender.release();

	}

}
