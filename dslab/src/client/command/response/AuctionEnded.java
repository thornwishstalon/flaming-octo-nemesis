package client.command.response;

import client.ClientMain;
import client.ClientStatus;
import command.ICommand;

/**
 * an auction has ended. execute() returns a readable notification
 *
 */
public class AuctionEnded implements ICommand {

	@Override
	public int numberOfParams() {
		return 4;
	}

	@Override
	public String execute(String[] params) {
		String user = params[0];
		String owner= params[1];
		String price= params[2];
		String description= params[3];
	
		
		//System.out.println("!auction-ended block:");
		if(user.trim().equals(ClientStatus.getInstance().getUser().trim())||(user.equals("group")))
			return "!print The auction '"+description+"' has ended. You won with "+price+".";
		else if(ClientStatus.getInstance().getUser().equals(owner)) return "!print Your auction '"+description+"' has ended. "+user+" won with "+price+".";
		else  return "!print The auction '"+description+"' has ended. You won with "+price+".";
	}

	@Override
	public boolean needsRegistration() {
		return false;
	}

}
