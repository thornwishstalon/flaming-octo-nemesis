package managementClient;


import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

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
	
	
	/**
	 * stubs are loaded from the registry
	 * 
	 * @param setup
	 */
	public void init(ManagementClientSetup setup){
		try {
			Registry registry = LocateRegistry.getRegistry();
			billingServer = (BillingServer) registry.lookup(setup.getBillingBindingName());
			
			//###########################
			// TODO Analytics server
			
			
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

	
	
	
}
