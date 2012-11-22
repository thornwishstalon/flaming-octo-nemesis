package analyticsServer.remote;

import java.rmi.RemoteException;
import java.util.ArrayList;

import network.rmi.SubscriberCallback;
import analyticsServer.db.StatisticEventsDATABASE;
import analyticsServer.db.content.RegExpHelper;
import analyticsServer.event.Event;

public class AnalyticsServerImpl implements AnalyticsServer {
	//TESTING ONLY
	private ArrayList<SubscriberCallback> subscriptions;
	private StatisticEventsDATABASE statisticEvents;

	public AnalyticsServerImpl(StatisticEventsDATABASE statisticEvents) {
		this.subscriptions= new ArrayList<SubscriberCallback>();
		this.statisticEvents = statisticEvents;
	}
	
	@Override
	public synchronized void subscribe(String regex, SubscriberCallback client)
			throws RemoteException {
		//TESTING ONLY
		subscriptions.add(client);

	}

	@Override
	public synchronized void processEvent(Event event) throws RemoteException {
		
		if(RegExpHelper.isEventType("(AUCTION_.*)", event)) {
			
		} else if(RegExpHelper.isEventType("(USER_.*)", event)) {
			statisticEvents.processUserEvent(event);
		} else if(RegExpHelper.isEventType("(BID_.*)", event)) {
			
		}
		
		
		// Testing - > Callback
		 for (SubscriberCallback sub : subscriptions) {
			sub.notify(event);
		}

	}

	@Override
	public synchronized void unsubscribe(long id) throws RemoteException {
		// TODO Auto-generated method stub

	}

}
