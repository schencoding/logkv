package com.hp.hpl.logkv.ingestkv;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.hp.hpl.logkv.conf.Parameter;
import com.hp.hpl.logkv.util.SerializeHelper;

public class IngestKVReplicaReceiver extends Thread {

	private ServerSocket socketServer = null;

	public IngestKVReplicaReceiver() {
		try {
			socketServer = new ServerSocket(Parameter.INGEST_KV_REPLICA_RECEVIER_PORT);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void run() {
		while (!this.isInterrupted()) {
			try {
				Socket socket = socketServer.accept();
				IngestKVReplicaReceiverTask task = new IngestKVReplicaReceiverTask();
				task.setSocket(socket);
				task.start();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public class IngestKVReplicaReceiverTask extends Thread {
		Socket socket = null;

		public void setSocket(Socket socket) {
			this.socket = socket;
		}

		@Override
		public void run() {
			try {
				BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
				String clientHostName = socket.getInetAddress().getHostAddress();

				byte[] truIdBytes = new byte[8];
				int len = bis.read(truIdBytes);
				long truId = SerializeHelper.bytesToLong(truIdBytes);
				FileOutputStream fos = new FileOutputStream(Parameter.RECEIVED_INGEST_KV_REPLICA_DIR + clientHostName + "_" + truId);
				fos.write(truIdBytes);
				byte[] bytes = new byte[Parameter.BUFFER_SIZE_FOR_INGEST_KV_REPLICA_RECEIVER];
				len = 0;
				while ((len = bis.read(bytes)) != -1) {
					fos.write(bytes, 0, len);
				}
				
				if (fos != null) {
					fos.flush();
					fos.close();
				}
				if (socket != null) {
					socket.close();
				}				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
}
