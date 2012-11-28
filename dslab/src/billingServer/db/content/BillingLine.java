package billingServer.db.content;

import java.io.Serializable;


public class BillingLine implements Serializable {

	private static final long serialVersionUID = -996707184733426129L;
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

	public synchronized void setAuctionID(long auctionID) {
		this.auctionID = auctionID;
	}

	public double getPrice() {
		return price;
	}

	public synchronized void setPrice(double price) {
		this.price = price;
	}
}
