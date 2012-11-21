package analyticsServer.event;


public class AuctionEvent extends Event {
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
	

}
