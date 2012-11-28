package analyticsServer.remote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import network.rmi.SubscriberCallback;
import analyticsServer.db.StatisticEventsDATABASE;
import analyticsServer.db.content.RegExpHelper;
import analyticsServer.db.content.Subscription;
import analyticsServer.event.Event;

public class AnalyticsServerImpl implements AnalyticsServer {
	private ArrayList<Subscription> subscriptions;
	private StatisticEventsDATABASE statisticEvents;

	public AnalyticsServerImpl(StatisticEventsDATABASE statisticEvents) {
		
		this.subscriptions= new ArrayList<Subscription>();
		this.statisticEvents = statisticEvents;
	}
	
	@Override
	public synchronized long subscribe(String regex, SubscriberCallback client)
			throws RemoteException {
		
		regex = regex.replaceAll("'", "");
		
		Subscription s = new Subscription(regex, client);
		subscriptions.add(s);
		return s.getId();
	}

	@Override
	public synchronized void processEvent(Event event) throws RemoteException {

		ArrayList<Event> eventNotifications = new ArrayList<Event>();
		eventNotifications.add(event);

		if(RegExpHelper.isEventType("(AUCTION_.*)", event)) {
			eventNotifications = statisticEvents.processAuctionEvent(eventNotifications);
		} else if(RegExpHelper.isEventType("(USER_.*)", event)) {
			eventNotifications = statisticEvents.processUserEvent(eventNotifications);
		} else if(RegExpHelper.isEventType("(BID_.*)", event)) {
			eventNotifications = statisticEvents.processBidEvent(eventNotifications);
		}


		for (Subscription sub : subscriptions) { // All subscriptions
			for(Event e : eventNotifications ) { // All events
				if(RegExpHelper.isEventType(sub.getRegex(), e)) {
					try{
						
						sub.getClient().notify(e);   // Callback for matches
						
					}catch(RemoteException ex){
						// nothing to do here right now TODO
					}
				}
			}
		}

	}

	@Override
	public synchronized void unsubscribe(long id) throws RemoteException {
		
		for (Subscription sub : subscriptions) {		
			if(sub.getId() == id) {
				subscriptions.remove(sub);
				break;
			}
		} 
	}
	
	
	public synchronized void killSubscriptions(){
		System.out.println("kill subs...");
		for(Subscription sub: subscriptions){
			try {
				System.out.println("subID: "+sub.getId()+"...");
				sub.getClient().terminate();
				
				System.out.println("\t...removed");
			} catch (RemoteException e) {
				System.out.println("..remove error");
				//e.printStackTrace();
			}
		}
	}

}
