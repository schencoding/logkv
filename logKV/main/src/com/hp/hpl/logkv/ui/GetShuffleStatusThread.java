package com.hp.hpl.logkv.ui;

import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import com.hp.hpl.logkv.coordinator.CoordinatorAccessor;
import com.hp.hpl.logkv.demo.jms.JMSSender;
import com.hp.hpl.logkv.ingestkv.ShuffleStatus;
import com.hp.hpl.logkv.util.Util;

public class GetShuffleStatusThread implements Runnable {

	private static CoordinatorAccessor coordinatorAccessor = new CoordinatorAccessor();

	private JMSSender sender = null;
	private String requestID = null;
	private Map<String, Long> lastHeartBeatTimes = null;
	boolean bEnd = false;
	private static long startTRU = -1L;	

	static{
		try {
			startTRU = coordinatorAccessor.getStartupTRU();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		Timer timer = new Timer("Check Live Status");
		timer.schedule(new CheckRequestLiveStatusTask(), 0, 60 * 1000);
		
		while (!bEnd) {
			try {
				Util.log("Fetch shuffle status.", this.getClass());
				ShuffleStatus[] statuses = coordinatorAccessor.getShuffleStatuses();
				for(int i = 0; i < statuses.length; i++ ){
					long oldTruId = statuses[i].getTruId();
					long newTruId = oldTruId - this.startTRU;
					statuses[i].setTruId(newTruId);
				}
				Long[] ingestedTrus = coordinatorAccessor.getIngestedTRUs();
				for(int i = 0; i < ingestedTrus.length; i++ ){
					long oldTruId = ingestedTrus[i];
					long newTruId = oldTruId - this.startTRU;
					ingestedTrus[i] = newTruId;
				}
				
				if (statuses.length > 0) {
					ShuffleStatusResponse sss = new ShuffleStatusResponse(System.currentTimeMillis());
					sss.setShuffleStatuses(statuses);
					sss.setIngestedTRUs(ingestedTrus);
					sender.send(requestID, sss);
					Util.log("Send out a response message: " + sss, this.getClass());			
				}
				Thread.sleep(2 * 1000);
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void setSender(JMSSender sender) {
		this.sender = sender;
	}

	public void setRequestId(String requestID) {
		this.requestID = requestID;
	}
	
	public class CheckRequestLiveStatusTask extends TimerTask{

		@Override
		public void run() {			
			Long t= lastHeartBeatTimes.get(requestID);
			long current = System.currentTimeMillis();
			if(current - t > 65 * 1000){
				bEnd = true;
				Util.log("STOPPED send ShuffleStatus out.", this.getClass());
				this.cancel();
			}			
		}		
	}

	public void setLastHeartBeatTimes(Map<String, Long> lastHeartBeatTimes) {
		this.lastHeartBeatTimes = lastHeartBeatTimes;		
	}
	
}
