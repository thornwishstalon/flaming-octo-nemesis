package analyticsServer.event;

import java.text.SimpleDateFormat;
import java.util.Date;

import server.command.Auctions;


public class AuctionEvent extends Event {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8837175818680978355L;
	private long auctionID;
	//auction_event
	public static final String AUCTION_STARTED="AUCTION_STARTED";
	public static final String AUCTION_ENDED="AUCTION_ENDED";
	
	protected AuctionEvent(String ID, String type, long timestamp, long auctionID) {
		super(ID, type, timestamp);
		this.auctionID=auctionID;
	}
	
	public long getAuctionID(){
		return auctionID;
	}
	
	public String toString(){
		SimpleDateFormat df= new SimpleDateFormat("dd.MM.yyyy kk:mm:ss z");
		String s=type+": "+ df.format(new Date(timestamp))+" - ";
		if(type.equals(AUCTION_STARTED))
			s+= "auction with id "+auctionID+" has started.";
		else s+="auction with id "+auctionID+" has ended.";
		return s;
	}
	

}
