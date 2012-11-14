package billingServer.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;

import billingServer.db.PriceSteps;

public interface BillingServerSecure extends Remote{
<<<<<<< HEAD
	
	
=======
	public PriceSteps getPriceSteps();
	public void createPriceStep(double startPrice, double endPrice, double fixedPrice, double variablePricePercent) throws RemoteException;
	public void deletePriceStep(double startPrice, double endPrice) throws RemoteException;
>>>>>>> c1c5d6fd55a8651025ced455f12cec75a84a35a3
}
