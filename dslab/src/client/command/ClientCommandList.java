package client.command;

import java.util.HashMap;

import network.tcp.server.TCPServerConnection;

import command.ICommand;
import command.ICommandList;

public class ClientCommandList implements ICommandList {
	private HashMap<String, ICommand> commands;
	
	public ClientCommandList(TCPServerConnection connection){
		commands= new HashMap<String, ICommand>();
		
		Login login= new Login(connection);
		Logout logout= new Logout(connection);
		List list = new List(connection);
		Create create= new Create(connection);
		Bid bid = new Bid(connection);
		Register register= new Register(connection);
		
		commands.put("!login", login);
		commands.put("!logout", logout);
		commands.put("!list", list);
		commands.put("!create", create);
		commands.put("!bid", bid);
		commands.put("!register", register);
	}
	
	
	@Override
	public boolean containsKey(String commandKey) {
		return commands.containsKey(commandKey);
	}

	@Override
	public ICommand get(String commandKey) {
		return commands.get(commandKey);
	}

	
}
