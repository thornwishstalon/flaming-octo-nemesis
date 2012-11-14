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
		return 3;
	}

	@Override
	public String execute(String[] params) {
		String user = params[0];
		String price= params[1];
		String description= params[2];
		//System.out.println("!auction-ended block:");
		if(user.trim().equals(ClientStatus.getInstance().getUser().trim()))
			return "The auction '"+description+"' has ended. You won with "+price+".";
		else return "The auction '"+description+"' has ended. "+user+" won with "+price+".";
	}

	@Override
	public boolean needsRegistration() {
		return false;
	}

}
