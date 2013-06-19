package com.hp.hpl.logkv.ui;

import java.util.UUID;

import javax.jms.DeliveryMode;

import com.hp.hpl.logkv.demo.jms.JMSSender;

public class GetLogSourceRequestTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JMSSender sender = new JMSSender("LogKV.request", DeliveryMode.PERSISTENT, 0);

		AddLogSourceRequest request = new AddLogSourceRequest("16.158.159.183", 0, true, 20);

		String requestClientID = UUID.randomUUID().toString();
		sender.send(requestClientID, request);
		try {
			Thread.sleep(3 * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		sender.release();

	}

}
