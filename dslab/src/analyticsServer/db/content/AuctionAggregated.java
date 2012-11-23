package analyticsServer.db.content;

import java.util.HashMap;

public class AuctionAggregated {
	
	private double timeAVG=0;
	private int auctionCount=0;
	private HashMap<Long, Double> auctionLog;
	private HashMap<Long, Boolean> auctionSuccessLog;
	private int success=0, total=0;
	
	public AuctionAggregated() {
		auctionLog = new HashMap<Long, Double>();
		auctionSuccessLog = new HashMap<Long, Boolean>();
	}

	public void startAuction(Long id, double timestamp) {
		synchronized (auctionLog) {
			auctionLog.put(id, timestamp);
		}
		synchronized (auctionSuccessLog) {
			auctionSuccessLog.put(id, false);
		}
	}
	
	public void endAuction(Long id, double timestamp) {
		
		double sessionTime;
		
		synchronized (auctionLog) {
			sessionTime = timestamp - auctionLog.get(id);
			auctionLog.remove(id);
		}
		
		synchronized (auctionSuccessLog) {
			total++;
			if(auctionSuccessLog.get(id))
				success++;
			auctionSuccessLog.remove(id);
		}
		
		timeAVG = (timeAVG*auctionCount + sessionTime) / (++auctionCount);
	}
	
	public void successfulAuction(long id) {
		synchronized (auctionSuccessLog) {
			auctionSuccessLog.put(id, true);
		}
	}
	
	public double getSuccessRatio() {
		return (double) (success/total);
	}
	
	public double getTimeAVG() {
		return timeAVG;
	}
	
}
