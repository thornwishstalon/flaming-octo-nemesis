package analyticsServer.db.content;

import java.util.HashMap;

public class AuctionAggregated {
	
	private double timeAVG=Double.POSITIVE_INFINITY;
	private int auctionCount=0;
	private HashMap<Long, Double> auctionLog;
	
	public AuctionAggregated() {
		auctionLog = new HashMap<Long, Double>();
	}

	public void startAuction(Long id, double timestamp) {
		synchronized (auctionLog) {
			auctionLog.put(id, timestamp);
		}
	}
	
	public void endAuction(Long id, double timestamp) {
		
		double sessionTime;
		
		synchronized (auctionLog) {
			sessionTime = timestamp - auctionLog.get(id);
			auctionLog.remove(id);
		}
		timeAVG = (timeAVG*auctionCount + sessionTime) / (++auctionCount);
	}
	
	public double getTimeAVG() {
		return timeAVG;
	}
	
}
