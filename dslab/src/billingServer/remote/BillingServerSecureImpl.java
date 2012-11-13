/*
 * Implementation of the BillingServerSecure
 * Reacts to commands from the Man.Client.
 * 
 * Singleton Pattern.
 */

package billingServer.remote;

import java.rmi.RemoteException;

import billingServer.db.BillingServerBillDATABASE;
import billingServer.db.PriceSteps;
import billingServer.db.content.Bill;

public class BillingServerSecureImpl implements BillingServerSecure {

	private static BillingServerSecureImpl instance;
	private PriceSteps priceSteps;
	private BillingServerBillDATABASE billDATABASE;
	
	public static BillingServerSecureImpl getSingleInstance() {
		if(instance == null) {
			synchronized (BillingServerSecureImpl.class) {
				instance = new BillingServerSecureImpl();
			}
		}
		return instance;
	}
	
	// constructor
	private BillingServerSecureImpl() {
		priceSteps = PriceSteps.getSingleInstance();
		billDATABASE = new BillingServerBillDATABASE();
	}
	
	// create [!addStep]
	public void createPriceStep(double startPrice, double endPrice, double fixedPrice, double variablePricePercent) 
			throws RemoteException  {
		
		if(!priceSteps.createPriceStep(startPrice, endPrice, fixedPrice, variablePricePercent))
			throw new RemoteException();
	
	}
	
	// delete 
	public void deletePriceStep(double startPrice, double endPrice) 
			throws RemoteException {
		
		if(!priceSteps.deletePriceStep(startPrice, endPrice))
			throw new RemoteException();
		
	}

	public PriceSteps getPriceSteps() {
		return priceSteps;
	}

	public void billAuction(String user, long auctionID, double price) {
		billDATABASE.billAuction(user, auctionID, price);
	}
	
	public Bill getBill(String user) {
		return billDATABASE.getBill(user);
	}
	
}
