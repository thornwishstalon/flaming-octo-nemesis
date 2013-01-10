package server.logic;

import network.tcp.server.TCPServerConnection;

public class Group extends User {

	public Group(String name) {
		super("group");

	}
	
	@Override
	public synchronized void addNotification(UserNotification note) {
		System.out.println("group.addNotification");
		UserDATABASE.getInstance().notifyLoggedInUsers(note.getMessage());	
	}
	
	
	@Override
	public boolean isLoggedIn() {
		return UserDATABASE.getInstance().getActiveUsers() > 0;
	}
	
	@Override
	public void clearNotifications() {
		// nothing to do here
	}
	
	@Override
	public TCPServerConnection getConnection() {
		return null;
	}
	
	@Override
	public String getName() {
		return super.getName();
	}
	
	@Override
	public void stopTimer() {
		// nothing to do here
	//	super.stopTimer();
	}
	
	@Override
	public void startTimer() {
		// nothing to do here
		//super.startTimer();
	}

}
