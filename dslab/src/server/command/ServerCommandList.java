package server.command;

import java.util.HashMap;

import command.ICommand;
import command.ICommandList;

public class ServerCommandList implements ICommandList {
	private HashMap<String, ICommand> commands;
	
	public ServerCommandList(){
		commands= new HashMap<String, ICommand>();
		Users users= new Users();
		Auctions auctions= new Auctions();
		Close close= new Close();
		Reconnect reconnect = new Reconnect();
		
		commands.put("!users", users);
		commands.put("!auctions", auctions);
		commands.put("!close", close);
		commands.put("!reconnect", reconnect);
		
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
