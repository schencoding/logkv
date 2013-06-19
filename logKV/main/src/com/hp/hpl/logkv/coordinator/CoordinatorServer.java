package com.hp.hpl.logkv.coordinator;

import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import com.hp.hpl.logkv.conf.Parameter;
import com.hp.hpl.logkv.util.Util;

public class CoordinatorServer {

	public static void main(String[] args) throws RemoteException, MalformedURLException, UnknownHostException {
		CoordinatorImpl coordinator = new CoordinatorImpl();
		LocateRegistry.createRegistry(Parameter.COORDINATOR_PORT);

		String myIp = Util.getMyIpStr();
		Util.log("Server IP: " + myIp, CoordinatorServer.class);
		String serviceURL = "rmi://" + myIp + ":" + Parameter.COORDINATOR_PORT + "/CoordinatorServer";
		Util.log("Service URL: " + serviceURL, CoordinatorServer.class);
		try {
			Naming.rebind(serviceURL, coordinator);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Util.log("Coordinator server @ " + myIp + " started.", CoordinatorServer.class);
	}

}
