package billingServer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Properties;

import network.rmi.RMIRegistry;

import billingServer.remote.BillingServer;
import billingServer.remote.BillingServerImpl;
import billingServer.remote.BillingServerSecure;
import billingServer.remote.BillingServerSecureImpl;

//import billingServer.db.values.MD5Helper;

public class BillingServerMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BillingServerSetup setup= new BillingServerSetup(args);
		System.out.println(setup.toString());

		/*
		 * create server-class & stub
		 */
        BillingServer server = BillingServerImpl.getSingleInstance();
        BillingServer stub;
		
        /*
         * make BillingServer available for clients via RMI-Registry
         */
        try {
        	// get port-number & open registry
        	int port= Integer.parseInt(setup.getRegistryProperty("registry.port"));
        	System.out.println("port: " + port);
        	
        	// get registry & bind remote-obj
			stub = (BillingServer) UnicastRemoteObject.exportObject(server, 0);
			//BillingServerSecure stubSecure = (BillingServerSecure) UnicastRemoteObject.exportObject(BillingServerSecureImpl.getSingleInstance(), 0);
			Registry registry = RMIRegistry.getRegistry(port);
	        
	        
	        registry.rebind(setup.getBindingName(), stub);
	 
	        System.out.println("BillingServer bound");
	        
		} catch (RemoteException e) {
			System.out.println("Binding of BillingServer was not successful!");
			e.printStackTrace();
		}
        

		
		
		System.out.println("BillingServer READY!");
		
		//System.out.println(MD5Helper.StringToMD5("dslab2012"));

	}
	
	

}
