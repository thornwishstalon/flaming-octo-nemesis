package client.command;

import server.logic.UserDATABASE;
import network.tcp.server.TCPServerConnection;
import command.ICommand;

public class Login implements ICommand {
	//!login alice
	private TCPServerConnection connection;

	public Login(TCPServerConnection connection) {
		this.connection=connection;
	}

	@Override
	public int numberOfParams() {
		return 1;
	}

	@Override
	public synchronized String execute(String[] params) {
		
		String username= params[0].trim();
		//String sPort= params[1].trim();
		//int port = 0;
		//try{
			//port= Integer.valueOf(sPort);
		//}catch(NumberFormatException e){
			//return ("!print "+sPort+" is not a number!");
		//}
		
		int x = UserDATABASE.getInstance().loginUser(username, connection.getInetAddress(), connection.getClientPort(),connection );//Integer.valueOf(port));
		switch(x){
		case UserDATABASE.NO_USER_WITH_THAT_NAME:
			
		case UserDATABASE.SUCCESSFULLY_LOGGED_IN: 
			connection.setUser(UserDATABASE.getInstance().getUser(username));
			return "!ack-login "+username;
			
		case UserDATABASE.ALREADY_LOGGED_IN: return "!print Error: user is already logged in!";

		case UserDATABASE.SUCCESSFULLY_LOGGED_IN_HAS_NOTIFICATIONS:
			connection.setUser(UserDATABASE.getInstance().getUser(username));
			return "!ack-login "+username+"\n"+UserDATABASE.getInstance().getNotifactions(username);
		}

		return "!print ERROR: something is missing here";
	}

	@Override
	public boolean needsRegistration() {
		return false;
	}

	
	/*
	 * 
	 * return "Successfully logged in as "+username;

		}else if((user != null)&&(user.isLoggedIn())){
			return "Error: user is already logged in!";
		}else{
			user.setLoggedIn(true);
			return "Successfully logged in as "+username;
		}
	 * 
	 */



}
