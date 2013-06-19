package com.hp.hpl.logkv.demo.jms;

import javax.jms.Connection;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;

import com.hp.hpl.logkv.demo.util.Constants;

/**
 * JMS Receiver.
 * @author Edmond
 */
public class JMSReceiver {

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
	 * JMS consumer queue.
	 */
	private Queue consumerQueue;

	/**
	 * constructor method.
	 * @param queueName		listen queue name
	 */
	public JMSReceiver(String queueName) {

		connectionFactory = JMSConnectionFactory.getInstance();

		try {

			connection = connectionFactory.createConnection();

			connection.start();

			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

			consumerQueue = session.createQueue(queueName);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	/**
	 * receive message.
	 * @param requestClientID	request client ID
	 * @param timeout			receive time out
	 * @return receive message
	 */
	public Message receive(String requestClientID, long timeout) {

		MessageConsumer comsumer = null;

		try {

			if (requestClientID != null) {
				String messageSelector = Constants.JMS_REQUEST_CLIENT_ID + "='" + requestClientID + "'";
				System.out.println(messageSelector);
				comsumer = session.createConsumer(consumerQueue, messageSelector);
			} else {
				comsumer = session.createConsumer(consumerQueue);
			}

			return comsumer.receive(timeout);

		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {

			if (comsumer != null) {
				try {
					comsumer.close();
				} catch (Exception ingore) { System.err.println(ingore); }
			}

		}

	}

	/**
	 * release resource.
	 */
	public void release() {

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
