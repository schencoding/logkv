package com.hp.hpl.logkv.ingestkv;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.RemoteException;
import java.util.TreeMap;

import com.hp.hpl.logkv.conf.Parameter;
import com.hp.hpl.logkv.coordinator.CoordinatorAccessor;
import com.hp.hpl.logkv.model.LocalTRU;

public class ShuffleSender extends Thread{
	
	
	private CoordinatorAccessor coordinatorAccessor = new CoordinatorAccessor();
	private TreeMap<Long, LocalTRU> localTRUs = new TreeMap<Long, LocalTRU>();
	private ServerSocket socketServer = null;	
	private int numberOfActiveShuffling = 0;


	public ShuffleSender(TreeMap<Long, LocalTRU> localTRUs) {
		this.localTRUs = localTRUs;
		try {
			socketServer = new ServerSocket(Parameter.SHUFFLE_SENDER_PORT);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}  
	}

	@Override
	public void run() {
		while(! interrupted()){			
			try {
				Socket socket = socketServer.accept();
				if(numberOfActiveShuffling < Parameter.NUMBER_OF_CONCURRENT_SHUFF_THRES){
					ShuffleSenderTask task = new ShuffleSenderTask(localTRUs, this);
					task.setSocket(socket);
					task.start();
				}else{
					denyShuffle(socket);
				}
				
			}catch(Exception e){
				e.printStackTrace();
			}			
		}
	}
	
	private void denyShuffle(Socket socket) {
		try {
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			dos.write(-1);
			dos.flush();
			dos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

	public void shuffleCompletionCallBack(long truId){
		numberOfActiveShuffling -= 1;
		try {
			coordinatorAccessor.notifyShuffleFinished(truId);
			LocalTRU localTRU = localTRUs.remove(new Long(truId));
			if(localTRU != null){
				localTRU.close();
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	

}
