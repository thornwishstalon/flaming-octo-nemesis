package server.logic;

import java.net.InetAddress;
import java.util.HashMap;

public class UserDATABASE {
	public final static int NO_USER_WITH_THAT_NAME=0;
	public final static int SUCCESSFULLY_LOGGED_IN=1;
	public final static int ALREADY_LOGGED_IN=2;
	public final static int SUCCESSFULLY_LOGGED_OUT=3;
	
	//public static int AUCTION_EXPIRED=3;
	
	private int idCounter=0;
	private static UserDATABASE instance=null;
	private HashMap<String, User> users;
	
	private UserDATABASE(){
		users= new HashMap<String, User>();
	}
	
	public static UserDATABASE getInstance(){
		if(instance==null)
			instance= new UserDATABASE();
		return instance;
	}
	
	public synchronized int loginUser(String username, InetAddress inetAddress, int port){
		User user=users.get(username);
		if((user == null)){
			user= new User(username);
			user.setLoggedIn(true);
			user.setID(++idCounter);
			user.setAddress(inetAddress);
			user.setPort(port);
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
			System.out.println("new user added to DATABASE");
			return SUCCESSFULLY_LOGGED_IN;
			
		}else if((user != null)&&(user.isLoggedIn())){
			return ALREADY_LOGGED_IN;
		}else{
			user.setLoggedIn(true);
			user.setAddress(inetAddress);
			user.setPort(port);
			if(user.hasNotifications()){
				user.startNotification(500);
			}
			return SUCCESSFULLY_LOGGED_IN;
		}
	}
	
	public synchronized User getUser(String username){
		return users.get(username); 
	}
	
	public synchronized int logout(String username){
		User user=users.get(username);
		if((user == null)){
			return NO_USER_WITH_THAT_NAME;
			
		}else{
			user.setLoggedIn(false);
			user.setAddress(null);
			user.setPort(0);
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
	
	
	
}
