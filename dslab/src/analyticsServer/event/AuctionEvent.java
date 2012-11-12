package analyticsServer.event;


public class AuctionEvent extends Event {
	private int auctionID;
	//auction_event
	public static final String AUCTION_STARTED="AUCTION_STARTED";
	public static final String AUCTION_ENDED="AUCTION_ENDED";
	
	protected AuctionEvent(String ID, String type, long timestamp, int auctionID) {
		super(ID, type, timestamp);
		this.auctionID=auctionID;
	}
	
	public int getAuctionID(){
		return auctionID;
	}
	

}
