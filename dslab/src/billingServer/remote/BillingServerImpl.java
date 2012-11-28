/*
 * Implements the BillingServer interface and takes care of user validation.
 * Delivers a reference to a BillingServerSecure after successful login.
 * Uses Singleton-Pattern.
 */

package billingServer.remote;

import java.rmi.NoSuchObjectException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import billingServer.db.BillingServerUserDATABASE;


public class BillingServerImpl implements BillingServer {

	private static BillingServerImpl instance;
	private BillingServerUserDATABASE users;
	private ArrayList<BillingServerSecure> secureConn;
	
	public static BillingServerImpl getSingleInstance() {
		if(instance == null) {
			synchronized (BillingServerImpl.class) {
				instance = new BillingServerImpl();
			}
		}
		return instance;
	}
	
	private BillingServerImpl() {
		users= new BillingServerUserDATABASE(); //init user-DB
		secureConn= new ArrayList<BillingServerSecure>();
	}
	
	/*
	 * (non-Javadoc)
	 * @see billingServer.remote.BillingServer#login(java.lang.String, java.lang.String)
	 * 
	 * @param username		name of management-user
	 * @param password		password of management-user
	 */
	@Override
	public synchronized BillingServerSecure login(String username, String password)
			throws RemoteException {
		
		synchronized (users) {
			
			if(users.verifyUser(username, password)) {
				BillingServerSecureImpl tmp = new BillingServerSecureImpl();
				secureConn.add(tmp);
				return tmp;
			} else {
				return null;
			}

		}
	}
	
	public synchronized void disconnect(){
		for(BillingServerSecure sec:secureConn){
			try {
				UnicastRemoteObject.unexportObject(sec, true);
			} catch (NoSuchObjectException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
		}
	}

	

}
