package managementClient.commands;

import java.util.HashMap;

import client.command.response.Print;

import command.ICommand;
import command.ICommandList;

/**
 * ManagementClient's respond command-list (needed by the Respond CommandParser within ManagementClientInputThread)
 * 
 * might be unecessary ;)
 * 
 * @author f
 *
 */
public class ManagementClientRespondCommandList implements ICommandList{
private HashMap<String, ICommand> commands;
	
	public ManagementClientRespondCommandList(){
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
