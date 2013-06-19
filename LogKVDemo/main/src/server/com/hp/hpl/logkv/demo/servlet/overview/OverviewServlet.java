package com.hp.hpl.logkv.demo.servlet.overview;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.UUID;

import javax.jms.DeliveryMode;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hp.hpl.logkv.demo.jms.JMSReceiver;
import com.hp.hpl.logkv.demo.jms.JMSSender;

/**
 * Overview servlet.
 * @author Edmond
 */
public class OverviewServlet extends HttpServlet {

	/**
	 * JMS sender.
	 */
	private JMSSender jmsSender;

	/**
	 * JMS sender.
	 */
	private JMSReceiver jMSReceiver;

	/**
	 * response receive timeout.
	 */
	private int responseReceiveTimeout;

	/**
	 * serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		String requestQueueName = config.getInitParameter("requestQueueName");
		int requestMessageTimeToLive = Integer.parseInt(config.getInitParameter("requestMessageTimeToLive"));
		String responseQueueName = config.getInitParameter("responseQueueName");
		responseReceiveTimeout = Integer.parseInt(config.getInitParameter("responseReceiveTimeout"));

		jmsSender = new JMSSender(requestQueueName, DeliveryMode.PERSISTENT, requestMessageTimeToLive);
		jMSReceiver = new JMSReceiver(responseQueueName);
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ObjectInputStream reader = null;

		ObjectOutputStream writer = null;

		try {

			reader = new ObjectInputStream(request.getInputStream());

			Serializable requestObj = (Serializable) reader.readObject();

			reader.close();

			reader = null;

			String requestClientID = UUID.randomUUID().toString();

			jmsSender.send(requestClientID,  requestObj);

			Message message = jMSReceiver.receive(requestClientID, responseReceiveTimeout);

			if (message instanceof ObjectMessage) {

				ObjectMessage objectMessage = (ObjectMessage) message;
				Serializable obj = objectMessage.getObject();

				response.setContentType("application/x-java-serialized-object");

				writer = new ObjectOutputStream(response.getOutputStream());

				writer.writeObject(obj);
				writer.flush();

			} else {
				System.err.println("message[" + message + "] can't cast to ObjectMessage type.");
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {

			if (reader != null) {
				try {
					reader.close();
				} catch (Exception ignore) {
					System.err.println(ignore);
				}
			}

			if (writer != null) {
				try {
					writer.close();
				} catch (Exception ignore) {
					System.err.println(ignore);
				}
			}

		}

	}


	@Override
	public void destroy() {

		if (jmsSender != null) {
			jmsSender.release();
		}

		if (jMSReceiver != null) {
			jMSReceiver.release();
		}

		super.destroy();
	}

}
