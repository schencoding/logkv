package com.hp.hpl.logkv.logsource;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

import com.hp.hpl.logkv.conf.Parameter;
import com.hp.hpl.logkv.model.Schema;
import com.hp.hpl.logkv.util.Util;

public class LogSourceRunner extends Thread {

	private ServerSocket socketServer = null;
	private Schema schema = null;
	private static LogSourceDescriptor logSourceDescriptor = null;
	private static LogSourceAgent logSourceAgent = null;

	public LogSourceRunner(Schema schema) {
		this.schema = schema;
		try {
			socketServer = new ServerSocket(Parameter.LOG_SOURCE_RUNNER_PORT);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void run() {
		while (!this.isInterrupted()) {
			try {
				Socket socket = socketServer.accept();
				LogSourceRunnerTask task = new LogSourceRunnerTask();
				task.setSocket(socket);
				task.start();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public class LogSourceRunnerTask extends Thread {

		private Socket socket = null;

		@Override
		public void run() {
			try {
				ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

				Object object = ois.readObject();
				if (object instanceof LogSourceDescriptor) {
					if (logSourceAgent != null) {
						logSourceAgent.close();
						logSourceAgent = null;
					}
					if (logSourceAgent == null) {
						logSourceDescriptor = (LogSourceDescriptor) object;
						logSourceAgent = new LogSourceAgent(logSourceDescriptor, schema);
						logSourceAgent.start();
						Util.log("LogSourceAgent started.", this.getClass());
					} else {
						logSourceAgent.updateLogSource(logSourceDescriptor);
						Util.log("LogSourceAgent updated.", this.getClass());
					}
				} /*
				 * else if (object instanceof LogSourceCollection) { if
				 * (logSourceAgent != null) { LogSourceCollection logSources =
				 * (LogSourceCollection) object; LogSourceDescriptor[]
				 * logSourceDescriptors = logSources.getLogSources(); for (int i
				 * = 0; i < logSourceDescriptors.length; i++) {
				 * LogSourceDescriptor descriptor = logSourceDescriptors[i]; if
				 * (descriptor.getLogSourceId() ==
				 * logSourceDescriptor.getLogSourceId()) {
				 * logSourceAgent.updateLogSource(descriptor); } } } }
				 */else {
					System.err.println("Unkown request.");
				}
				socket.close();

				/*
				 * if (logSourceAgent == null) { logSourceAgent = new
				 * LogSourceAgent(logSourceDescriptor, schema);
				 * logSourceAgent.start(); } else {
				 * logSourceAgent.updateLogSource(logSourceDescriptor); }
				 */
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}

		public Socket getSocket() {
			return socket;
		}

		public void setSocket(Socket socket) {
			this.socket = socket;
		}

	}

}
