package com.hp.hpl.logkv.demo.applet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.URL;
import java.net.URLConnection;

/**
 * URL requester.
 * @author Edmond
 */
public class URLRequester {

	/**
	 * URL.
	 */
	private URL url;

	/**
	 * constructor method.
	 * @param url	URL
	 */
	public URLRequester(URL url) {
		this.url = url;
	}

	/**
	 * request.
	 * @param requestObj	request object
	 * @return	response object
	 */
	public Object request(Serializable requestObj) {

		System.out.println("request object : " + requestObj);

		Object result = null;

		ObjectOutputStream writer = null;
		ObjectInputStream reader = null;

		try {

			URLConnection connection = url.openConnection();
			connection.setRequestProperty("Content-type", "application/x-java-serialized-object");
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setUseCaches(false);
			connection.setDefaultUseCaches(false);
			connection.connect();
			writer = new ObjectOutputStream(connection.getOutputStream());
			writer.writeObject(requestObj);
			writer.flush();
			writer.close();
			writer = null;

			reader = new ObjectInputStream(connection.getInputStream());
			result = reader.readObject();

		} catch (Exception ex) {
			throw new RuntimeException(ex);
		} finally {

			if (writer != null) {
				try {
					writer.close();
				} catch (Exception ignore) {
					System.err.println(ignore);
				}
			}

			if (reader != null) {
				try {
					reader.close();
				} catch (Exception ignore) {
					System.err.println(ignore);
				}
			}
		}

		System.out.println("response object : " + result);

		return result;
	}

	/**
	 * post and no response.
	 * @param requestObj	request object
	 */
	public void post(Serializable requestObj) {

		System.out.println("request object : " + requestObj);

		ObjectOutputStream writer = null;

		try {

			URLConnection connection = url.openConnection();
			connection.setRequestProperty("Content-type", "application/x-java-serialized-object");
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setUseCaches(false);
			connection.setDefaultUseCaches(false);
			connection.connect();
			writer = new ObjectOutputStream(connection.getOutputStream());
			writer.writeObject(requestObj);
			writer.flush();
			writer.close();
			writer = null;

		} catch (Exception ex) {
			throw new RuntimeException(ex);
		} finally {

			if (writer != null) {
				try {
					writer.close();
				} catch (Exception ignore) {
					System.err.println(ignore);
				}
			}

		}
	}

}
