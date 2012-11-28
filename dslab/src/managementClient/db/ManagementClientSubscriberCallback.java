package managementClient.db;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import analyticsServer.event.Event;
import network.rmi.SubscriberCallback;

/**
 * SubscriberCallback for the ManagementClient
 *
 */
public class ManagementClientSubscriberCallback extends UnicastRemoteObject implements SubscriberCallback {

	protected ManagementClientSubscriberCallback() throws RemoteException {
		super();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -954345295205926142L;

	@Override
	public void notify(Event event) throws RemoteException {
		//notify the EventDatabase
		EventDATABASE.getInstance().notify(event);

	}

	@Override
	public void terminate() throws RemoteException {
		this.unexportObject(this, true);
		try {
			this.finalize();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}
