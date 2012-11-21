package analyticsServer.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;

import analyticsServer.event.Event;

import managementClient.remote.SubscriberCallback;

public interface AnalyticsServer extends Remote {
	public void subscribe(String regex, SubscriberCallback client) throws RemoteException;
	public void processEvent(Event event) throws RemoteException;
	public void unsubscribe(long id) throws RemoteException;
	
}
