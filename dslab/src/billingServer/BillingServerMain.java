package billingServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;


import network.rmi.RMIRegistry;
import billingServer.db.content.MD5Helper;
import billingServer.remote.BillingServer;
import billingServer.remote.BillingServerImpl;


//import billingServer.db.values.MD5Helper;

public class BillingServerMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BufferedReader in=null;
		String input=null;

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

			// get registry & bind remote-obj
			stub = (BillingServer) UnicastRemoteObject.exportObject(server,0);
			Registry registry = RMIRegistry.getRegistry();

			/*
			System.out.println("MD5 HELPER");
			System.out.println("x || " + MD5Helper.StringToMD5("x"));
			System.out.println("y || " + MD5Helper.StringToMD5("y"));
			System.out.println("z || " + MD5Helper.StringToMD5("z"));
			*/


			registry.rebind(setup.getBindingName(), stub);

			System.out.println("BillingServer bound");
			System.out.println("BillingServer READY!");

			in= new BufferedReader(new InputStreamReader(System.in));
			try {
				while((input= in.readLine())!=null){
					if(input.equals("!exit")){
						break;
					}
				}
			} catch (IOException e) {
				//e.printStackTrace();
			}
			
			System.out.println("ENDING billingServer");
			
			//close input-bufferedReader
			try {
				in.close();
			} catch (IOException e) {
				//e.printStackTrace();
			}

			
	//		try {
				//disconnect loggedin clients
				((BillingServerImpl) server).disconnect();
				
				System.out.println("unbind billing-service from registry...");
				UnicastRemoteObject.unexportObject(server, true);
				
				//registry.unbind(setup.getBindingName());
		//	} catch (NotBoundException e) {
				// TODO Auto-generated catch block
			//	e.printStackTrace();
			//}


		} catch (RemoteException e) {
			System.out.println("Binding of BillingServer was not successful!\n");
			e.printStackTrace();
		}
		

		System.out.println("\tGoodbye");
		
		


		//System.out.println(MD5Helper.StringToMD5("dslab2012"));

	}



}
