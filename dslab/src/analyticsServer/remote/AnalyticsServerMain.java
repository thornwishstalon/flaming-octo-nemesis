package analyticsServer.remote;

import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import network.rmi.RMIRegistry;
import billingServer.remote.BillingServer;
import analyticsServer.db.StatisticEventsDATABASE;
import analyticsServer.event.Event;

public class AnalyticsServerMain implements IAnalyticsServer {

	private static IAnalyticsServer server, stub;
	private static StatisticEventsDATABASE statisticEvents;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		server = new AnalyticsServerMain();
		statisticEvents = new StatisticEventsDATABASE();
		
        /*
         * make AnalyticsServer available for clients via RMI-Registry
         */
        try {
        	
        	// get registry & bind remote-obj
			stub = (IAnalyticsServer) UnicastRemoteObject.exportObject(server, 0);
			Registry registry = RMIRegistry.getRegistry(); 
	        registry.rebind(args[0], stub);
	 
	        System.out.println("AnalyticsServer bound");
	        
		} catch (RemoteException e) {
			System.out.println("Binding of AnalyticsServer was not successful!");
			e.printStackTrace();
		}
	}
	
	public void subscribe(String regEx) {
		
	}

	public void processEvent(Event event) {
		
		switch(event.getType()) {
		case "bla":
			
		case "blo":

		}
	}
	
	public void unsubscribe(String regEx) {
		
	}
	
}
