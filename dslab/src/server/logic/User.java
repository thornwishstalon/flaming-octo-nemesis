package server.logic;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;


import network.tcp.server.TCPServerConnection;


public class User {
	private int ID;
	private String name;
	private boolean loggedIn=false;
	private TCPServerConnection connection=null;
	
	private InetAddress address;
	private int port;
	
	private ArrayList<UserNotification> notifications;
	private Timer timer=null;
	
	public User(String name){
		notifications= new ArrayList<UserNotification>();
		this.name=name;
		
		//timer= new Timer();

		
		
	}
	
	private class MessageBrokerTask extends TimerTask{
		//private int checkInterval = 1000; //ms
		
		public MessageBrokerTask() {
			
		}
		@Override
		public void run() {
			//System.out.println("task says hi");
			if(hasNotifications()){
				connection.print(notifications);
				clearNotifications();
			}
			
		}
		
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
	
	
	public TCPServerConnection getConnection() {
		return connection;
	}

	public void setConnection(TCPServerConnection connection) {
		this.connection = connection;
	}
	
	public void startTimer(){
		timer= new Timer();
		timer.scheduleAtFixedRate(new MessageBrokerTask(), 100, 1000);
		
	}
	
	public void stopTimer(){
		timer.cancel();
		timer.purge();
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
	/*
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
	*/
	
	
	
}
