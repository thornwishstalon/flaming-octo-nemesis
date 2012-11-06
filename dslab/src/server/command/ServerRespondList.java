package server.command;

import java.util.HashMap;

import client.command.response.Print;

import command.ICommand;
import command.ICommandList;

public class ServerRespondList implements ICommandList {
	private HashMap<String, ICommand> commands;
	
	public ServerRespondList(){
		commands= new HashMap<String, ICommand>();
		//Users users= new Users();
		//Auctions auctions= new Auctions();
		
		//commands.put("!users", users);
		//commands.put("!auctions", auctions);
		commands.put("!print", new Print());
		
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
