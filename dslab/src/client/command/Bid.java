package client.command;

import server.logic.AuctionDATABASE;
import network.tcp.server.TCPServerConnection;
import command.ICommand;

public class Bid implements ICommand {
	private TCPServerConnection connection;
	
	public Bid(TCPServerConnection connection) {
		this.connection= connection;
	}

	@Override
	public int numberOfParams() {
		return 2;
	}

	@Override
	public String execute(String[] params) {
		String sID=params[0];
		String sAmount=params[1];
		int id=0;
		double amount=0;
		
		try{
			id= Integer.valueOf(sID.trim());
		}
		catch(NumberFormatException e){
			return "!print "+sID+" is not a number";
		}
		
		try{
			amount= Double.valueOf(sAmount.trim());
		}
		catch(NumberFormatException e){
			return "!print "+sAmount+" is not a number";
		}
		
		int x;
		try {
			x = AuctionDATABASE.getInstance().bidOnAuction(id, connection.getUserObject(), amount);
		} catch (Exception e) {
			return "!print There is no auction with that id!";
		}
		
		switch(x){
		case AuctionDATABASE.NEEDS_MORE_MONEY:
			return "!print Amount of money is not sufficient to overbid!";
			
		case AuctionDATABASE.NO_AUCTION_WITH_ID_FOUND:
			return "!print There is no auction with that id!";
		case AuctionDATABASE.AUCTION_EXPIRED:
			return "!print This auction has expired";
		case AuctionDATABASE.SUCCESSFULLY_PLACED_BID:
			return "!print You successfully bid with " +amount+" on '"+AuctionDATABASE.getInstance().getDescription(id)+"'.";
		case AuctionDATABASE.OWNER_SAME_AS_BIDDER:
			return "!print Don't bid on your own items!";
		}
		

		return "!print "+"lol";
	}

	@Override
	public boolean needsRegistration() {
		return true;
	}

	
}
