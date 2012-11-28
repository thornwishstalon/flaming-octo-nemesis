package analyticsServer.db.content;

import analyticsServer.event.Event;

public class RegExpHelper {
	
	public static boolean isEventType(String regEx, Event event) {
		
		String type = event.getType();
		
		try {
			if(type.matches(regEx))
				return true;
		} catch (Exception e1) {
			System.out.println("Invalid RegEx pattern '" +  regEx + "' was processed as no match found.");
		}
		
		return false;
	}
	
}
