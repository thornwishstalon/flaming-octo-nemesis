package server;

import analyticsServer.remote.AnalyticsServer;
import billingServer.remote.BillingServerSecure;

/**
 * Singelton
 *
 */
public class ServerStatus {
	private static ServerStatus instance=null;
	private ServerSetup setup=null;
	
	//RMI interfaces
	private BillingServerSecure billingServer=null;
	private AnalyticsServer analyticsServer=null;
	
	
	private ServerStatus(){
		
	}
	
	public static ServerStatus getInstance(){
		if(instance==null)
				instance=new ServerStatus();
		return instance;
	}
	
	public void init(ServerSetup setup){
		//get RMI references, initialize them
		this.setup=setup;
		
		//login on billing server
		
		//get analyticsServer 
		
		//.... TODO
		
		
	}
	
	
	
	public BillingServerSecure getBillingServer(){
		return billingServer;
	}
	
	public AnalyticsServer getAnalyticsServer(){
		return analyticsServer;
	}
	
	
}
