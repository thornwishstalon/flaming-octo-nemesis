package client.command;

import server.logic.User;
import server.logic.UserDATABASE;
import network.security.SimpleStringStream;
import network.security.StaticStream;
import network.tcp.server.TCPServerConnection;
import command.ICommand;

public class Logout implements ICommand {
	private TCPServerConnection connection;
	
	public Logout(TCPServerConnection connection) {
		this.connection=connection;
	}

	@Override
	public int numberOfParams() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String execute(String[] params) {
		User user=connection.getUserObject();
		if(user==null){
			return "!print "+"you need to log in first!";
		}
		
		int x= UserDATABASE.getInstance().logout(user.getName());
		switch(x){
		case UserDATABASE.NO_USER_WITH_THAT_NAME:
			return "!print "+"ERROR: no user with that name known!";
		case UserDATABASE.SUCCESSFULLY_LOGGED_OUT:
			connection.setUserObject(null);
			// reset en/decoding
			connection.print("!ack-logout");
			connection.initDecoderSetting();
			return "";
		}
		
		return null;
	}

	@Override
	public boolean needsRegistration() {
		// TODO Auto-generated method stub
		return true;
	}

	

}
