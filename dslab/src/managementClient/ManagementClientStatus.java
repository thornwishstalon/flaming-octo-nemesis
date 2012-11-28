package managementClient;


import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.HashMap;

import managementClient.db.EventDATABASE;
import network.rmi.RMIRegistry;

import analyticsServer.remote.AnalyticsServer;
import billingServer.remote.BillingServer;
import billingServer.remote.BillingServerSecure;

/**
 * ManagementClient STATUS class, Singleton
 * 
 *
 */
public class ManagementClientStatus {
	private static ManagementClientStatus instance=null;
	private BillingServerSecure billingServerSecure=null;
	private BillingServer billingServer=null;
	private HashMap<Long,Long> subscriptionIDs;
	
	private AnalyticsServer analyticsServer=null;
	private String user="";
	
	
	/**
	 * stubs are loaded from the registry
	 * 
	 * @param setup
	 */
	public void init(ManagementClientSetup setup){
		subscriptionIDs= new HashMap<Long,Long>();
		try {
			Registry registry =RMIRegistry.getRegistry(11269);
			
			//###########################
			// billing server
			billingServer = (BillingServer) registry.lookup(setup.getBillingBindingName());
			
			//###########################
			// analytics server
			
			analyticsServer= (AnalyticsServer) registry.lookup(setup.getAnalyticsBindingName());
			System.out.println("connections established.");
			
			
		} catch (RemoteException e) {
			//e.printStackTrace();
			System.out.println("an Error has occured!");
		} catch (NotBoundException e) {
			//e.printStackTrace();
			System.out.println("Server unreachable!");
			try {
				System.in.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				//e1.printStackTrace();
			}
		}
		
		
	}
	
	/*
	 * GETTER
	 */
	
	public static ManagementClientStatus getInstance(){
		if(instance==null)
			instance= new ManagementClientStatus();
		return instance;
	}

	public BillingServerSecure getbillingServerSecure() {
		return billingServerSecure;
	}
	
	public BillingServer getBillingServer() {
		return billingServer;
	}

	/*
	 * SETTER
	 */
	public void setbillingServerSecure(BillingServerSecure billingServer) {
		this.billingServerSecure = billingServer;
	}

	

	public void setBillingServer(BillingServer billingServer) {
		this.billingServer = billingServer;
	}
	
	public AnalyticsServer getAnalyticsServer(){
		return analyticsServer;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
	
	public void addSubscription(long id){
		subscriptionIDs.put(id,id);
	}
	
	public void removeSubscription(long id){
		subscriptionIDs.remove(id);
	}
	
	private void terminateSubscribtions(){
		for(Long key:subscriptionIDs.keySet()){
			try {
				System.out.println("ending subscription "+key);
				
				analyticsServer.unsubscribe(key);
			} catch (RemoteException e) {
				//e.printStackTrace();
			}
		}
		
	}
	
	public void disconnect(){
		if(!user.equals("")){
			try {
			
				terminateSubscribtions();
				EventDATABASE.getInstance().killCallback();
				
				billingServerSecure.logout();
				billingServerSecure= null;
				
			} catch (RemoteException e) {
				//nothing to do...
			}
		}
	}
	

	
	
	
}
