package com.hp.hpl.logkv.demo.jms;

import java.io.Serializable;

import javax.jms.Connection;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;

import com.hp.hpl.logkv.demo.util.Constants;

/**
 * JMS Sender.
 * @author Edmond
 */
public class JMSSender {

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
	 * JMS producer.
	 */
	private MessageProducer  producer;

	/**
	 * constructor method.
	 * @param queueName		producer queue name
	 * @param deliveryMode	deliver mode (DeliveryMode.PERSISTENT or DeliveryMode.NON_PERSISTENT)
	 * @param timeToLive	time to live in millisecond
	 */
	public JMSSender(String queueName, int deliveryMode, int timeToLive) {

		connectionFactory = JMSConnectionFactory.getInstance();

		try {

			connection = connectionFactory.createConnection();

			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

			Queue producerQueue = session.createQueue(queueName);

			producer = session.createProducer(producerQueue);

			producer.setDeliveryMode(deliveryMode);

			producer.setTimeToLive(timeToLive);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	/**
	 * send message for object.
	 * @param requestClientID	request client ID
	 * @param messageContent	list object
	 */
	public void send(String requestClientID, Serializable messageContent) {

		try {

			ObjectMessage objectMessage = session.createObjectMessage(messageContent);

			if (requestClientID != null) {
				objectMessage.setStringProperty(Constants.JMS_REQUEST_CLIENT_ID, requestClientID);
			}

			producer.send(objectMessage);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	/**
	 * release resource.
	 */
	public void release() {

		if (producer != null) {
			try {
				producer.close();
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
