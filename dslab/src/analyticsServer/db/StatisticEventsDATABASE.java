package analyticsServer.db;

import java.sql.Timestamp;

import analyticsServer.db.content.RegExpHelper;
import analyticsServer.db.content.UserSessiontime;
import analyticsServer.event.*;

public class StatisticEventsDATABASE {

	private UserSessiontime userSessiontime;
	
	
	private Timestamp sessionStarted;
	
	public StatisticEventsDATABASE() {
		sessionStarted = new Timestamp(System.currentTimeMillis());
		userSessiontime = new UserSessiontime();
	}

	public void processUserEvent(Event e) {
		try {
			UserEvent u = (UserEvent) e;
			
			if(RegExpHelper.isEventType("(USER_LOGIN.*)", u)) {
				userSessiontime.loginUser(u.getUsername(), u.getTimestamp());			
			} else {
				userSessiontime.logoutUser(u.getUsername(), u.getTimestamp());	
			}			
			
		} catch (Exception e1) {
			System.out.println("Wrong event-type. Could not process " + e.getType());
		}
	}

	


}
