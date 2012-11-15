package billingServer.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;

import billingServer.db.content.PriceSteps;

public interface BillingServerSecure extends Remote{
	public PriceSteps getPriceSteps() throws RemoteException;
	public void createPriceStep(double startPrice, double endPrice, double fixedPrice, double variablePricePercent) throws RemoteException;
	public void deletePriceStep(double startPrice, double endPrice) throws RemoteException;
}
