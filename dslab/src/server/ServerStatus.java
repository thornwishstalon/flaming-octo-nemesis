package server;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;

import network.rmi.RMIRegistry;
import analyticsServer.event.Event;
import analyticsServer.remote.AnalyticsServer;
import billingServer.remote.BillingServer;
import billingServer.remote.BillingServerSecure;

/**
 * Singelton
 *
 */
public class ServerStatus {
	private static ServerStatus instance=null;
	private ServerSetup setup=null;

	//RMI interfaces

	private BillingServerSecure billingServerSecure=null;
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

		Registry registry =RMIRegistry.getRegistry();


		try {
			//###########################
			// billing server

			BillingServer billingServer = (BillingServer) registry.lookup(setup.getBillingServerName());
			System.out.println("connection to billing server established.");

			billingServerSecure=billingServer.login(setup.getUsername().trim(), setup.getPassword().trim());

			if(billingServerSecure!=null)
				System.out.println("login on billing server complete.");
			//System.out.println(billingServerSecure.getPriceSteps());

			//###########################
			// analytics server

			analyticsServer= (AnalyticsServer) registry.lookup(setup.getAnalyticsServerName());
			System.out.println("connection to analytics server established.");

		} catch (AccessException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("the service is was not available via the registry!");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("an error has occured!");
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("a server is unreachable!");
		}



	}



	public BillingServerSecure getBillingServer(){
		return billingServerSecure;
	}

	public synchronized void notifyAnalyticsServer(Event e){
		if(analyticsServer!=null){
			try {
				analyticsServer.processEvent(e);
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				analyticsServer=null;

				System.out.println("analytics-server unreachable! -- "+e.toString());
				//e1.printStackTrace();
			}
		}else System.out.println("analytics-server unreachable! -- "+e.toString());

	}

	public synchronized void billAuction(String name, long auctionID, double price) {
		if(billingServerSecure!=null){
			try {
				//System.out.println("trying to BILL AUCTION "+auctionID);
				billingServerSecure.billAuction(name, auctionID, price);
			} catch (RemoteException e) {
				billingServerSecure =null;
				System.out.println("billing-server unreachable.");
				//e.printStackTrace();
			}
		}else System.out.println("billing-server unreachable.");

	}

	public void logout() {
		if(billingServerSecure!=null){
			try {
				System.out.println("logging out from billing-server");
				billingServerSecure.logout();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
		}

	}


}
