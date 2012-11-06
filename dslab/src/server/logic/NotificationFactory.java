package server.logic;

public class NotificationFactory {
	private static int counter=0;
	
	public static UserNotification createNotification(String message){
		return new UserNotification(++counter, message);
		
	}
}
