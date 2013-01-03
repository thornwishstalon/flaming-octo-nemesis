package server.logic;

import network.tcp.server.TCPServerConnection;

public class Group extends User {

	public Group(String name) {
		super("group");

	}
	
	@Override
	public void addNotification(UserNotification note) {
		// TODO Auto-generated method stub
		//UserDATABASE.getInstance().notifyLoggedInUsers(note.getMessage());	
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

}
