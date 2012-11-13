package analyticsServer.event;

public class BidEvent extends Event {
	//bid_event
	public static final String BID_PLACED="BID_PLACED";
	public static final String BID_OVERBID="BID_OVERBID";
	public static final String BID_WON="BID_WON";
	private String username;
	private int auctionID;
	private double price;
	
	protected BidEvent(String ID, String type, long timestamp, String username, int auctionID, double price) {
		super(ID, type, timestamp);
		this.username=username;
		this.auctionID=auctionID;
		this.price=price;
	}

	public String getUsername() {
		return username;
	}

	public int getAuctionID() {
		return auctionID;
	}

	public double getPrice() {
		return price;
	}
	
	

}