package loadTest;

/**
 * Dummy class, used to store auction relevant information, which was sent with an !auction-item command
 *
 */
public class LoadTestAuction {
	private int ID;
	private String owner;
	private String bidder;
	private long creation;
	private long duration;
	private double price;
	private String description;
	
	public LoadTestAuction(int id, String owner, String bidder, long creation, long duration, double price, String description) {
		this.ID= id;
		this.owner=owner;
		this.bidder=bidder;
		this.creation=creation;
		this.duration= duration;
		this.price=price;
		this.description=description;
	}
	
	//GETTER
	public int getID() {
		return ID;
	}
	public String getOwner() {
		return owner;
	}
	public String getBidder() {
		return bidder;
	}
	public long getCreation() {
		return creation;
	}
	public long getDuration() {
		return duration;
	}
	public double getPrice() {
		return price;
	}
	
	public String getDescription() {
		return description;
	}
	
	
}
