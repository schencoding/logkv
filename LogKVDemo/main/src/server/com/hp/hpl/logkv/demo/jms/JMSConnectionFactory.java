package com.hp.hpl.logkv.demo.jms;

import java.util.Properties;

import javax.jms.Connection;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * JMS worker.
 * @author Edmond
 */
public final class JMSConnectionFactory {

	/**
	 * instance of JMS connection factory.
	 */
	private static JMSConnectionFactory instance;

	/**
	 * JMS URL.
	 */
	private static String url;

	/**
	 * JMS user name.
	 */
	private static String userName;

	/**
	 * JMS password.
	 */
	private static String password;

	/**
	 * connection factory.
	 */
	private ActiveMQConnectionFactory connectionFactory;

	static {

		try {
			// load JMS configuration
			Properties jmsProperties = new Properties();
			jmsProperties.load(JMSConnectionFactory.class.getResourceAsStream("/JMS.properties"));
			url = jmsProperties.getProperty("url");
			userName = jmsProperties.getProperty("userName");
			password = jmsProperties.getProperty("password");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Constructor method.
	 */
	private JMSConnectionFactory() {
		connectionFactory = new ActiveMQConnectionFactory(userName, password, url);
	}

	/**
	 * get JMS connection factory instance.
	 * @return	JMS connection factory instance
	 */
	public static synchronized JMSConnectionFactory getInstance() {

		if (instance == null) {
			instance = new JMSConnectionFactory();
		}

		return instance;
	}

	 /**
	  * create JMS connection.
	  * @return	JMS connection
	  */
	public Connection createConnection() {

		try {
			return connectionFactory.createConnection();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	/**
	 * close JMS connection.
	 * @param connection	JMS connection
	 */
	public void closeConnection(Connection connection) {
		try {
			connection.close();
		} catch (Exception ignore) {
			System.err.println(ignore);
		}
	}

}
