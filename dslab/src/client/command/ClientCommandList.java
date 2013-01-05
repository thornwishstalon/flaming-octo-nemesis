package client.command;

import java.util.HashMap;

import network.tcp.server.TCPServerConnection;

import command.ICommand;
import command.ICommandList;

/** 
 * CommandsList for an CommandParser on the server responsible interpreting user input recieved from a client
 *
 */
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
		GroupBid groupBid= new GroupBid(connection);
		Confirm confirm= new Confirm();
		
		//TESTING dslab3_stage2
		HMAC_TEST hesh= new HMAC_TEST("keys/alice.key");
		
		commands.put("!login", login);
		commands.put("!logout", logout);
		commands.put("!list", list);
		commands.put("!create", create);
		commands.put("!bid", bid);
		commands.put("!register", register);
		commands.put("!hesh", hesh);
		commands.put("!groupBid", groupBid);
		commands.put("!confirm", confirm);
		
		//TESTING
		commands.put("!sendAll", new SendAll());
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
