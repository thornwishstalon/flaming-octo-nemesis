package network.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

import analyticsServer.event.Event;

/**
 * Remote interface used to notify a subscriber about 
 * certain events the subscriber is knowingly interested in.
 *
 */
public interface SubscriberCallback extends Remote {
	
	/** passes the event to the subscriber via RMI
	 * 
	 * @param event
	 * @throws RemoteException
	 */
	public void notify(Event event) throws RemoteException;
	
	/**
	 * terminates the remote object(-> also the RMI connection)
	 * 
	 * @throws RemoteException
	 */
	public void terminate() throws RemoteException;
}
