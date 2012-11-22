package analyticsServer.db.content;

import analyticsServer.event.Event;

public class RegExpHelper {
	
	public static boolean isEventType(String regEx, Event e) {
		
		String type = e.getType();
		
		if(type.matches(regEx))
			return true;
		
		return false;
	}
	
}
