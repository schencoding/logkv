package com.hp.hpl.logkv.demo.jms;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;


/**
 * JMS receiver test.
 * @author Edmond
 */
public final class JMSReceiverTest {

	/**
	 * prevent to instance.
	 */
	private JMSReceiverTest() {
	}

	/**
	 * main method.
	 * @param args	arguments array
	 * @throws JMSException JMS Exception
	 */
	public static void main(String[] args) throws JMSException {

		JMSReceiver receiver = new JMSReceiver("logkv");

		System.out.println(((ObjectMessage) receiver.receive("001", 1000)).getObject());
		System.out.println(((ObjectMessage) receiver.receive("001", 1000)).getObject());
		System.out.println(((ObjectMessage) receiver.receive("001", 1000)).getObject());

		receiver.release();
	}

}
