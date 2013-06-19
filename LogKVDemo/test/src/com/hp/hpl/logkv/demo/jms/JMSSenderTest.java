package com.hp.hpl.logkv.demo.jms;

import javax.jms.DeliveryMode;

/**
 * JMS sender test.
 * @author Edmond
 */
public final class JMSSenderTest {

	/**
	 * prevent to instance.
	 */
	private JMSSenderTest() {
	}

	/**
	 * main method.
	 * @param args	arguments array
	 */
	public static void main(String[] args) {

		JMSSender sender = new JMSSender("logkv", DeliveryMode.PERSISTENT, 0);
		String requestClientID = "001";
		String messageContent = "Guo Meng";
		sender.send(requestClientID, messageContent);
		sender.send(requestClientID, "HR");
		sender.release();

	}

}
