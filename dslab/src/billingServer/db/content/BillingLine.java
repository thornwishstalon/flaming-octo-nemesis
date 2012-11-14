package billingServer.db.content;


public class BillingLine {

	private long auctionID;
	private double price;
	
	public BillingLine(long auctionID, double price) {
		this.auctionID = auctionID;
		this.price = price;
	}
	
	/*
	 * Getter / Setter
	 */
	public long getAuctionID() {
		return auctionID;
	}

	public void setAuctionID(long auctionID) {
		this.auctionID = auctionID;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}