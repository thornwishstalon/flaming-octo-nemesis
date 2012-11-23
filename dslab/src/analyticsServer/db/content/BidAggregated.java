package analyticsServer.db.content;

import java.sql.Timestamp;

public class BidAggregated {
	private double bidPriceMax=0;
	private long sessionStarted;
	private int bidCount=0;
	
	public BidAggregated() {
		sessionStarted =System.currentTimeMillis();
	}

	public void bidPlaced(double bidPriceMax) {
		if(bidPriceMax > this.bidPriceMax)
			this.bidPriceMax = bidPriceMax;
			bidCount++;
	}
	
	
	public double getBidPriceMax() {
		return bidPriceMax;
	}

	public double getBidCountPerMinute() {
		long minutes = (System.currentTimeMillis()-sessionStarted)/(1000*60);
		return bidCount/minutes;
	}

	
}
