package analyticsServer.remote;

import java.rmi.RemoteException;
import java.util.ArrayList;

import network.rmi.SubscriberCallback;
import analyticsServer.event.Event;

public class AnalyticsServerImpl implements AnalyticsServer {
	//TESTING ONLY
	private ArrayList<SubscriberCallback> subscriptions;

	public AnalyticsServerImpl() {
		//TESTING ONLY
		this.subscriptions= new ArrayList<SubscriberCallback>();
	}
	
	@Override
	public synchronized void subscribe(String regex, SubscriberCallback client)
			throws RemoteException {
		//TESTING ONLY
		subscriptions.add(client);

	}

	@Override
	public synchronized void processEvent(Event event) throws RemoteException {
		//TESTING ONLY
		 for (SubscriberCallback sub : subscriptions) {
			sub.notify(event);
		}

	}

	@Override
	public synchronized void unsubscribe(long id) throws RemoteException {
		// TODO Auto-generated method stub

	}

}
