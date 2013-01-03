package client.command;

import server.logic.AuctionDATABASE;
import command.ICommand;

public class Confirm implements ICommand {

	@Override
	public int numberOfParams() {
		return 3; //!confir, <auctionID> <price> <initiator>
	}

	@Override
	public String execute(String[] params) {
		int auctionID;
		double price;
		String initiator;

		try{
			auctionID= Integer.valueOf(params[0]);
		}catch(NumberFormatException e){
			return "!print "+ params[0]+" is not a number";
		}

		try{
			price= Integer.valueOf(params[1]);
		}catch(NumberFormatException e){
			return  "!print "+params[1]+" is not a number";
		}

		initiator=params[2];

		int x= AuctionDATABASE.getInstance().confirmTentativeBid(auctionID, price, initiator);
		switch(x){
		case AuctionDATABASE.NO_AUCTION_WITH_ID_FOUND:
			return "!rejected "+"U sure that that's the right ID?";
		case AuctionDATABASE.PRICE_MISSMATCH:
			return "!rejected "+"You and the Initiator of this poll disagree on the price!";
		case AuctionDATABASE.INITIATOR_MISSMATCH:
			return "!rejected "+"Incorrect Initiator!";
		case AuctionDATABASE.SUCCESSFULLY_CONFIRMED_POLL:	
			return "!print "+"You have confirmed the poll!";
		}


		return "";
	}

	@Override
	public boolean needsRegistration() {
		return true;
	}

}
