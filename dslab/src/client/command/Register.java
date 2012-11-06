package client.command;

import network.tcp.server.TCPServerConnection;
import command.ICommand;

public class Register implements ICommand {
	private TCPServerConnection connection;
	
	public Register(TCPServerConnection connection) {
		this.connection=connection;
	}
	
	
	@Override
	public int numberOfParams() {
		return 1; //port 
	}

	@Override
	public String execute(String[] params) {
		String sPort= params[0];
		int port=0;
		try{
			port= Integer.valueOf(sPort.trim());
		}catch(NumberFormatException e){
			return sPort+" is not a number";
		}
		connection.setPort(port);
		
		return "!ack";
	}

	@Override
	public boolean needsRegistration() {
		return false;
	}

}
