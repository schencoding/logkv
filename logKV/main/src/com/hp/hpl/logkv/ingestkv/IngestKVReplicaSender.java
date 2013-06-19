package com.hp.hpl.logkv.ingestkv;

import java.io.BufferedOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.hp.hpl.logkv.conf.Parameter;

public class IngestKVReplicaSender extends Thread {
	private BlockingQueue<byte[]> dupQueue = new LinkedBlockingQueue<byte[]>();
	IngestKVReplicaSenderClient senderClient = null;
	private InetAddress inetAddress;
	private String replicaReceiverIp = null;

	public IngestKVReplicaSender(String replicaReceiverIp) {
		this.replicaReceiverIp = replicaReceiverIp;
		senderClient = new IngestKVReplicaSenderClient();
		try {
			inetAddress = InetAddress.getByName(replicaReceiverIp);
			senderClient.connect(inetAddress, Parameter.INGEST_KV_REPLICA_RECEVIER_PORT);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

	public void addBytes(byte[] bytes) {
		try {
			dupQueue.put(bytes);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while (!this.isInterrupted()) {
			byte[] bytes = null;
			try {
				bytes = dupQueue.take();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (bytes == null || bytes.length == 0) {
				break;
			}
			senderClient.send(bytes, 0, bytes.length);
		}
	}
	
	public void close(){
		senderClient.close();
	}
	

	public class IngestKVReplicaSenderClient {
		private Socket socket = null;
		private BufferedOutputStream bos = null;

		public void connect(InetAddress inetAddress, int listenerPort) {
			try {
				socket = new Socket(inetAddress, listenerPort);
				bos = new BufferedOutputStream(socket.getOutputStream());
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}

		public void send(byte[] bytes, int offset, int length) {
			try {
				bos.write(bytes, offset, length);
			} catch (Exception e) {
				e.printStackTrace();
				this.connect(inetAddress, Parameter.INGEST_KV_REPLICA_RECEVIER_PORT);
			}
		}

		public void close() {
			/*
			 * close the output stream
			 */
			if (bos != null) {
				try {
					bos.flush();
					bos.close();
				} catch (Exception ignore) {
					ignore.printStackTrace();
				}
			}

			/*
			 * close the socket
			 */
			if (socket != null) {
				try {
					socket.close();
				} catch (Exception ignore) {
					ignore.printStackTrace();
				}
			}
		}

	}

}
