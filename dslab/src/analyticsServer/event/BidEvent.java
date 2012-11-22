package analyticsServer.event;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BidEvent extends Event {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2475924747078965015L;
	//bid_event
	public static final String BID_PLACED="BID_PLACED";
	public static final String BID_OVERBID="BID_OVERBID";
	public static final String BID_WON="BID_WON";
	private String username;
	private long auctionID;
	private double price;

	protected BidEvent(String ID, String type, long timestamp, String username, long auctionID, double price) {
		super(ID, type, timestamp);
		this.username=username;
		this.auctionID=auctionID;
		this.price=price;
	}

	public String getUsername() {
		return username;
	}

	public long getAuctionID() {
		return auctionID;
	}

	public double getPrice() {
		return price;
	}

	public String toString(){
		SimpleDateFormat df= new SimpleDateFormat("dd.MM.yyyy kk:mm:ss z");
		String s=type+": "+ df.format(new Date(timestamp))+" - ";

		if(type.equals(BID_PLACED))
			s+= "user " +username +" placed bid " +price + " on auction" +auctionID ;
		else if(type.equals(BID_OVERBID))
			s+="user " +username +" has been overbid on auction" +auctionID ;
		else
			s+="user " +username +" won on auction" +auctionID+ " with "+price ;

		return s;
	}

}
