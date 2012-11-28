package analyticsServer.remote;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import network.rmi.RMIRegistry;
import billingServer.remote.BillingServer;
import analyticsServer.db.StatisticEventsDATABASE;
import analyticsServer.event.Event;

public class AnalyticsServerMain {

	private static AnalyticsServer server, stub;
	private static StatisticEventsDATABASE statisticEvents;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BufferedReader in=null;
		String input=null;
		
		statisticEvents = new StatisticEventsDATABASE();
		server = new AnalyticsServerImpl(statisticEvents);
		
		
        /*
         * make AnalyticsServer available for clients via RMI-Registry
         */
        try {
        	
        	// get registry & bind remote-obj
			stub = (AnalyticsServer) UnicastRemoteObject.exportObject(server, 0);
			Registry registry = RMIRegistry.getRegistry(); 
	        registry.rebind(args[0], stub);
	 
	        System.out.println("AnalyticsServer bound");
	        
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
	        
	        //close input-bufferedReader
	        try{
	        	in.close();
	        }catch(IOException e){
	        	
	        }
	        
	        //try {
	        	System.out.println("trying to end subscriptions");
	        	//end server... end callbacks
	        	((AnalyticsServerImpl) server).killSubscriptions();
	        	
	        	UnicastRemoteObject.unexportObject(server, true);
	        	
				//registry.unbind(args[0]);
				
				System.out.println("analyticsServer unbound");
			//} catch (NotBoundException e) {
				// TODO Auto-generated catch block
			//	e.printStackTrace();
			//}
	        
		} catch (RemoteException e) {
			System.out.println("Binding of AnalyticsServer was not successful!");
			e.printStackTrace();
		}
        
	}
	
	
}
