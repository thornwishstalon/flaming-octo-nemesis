package analyticsServer;

import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import network.rmi.RMIRegistry;

import analyticsServer.remote.AnalyticsServer;
import analyticsServer.remote.AnalyticsServerImpl;

public class AnalyticsServerMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		AnalyticsServerSetup setup= new AnalyticsServerSetup(args);
		
		AnalyticsServer server= new AnalyticsServerImpl();
		AnalyticsServer stub;
		
		try {
        	// get port-number & open registry
        	int port= Integer.parseInt(setup.getRegistryProperty("registry.port"));
        	System.out.println("port: " + port);
        	
        	// get registry & bind remote-obj
			stub = (AnalyticsServer) UnicastRemoteObject.exportObject(server,0);

			Registry registry = RMIRegistry.getRegistry(port);
	        

	        
	        registry.rebind(setup.getBindingName(), stub);
	 
	        System.out.println("AnalyticsServer bound");
	        
		} catch (RemoteException e) {
			System.out.println("Binding of BillingServer was not successful!\n");
			e.printStackTrace();
		}
        
	}

}
