package analyticsServer.db;

import java.sql.Timestamp;
import java.util.ArrayList;

import analyticsServer.db.content.BidAggregated;
import analyticsServer.db.content.RegExpHelper;
import analyticsServer.db.content.UserAggregated;
import analyticsServer.event.*;

public class StatisticEventsDATABASE {

	private UserAggregated userAggregated;
	private BidAggregated bidAggregated;
	
	
	private Timestamp sessionStarted;
	
	public StatisticEventsDATABASE() {
		sessionStarted = new Timestamp(System.currentTimeMillis());
		userAggregated = new UserAggregated();
		bidAggregated = new BidAggregated();
	}

	public ArrayList<Event> processUserEvent(ArrayList<Event> eventNotifications) {
		try {
			UserEvent u = (UserEvent) eventNotifications.get(0);
			
			if(RegExpHelper.isEventType("(USER_LOGIN.*)", u)) {
				userAggregated.loginUser(u.getUsername(), u.getTimestamp());			
			} else {
				userAggregated.logoutUser(u.getUsername(), u.getTimestamp());
				// Events are created -> USER_SESSIONTIME_MIN, USER_SESSIONTIME_MAX, USER_SESSIONTIME_AVERAGE
				eventNotifications.add(EventFactory.createStatisticsEvent(userAggregated.getTimeMin(), 0));
				eventNotifications.add(EventFactory.createStatisticsEvent(userAggregated.getTimeMax(), 1));
				eventNotifications.add(EventFactory.createStatisticsEvent(userAggregated.getTimeAVG(), 2));
			}			
			
		} catch (Exception e1) {
			System.out.println("Wrong event-type. Could not process " + eventNotifications.get(0).getType());
		}
		return eventNotifications;
	}
	
	
	public ArrayList<Event> processBidEvent(ArrayList<Event> eventNotifications) {
		try {
			BidEvent b = (BidEvent) eventNotifications.get(0);
			
			if(RegExpHelper.isEventType("(BID_WON.*)", b)) {
				//? What to do if bid was won ?
			} else {
				//? Do place and overbid both incremet the bid count ?
				bidAggregated.bidPlaced(b.getPrice());
				eventNotifications.add(EventFactory.createStatisticsEvent(bidAggregated.getBidPriceMax(), 4));
				eventNotifications.add(EventFactory.createStatisticsEvent(bidAggregated.getBidCountPerMinute(), 3));
			}			
			
		} catch (Exception e1) {
			System.out.println("Wrong event-type. Could not process " + eventNotifications.get(0).getType());
		}
		
		return eventNotifications;
	}

}
