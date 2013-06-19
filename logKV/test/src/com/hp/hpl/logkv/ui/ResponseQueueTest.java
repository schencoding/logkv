package com.hp.hpl.logkv.ui;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import com.hp.hpl.logkv.demo.jms.JMSListener;

/**
 * JMS listener test.
 * @author Edmond
 */
public final class ResponseQueueTest {

	/**
	 * prevent to instance.
	 */
	private ResponseQueueTest() {
	}

	/**
	 * main method.
	 * @param args	arguments array
	 */
	public static void main(String[] args) {

		JMSListener listener = new JMSListener("LogKV.response");

		listener.listen(new MessageListener() {

			@Override
			public void onMessage(Message message) {

				try {
					if (message instanceof ObjectMessage) {
						System.out.println("receive message : " + ((ObjectMessage) message).getObject());
					} else {
						System.out.println("message type wrong : " + message);
					}
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
		});
	}

}
