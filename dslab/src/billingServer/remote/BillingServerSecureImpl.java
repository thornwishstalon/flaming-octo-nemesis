/*
 * Implementation of the BillingServerSecure
 * Reacts to commands from the Man.Client.
 * 
 * Singleton Pattern.
 */

package billingServer.remote;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


import billingServer.db.BillingServerBillDATABASE;
import billingServer.db.BillingServerPriceDATABASE;
import billingServer.db.content.Bill;
import billingServer.db.content.PriceSteps;

public class BillingServerSecureImpl extends UnicastRemoteObject implements BillingServerSecure {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1520541085738561629L;
	//private static BillingServerSecureImpl instance;
	//private PriceSteps priceSteps;
	private BillingServerBillDATABASE billDATABASE;
	
//	public static BillingServerSecureImpl getSingleInstance() {
//		if(instance == null) {
//			synchronized (BillingServerSecureImpl.class) {
//				instance = new BillingServerSecureImpl();
//			}
//		}
//		return instance;
//	}
	
	
	
	// constructor
	public BillingServerSecureImpl() throws RemoteException {
		super();
		//priceSteps = PriceSteps.getSingleInstance();

		billDATABASE = new BillingServerBillDATABASE();
	}
	
	// create [!addStep]
	public synchronized void createPriceStep(double startPrice, double endPrice, double fixedPrice, double variablePricePercent) 
			throws RemoteException  {
		
		if(!BillingServerPriceDATABASE.getInstance().createPriceStep(startPrice, endPrice, fixedPrice, variablePricePercent))
			throw new RemoteException();
	
	}
	
	// delete 
	public synchronized void deletePriceStep(double startPrice, double endPrice) 
			throws RemoteException {
		
		if(!BillingServerPriceDATABASE.getInstance().deletePriceStep(startPrice, endPrice))
			throw new RemoteException();
		
	}


	public synchronized PriceSteps getPriceSteps() throws RemoteException{
		return BillingServerPriceDATABASE.getInstance().getPriceSteps();
	}

	public synchronized void billAuction(String user, long auctionID, double price) {
		billDATABASE.billAuction(user, auctionID, price);
	}
	
	

	@Override
	public synchronized Bill getBill(String user) throws RemoteException {
		return billDATABASE.getBill(user);
	}
	
}
