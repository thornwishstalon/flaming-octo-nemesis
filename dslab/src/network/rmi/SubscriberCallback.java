package network.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

import analyticsServer.event.Event;

public interface SubscriberCallback extends Remote {
	public void notify(Event event) throws RemoteException;

}
