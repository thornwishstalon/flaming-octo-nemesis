package client.command.response;

import java.util.HashMap;


import command.ICommand;
import command.ICommandList;

/**
 * CommandList implementing ICommandList providing ICommands used by a 
 * Client's CommandParser responsible to interprete server responses
 *
 */

public class ResponseList implements ICommandList {
	private HashMap<String, ICommand> commands;
	
	
	public ResponseList(){
		
		commands= new HashMap<String, ICommand>();
		Ack ack= new Ack();
		NewBid newBid= new NewBid();
		AuctionEnded auctionEnded= new AuctionEnded();
		//LocalPassThrough through= new LocalPassThrough();
		Print print= new Print();
		AckLogin ackLogin= new AckLogin();
		AckLogout ackLogout = new AckLogout();
		Kill kill= new Kill();
		AckCreate ackCreate= new AckCreate();
		AuctionItem aucItem= new AuctionItem();
		Rejected rejected= new Rejected();
		Confirmed confirmed= new Confirmed();
		
		//TESTING dslab3_stage2
		DEHESH_TEST dehesh= new DEHESH_TEST("keys/alice.key");
		
		commands.put("!ack", ack);
		commands.put("!new-bid", newBid);
		commands.put("!auction-ended", auctionEnded);
		commands.put("!print", print);
		commands.put("!ack-login", ackLogin);
		commands.put("!ack-logout", ackLogout);
		commands.put("!kill", kill);
		commands.put("!ack-create",ackCreate);
		commands.put("!auction-item",aucItem);
		//TESTING
		commands.put("!dehesh", dehesh);
		//TESTING
		
		commands.put("!rejected", rejected);
		commands.put("!confirmed", confirmed);
	

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
