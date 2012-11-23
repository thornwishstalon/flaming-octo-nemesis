package analyticsServer.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;

import network.rmi.SubscriberCallback;

import analyticsServer.event.Event;


public interface AnalyticsServer extends Remote {
	public long subscribe(String regex, SubscriberCallback client) throws RemoteException;
	public void processEvent(Event event) throws RemoteException;
	public void unsubscribe(long id) throws RemoteException;
	
}
