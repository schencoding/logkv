package com.hp.hpl.logkv.ingestkv;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.rmi.RemoteException;
import java.util.TreeMap;

import com.hp.hpl.logkv.coordinator.CoordinatorAccessor;
import com.hp.hpl.logkv.model.LocalTRU;
import com.hp.hpl.logkv.util.Util;

public class ShuffleSenderTask extends Thread {

	public static final int BUFFER_SIZE = 8096;

	private TreeMap<Long, LocalTRU> localTRUs;
	private ShuffleSender shuffleSender;
	private Socket socket = null;
	private static String myIpStr = null;
	private static String destIpStr = null;
	
	private CoordinatorAccessor coordinatorAccessor = new CoordinatorAccessor();
	
	static{
		myIpStr = Util.getMyIpStr();
	}

	public ShuffleSenderTask(TreeMap<Long, LocalTRU> localTRUs, ShuffleSender shuffleSender) {
		this.localTRUs = localTRUs;
		this.shuffleSender = shuffleSender;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {

		try {
			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			ShuffleRequest request = (ShuffleRequest) ois.readObject();
			long truId = request.getTruId();
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			dos.writeInt(1);
			dos.writeLong(truId);
			sendout(truId, dos);
			dos.flush();
			dos.close();
			shuffleSender.shuffleCompletionCallBack(truId);
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	public void sendout(Long truId, DataOutputStream dos) {
		if(socket != null){
			destIpStr = socket.getRemoteSocketAddress().toString();
			destIpStr = destIpStr.substring(1);
			destIpStr = destIpStr.split(":")[0];
		}
		LocalTRU localTRU = localTRUs.get(truId);
		if (localTRU != null) {
			try {
				try {
					ShuffleStatus shuffleStatus = new ShuffleStatus(myIpStr, destIpStr, truId, true);
					shuffleStatus.setSize(localTRU.getNumOfEvent());
					coordinatorAccessor.addShuffleStatus(shuffleStatus);
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
				FileInputStream fis = new FileInputStream(localTRU.getFileName());
				byte[] bytes = new byte[BUFFER_SIZE];
				int size = -1;
				while ((size = fis.read(bytes)) != -1) {
					dos.write(bytes, 0, size);
				}

				dos.flush();
				fis.close();

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			System.err.println("Cannot find TRU: " + truId + " in " + Util.getMyIpStr() );

			try {
				dos.flush();
				dos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
