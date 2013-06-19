package com.hp.hpl.logkv.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

import com.hp.hpl.logkv.conf.Parameter;

public class Util {
	
	
	public static long getTruId(long time){
		long a = time / Parameter.TRU_SIZE;
		long b = time % Parameter.TRU_SIZE;
		if(b != 0){
			a += 1;
		}
		return a;
	}
	
	public static String getMyIpStr(){
		InetAddress localHost;
		try {
			localHost = InetAddress.getLocalHost();
			String myIp = localHost.getHostAddress();
			return myIp;
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return null;
	}	
	
	public static void log(String msg, Class cls){
		System.out.println("[t=" + System.currentTimeMillis()/1000 + "] @" + cls.getSimpleName() + " " + msg);
	}


	public static void err(String msg, Class cls) {
		System.err.println("[t=" + System.currentTimeMillis()/1000 + "] @" + cls.getSimpleName() + " " + msg);
	}

}
