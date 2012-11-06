package server.logic;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collections;

import network.udp.server.UDPNotificationThread;

public class User {
	private int ID;
	private String name;
	private boolean loggedIn=false;
	
	private InetAddress address;
	private int port;
	
	private ArrayList<UserNotification> notifications;
	
	
	public User(String name){
		notifications= new ArrayList<UserNotification>();
		this.name=name;
	}
	
	public String getName() {
		return name;
	}
	

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}
	
	public boolean isLoggedIn() {
		return loggedIn;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}
	
	public void addNotification(UserNotification note){
		notifications.add(note);
		if(loggedIn)
			startNotification();
	}
	
	public ArrayList<UserNotification> getNotifications(){
		Collections.sort(notifications); //order by timestamp
		return notifications;
	}
	
	public void clearNotifications(){
		UserNotification note=null;
		for(int i=0; i<notifications.size();i++){
			note= notifications.get(i);
			if(note.isSent()){
				notifications.remove(i);
			}
		}
	}
	
	public synchronized boolean hasNotifications(){
		boolean notes=false;
		for(UserNotification n:notifications){
			if(!n.isSent()){
				notes=true;
				break;
			}
		}
		
		return notes;
	}
	
	public String getDescription(){
		return "ID: "+ID+" "+name+" ONLINE: "+loggedIn;
	}

	public InetAddress getAddress() {
		return address;
	}

	public void setAddress(InetAddress address) {
		this.address = address;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
	
	public String getFullDescription(){
		if(loggedIn)
			return "ID: "+ID+" "+name+" contact from: "+address.getHostName()+":"+port+"\n\tonline: "+loggedIn+", has notifiactions: "+hasNotifications();
		else return "ID: "+ID+" "+name+"\n\tonline: "+loggedIn+", has notifiactions: "+hasNotifications();
		
	}
	/*
	public void notify(String message) throws IOException, SocketException{
		byte[]data= message.getBytes();
		DatagramPacket packet= new DatagramPacket(data, data.length,address,port);
		DatagramSocket socket= new DatagramSocket();
		socket.send(packet);
		
		socket.close();
		
	}
	*/
	public synchronized void  startNotification() {
		System.out.println("notify user: "+name);
		UDPNotificationThread thread= new UDPNotificationThread(this);
		thread.start();
		clearNotifications();
	}
	

	public synchronized void  startNotification(int delay) {
		System.out.println("notify user: "+name);
		UDPNotificationThread thread= new UDPNotificationThread(this,delay);
		thread.start();
		clearNotifications();
	}
	
	
	
}
