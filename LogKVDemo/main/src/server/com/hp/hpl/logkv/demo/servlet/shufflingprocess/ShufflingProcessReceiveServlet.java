package com.hp.hpl.logkv.demo.servlet.shufflingprocess;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hp.hpl.logkv.demo.jms.JMSReceiver;
import com.hp.hpl.logkv.ingestkv.ShuffleStatus;
import com.hp.hpl.logkv.ui.ShuffleStatusResponse;

/**
 * Shuffling process servlet.
 * @author Edmond
 */
public class ShufflingProcessReceiveServlet extends HttpServlet {

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
		String responseQueueName = config.getInitParameter("responseQueueName");
		responseReceiveTimeout = Integer.parseInt(config.getInitParameter("responseReceiveTimeout"));

		jMSReceiver = new JMSReceiver(responseQueueName);

	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ObjectInputStream reader = null;

		ObjectOutputStream writer = null;

		try {

			reader = new ObjectInputStream(request.getInputStream());

			String requestClientId = (String) reader.readObject();

			reader.close();

			reader = null;

			Message message = jMSReceiver.receive(requestClientId, responseReceiveTimeout);

			if (message instanceof ObjectMessage) {

				ObjectMessage objectMessage = (ObjectMessage) message;
				Serializable obj = objectMessage.getObject();

				response.setContentType("application/x-java-serialized-object");

				writer = new ObjectOutputStream(response.getOutputStream());

				writer.writeObject(obj);
				writer.flush();

			} else if (message == null) {

				// prevent GUI dead

				response.setContentType("application/x-java-serialized-object");

				writer = new ObjectOutputStream(response.getOutputStream());

				ShuffleStatusResponse responseObj = new ShuffleStatusResponse(System.currentTimeMillis());

				responseObj.setIngestedTRUs(new Long[] {});
				responseObj.setShuffleStatuses(new ShuffleStatus[] {});

				writer.writeObject(responseObj);
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


		if (jMSReceiver != null) {
			jMSReceiver.release();
		}

		super.destroy();
	}

}
