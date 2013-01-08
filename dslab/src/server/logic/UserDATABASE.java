package server.logic;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.List;

import server.ServerStatus;

import analyticsServer.event.Event;
import analyticsServer.event.EventFactory;

import network.tcp.server.TCPServerConnection;

public class UserDATABASE {
	public final static int NO_USER_WITH_THAT_NAME=0;
	public final static int SUCCESSFULLY_LOGGED_IN=1;
	public final static int ALREADY_LOGGED_IN=2;
	public final static int SUCCESSFULLY_LOGGED_OUT=3;
	public final static int SUCCESSFULLY_LOGGED_IN_HAS_NOTIFICATIONS=4;

	//public static int AUCTION_EXPIRED=3;

	private int idCounter=0;
	private static UserDATABASE instance=null;
	private HashMap<String, User> users;
	private int activeUsers=0;

	private UserDATABASE(){
		users= new HashMap<String, User>();
		
		users.put("group", new Group("group")); // add Group- User
	}

	public static UserDATABASE getInstance(){
		if(instance==null)
			instance= new UserDATABASE();
		return instance;
	}

	public synchronized int loginUser(String username, InetAddress inetAddress, int port, TCPServerConnection connection){
		User user=users.get(username);
		Event e= null;
		if((user == null)){
			user= new User(username);
			user.setLoggedIn(true);
			user.setID(++idCounter);
			user.setAddress(inetAddress);
			user.setPort(port);
			user.startTimer();
			user.setConnection(connection);
			users.put(username, user);

			/*
			try {
				user.notify("udp says: hey, bitch!");
			} catch (SocketException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 */

			e= EventFactory.createUserEvent(username, 0);
			notifyAnalytics(e);

			System.out.println("new user added to DATABASE");
			return SUCCESSFULLY_LOGGED_IN;

		}else if((user != null)&&(user.isLoggedIn())){
			return ALREADY_LOGGED_IN;
		}else if(user.hasNotifications()){
			user.setLoggedIn(true);
			user.setAddress(inetAddress);
			user.setPort(port);
			user.setConnection(connection);
			user.startTimer();

			e= EventFactory.createUserEvent(username, 0);
			notifyAnalytics(e);

			return SUCCESSFULLY_LOGGED_IN_HAS_NOTIFICATIONS;
		}
		else{
			user.setLoggedIn(true);
			user.setAddress(inetAddress);
			user.setPort(port);
			user.setConnection(connection);
			user.startTimer();

			e= EventFactory.createUserEvent(username, 0);
			notifyAnalytics(e);

			return SUCCESSFULLY_LOGGED_IN;
		}
	}

	public synchronized User getUser(String username){
		return users.get(username); 
	}

	public synchronized int logout(String username){
		User user=users.get(username);
		Event e=null;

		if((user == null)){
			return NO_USER_WITH_THAT_NAME;

		}else{
			user.stopTimer();
			user.setLoggedIn(false);
			user.setAddress(null);
			user.setPort(0);

			e= EventFactory.createUserEvent(username, 1);
			notifyAnalytics(e);

			return SUCCESSFULLY_LOGGED_OUT;
		}
	}

	public synchronized String getUserList(){
		String result="";
		User user=null;
		for(String key: users.keySet()){
			user= users.get(key);
			result+= user.getFullDescription()+"\n";
		}

		return result;
	}
	
	public synchronized String getClientList(){
		String result="";
		String user="";
		
		for(String key: users.keySet()){
			user= users.get(key).getClientDescription();
			if(!user.equals(""))
				result+=user+"\n";
		}
		
		result.substring(0,result.lastIndexOf('\n'));
		
		return result;
	}

	public synchronized String getNotifactions(String username){
		String answer="";

		for(UserNotification note : users.get(username).getNotifications()){
			answer += "!print "+note.getMessage()+"/n";
		}


		return answer; 

	}

	private  synchronized void notifyAnalytics(Event e){
		ServerStatus.getInstance().notifyAnalyticsServer(e);
	}
	
	public synchronized void killUsers(){
		User tmp=null;
		for(String key: users.keySet()){
			tmp= users.get(key);
			tmp.stopTimer();
		}
	}
	
	public synchronized void notifyLoggedInUsers(String note){
		activeUsers=0;
		User tmp=null;
		for(String key: users.keySet()){
			tmp= users.get(key);
				if(tmp.isLoggedIn()){
					activeUsers++;
					//System.out.println(tmp.getName()+" notified: "+note);
					tmp.addNotification(NotificationFactory.createNotification(note));
				}
		}		
	}
	
	public synchronized int getActiveUsers(){
		return activeUsers;
	}

}
