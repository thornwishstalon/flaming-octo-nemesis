package loadTest.command;

import java.util.HashMap;

import loadTest.TestClient;

import command.ICommand;
import command.ICommandList;

public class LoadTestRespondCommandList implements ICommandList {
	private HashMap<String, ICommand> commands;
	
	public LoadTestRespondCommandList(TestClient client) {
		commands= new HashMap<String, ICommand>();
		Ignore ignore= new Ignore();
		LoadTestAuctionItem item= new LoadTestAuctionItem(client);
		LoadTestACKLogout ackLogout= new LoadTestACKLogout(client);
		//IGNORE FOLLOWING COMMANDS
		commands.put("!ackLogin", ignore);
		commands.put("!print", ignore);
		commands.put("!ack-create", ignore);
		commands.put("!ack-logout", ackLogout);
		commands.put("!auction-ended", ignore);
		commands.put("!new-bid", ignore);
		
		//SPECIAL
		commands.put("!auction-item", item );
		
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
