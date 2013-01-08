package client.command;

import java.util.HashMap;


import command.ICommand;
import command.ICommandList;

public class ClientLocalCommandList implements ICommandList {
	private HashMap<String, ICommand> commands;
	
	
	public ClientLocalCommandList(int port){
		
		commands= new HashMap<String, ICommand>();
		LocalLogin login= new LocalLogin();
		//LocalCreate create= new LocalCreate();
		LocalPassThrough through= new LocalPassThrough();
		//LocalRegister register= new LocalRegister(port);  
		
		commands.put("!login", login);
		commands.put("!list", through);
		//commands.put("!create", create);
		commands.put("!create", through);
		commands.put("!logout", through);
		commands.put("!bid", through);

		//commands.put("!register", register);
		commands.put("!groupBid", through);
		//commands.put("!sendAll", through); //DEBUG ONLY
		commands.put("!confirm", through);
		

		commands.put("!getClientList", through);
		//commands.put("!register", register);

		//TESTING dslab3_stage2
		//commands.put("!hesh", through);
		
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
