package billingServer.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface BillingServer extends Remote {
	public BillingServerSecure login (String username, String password) throws RemoteException;
}
