package com.hp.hpl.logkv.demo.jms;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

/**
 * JMS listener test.
 * @author Edmond
 */
public final class JMSListenerTest {

	/**
	 * prevent to instance.
	 */
	private JMSListenerTest() {
	}

	/**
	 * main method.
	 * @param args	arguments array
	 */
	public static void main(String[] args) {

		JMSListener listener = new JMSListener("LogKV.request");

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
