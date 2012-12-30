package client.command;

import network.tcp.server.TCPServerConnection;
import server.logic.AuctionDATABASE;
import command.ICommand;

public class GroupBid implements ICommand {

	private TCPServerConnection connection;

	public GroupBid(TCPServerConnection con){
		this.connection=con;
	}

	@Override
	public int numberOfParams() {
		return 2; //!groupBid <auctionid> <price>
	}

	@Override
	public String execute(String[] params) {
		int auctionID;
		double price;

		try{
			auctionID = Integer.valueOf(params[0]);
		}catch(NumberFormatException e){
			return "!print "+params[0]+" is not a number!";
		}

		try{
			price = Double.valueOf(params[1]);
		}catch(NumberFormatException e){
			return "!print "+params[1]+" is not a number!";
		} 

		int reply= AuctionDATABASE.getInstance().createGroupBid(auctionID, price, connection.getUser());
		switch(reply){
		case AuctionDATABASE.NO_AUCTION_WITH_ID_FOUND:
			return "!print "+"There is currently no auction with that id!";
		case AuctionDATABASE.EXISTING_POLL:
			return "!print "+"There is already a group-bid poll concerning this auction.";
		case AuctionDATABASE.SUCCESSFULLY_PLACED_POLL:
			return "!print Your Poll has successfully been placed.";
		}
		
		return "";
	}

	@Override
	public boolean needsRegistration() {
		return true;
	}

}
