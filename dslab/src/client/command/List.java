package client.command;

import server.logic.AuctionDATABASE;
import command.ICommand;

public class List implements ICommand{

	@Override
	public int numberOfParams() {
		return 0;
	}

	@Override
	public String execute(String[] params) {
		
		return "!print "+AuctionDATABASE.getInstance().getList();
	}

	@Override
	public boolean needsRegistration() {
		return false;
	}

	

}
