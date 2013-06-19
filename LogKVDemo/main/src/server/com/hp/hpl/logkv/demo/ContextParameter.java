package com.hp.hpl.logkv.demo;

import java.util.Properties;

import com.hp.hpl.logkv.demo.jms.JMSConnectionFactory;

/**
 * Context parameter.
 * @author Edmond
 */
public final class ContextParameter {

	/**
	 * prevent to instance.
	 */
	private ContextParameter() {
	}

	/**
	 * server IP.
	 */
	public static final String SERVER_IP;

	/**
	 * server port.
	 */
	public static final String SERVER_PORT;

	static {

		try {
			// load LogKV demo configuration
			Properties logKVDemoProperties = new Properties();
			logKVDemoProperties.load(JMSConnectionFactory.class.getResourceAsStream("/LogKVDemo.properties"));
			SERVER_IP = logKVDemoProperties.getProperty("serverIP");
			SERVER_PORT = logKVDemoProperties.getProperty("serverPort");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
