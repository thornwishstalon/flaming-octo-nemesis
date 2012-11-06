package server.command;

import server.logic.AuctionDATABASE;
import command.ICommand;

public class Auctions implements ICommand{

	@Override
	public int numberOfParams() {
		return 1;
	}

	@Override
	public String execute(String[] params) {
		return "!print "+AuctionDATABASE.getInstance().getFullList();
	}

	@Override
	public boolean needsRegistration() {
		return false;
	}

}
