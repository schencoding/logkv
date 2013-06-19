package com.hp.hpl.logkv.demo.servlet.shufflingprocess;

import java.io.IOException;
import java.io.ObjectInputStream;

import javax.jms.DeliveryMode;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hp.hpl.logkv.demo.jms.JMSSender;
import com.hp.hpl.logkv.ui.ShuffleDestroyRequest;

/**
 * Shuffling process destroy servlet.
 * @author Edmond
 */
public class ShufflingProcessDestroyServlet extends HttpServlet {

	/**
	 * JMS sender.
	 */
	private JMSSender jmsSender;

	/**
	 * serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void init(ServletConfig config) throws ServletException {

		super.init(config);
		String requestQueueName = config.getInitParameter("requestQueueName");
		int requestMessageTimeToLive = Integer.parseInt(config.getInitParameter("requestMessageTimeToLive"));

		jmsSender = new JMSSender(requestQueueName, DeliveryMode.PERSISTENT, requestMessageTimeToLive);

	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ObjectInputStream reader = null;

		try {

			reader = new ObjectInputStream(request.getInputStream());

			ShuffleDestroyRequest requestObj = (ShuffleDestroyRequest) reader.readObject();

			reader.close();

			reader = null;

			jmsSender.send(requestObj.getRequestID(),  requestObj);

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

		}

	}


	@Override
	public void destroy() {

		if (jmsSender != null) {
			jmsSender.release();
		}

		super.destroy();
	}

}
