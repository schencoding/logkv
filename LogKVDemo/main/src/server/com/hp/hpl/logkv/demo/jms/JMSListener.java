package com.hp.hpl.logkv.demo.jms;

import javax.jms.Connection;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.Session;

/**
 * JMS Listener.
 * @author Edmond
 */
public class JMSListener {

	/**
	 * JMS connection factory.
	 */
	private JMSConnectionFactory connectionFactory;

	/**
	 * JMS connection.
	 */
	private Connection connection;

	/**
	 * JMS session.
	 */
	private Session session;

	/**
	 * JMS consumer.
	 */
	private MessageConsumer comsumer;

	/**
	 * constructor method.
	 * @param queueName		listen queue name
	 */
	public JMSListener(String queueName) {

		connectionFactory = JMSConnectionFactory.getInstance();

		try {

			connection = connectionFactory.createConnection();

			connection.start();

			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

			Queue consumerQueue = session.createQueue(queueName);

			comsumer = session.createConsumer(consumerQueue);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	/**
	 * listen message.
	 * @param messageListener	message listener
	 */
	public void listen(MessageListener messageListener) {

		try {
			comsumer.setMessageListener(messageListener);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	/**
	 * release resource.
	 */
	public void release() {

		if (comsumer != null) {
			try {
				comsumer.close();
			} catch (Exception ingore) { System.err.println(ingore); }
		}

		if (session != null) {
			try {
				session.close();
			} catch (Exception ingore) { System.err.println(session); }
		}

		if (connection != null) {
			connectionFactory.closeConnection(connection);
		}

	}

}
