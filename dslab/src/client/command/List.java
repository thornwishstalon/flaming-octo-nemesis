package client.command;

import java.util.ArrayList;

import network.tcp.server.TCPServerConnection;
import server.logic.Auction;
import server.logic.AuctionDATABASE;
import command.ICommand;

public class List implements ICommand{
	private TCPServerConnection connection;

	public List(TCPServerConnection connection){
		this.connection=connection;
	}

	@Override
	public int numberOfParams() {
		return 0;
	}

	@Override
	public String execute(String[] params) {
		ArrayList<Auction> tmp= AuctionDATABASE.getInstance().getAuctionList();
		int c=0;
		for(Auction a:tmp){
			if(!a.isExpired()){
				c++;
				if(a.getHighestBidder()!=null){
					connection.print("!auction-item "+a.getID()+" "+a.getCreation().getTime()+" "+a.getDuration()*1000+" "+
							a.getOwner().getName()+" "+a.getHighestBidder().getName()+" "+a.getPrice()+" "+a.getDescription());
				}else{
					connection.print("!auction-item "+a.getID()+" "+a.getCreation().getTime()+" "+a.getDuration()*1000+" "+
							a.getOwner().getName()+" "+"none"+" "+"0.0"+" "+a.getDescription());
				}
			}
		}
		if(c==0){
			return "!print"+" There are currently no auctions!";
		}

		return "";//"!print "+AuctionDATABASE.getInstance().getList();
	}

	@Override
	public boolean needsRegistration() {
		return false;
	}



}
