package analyticsServer.db;

import java.sql.Timestamp;

import analyticsServer.event.*;

public class StatisticEventsDATABASE {

	private StatisticsEvent userSessiontimeMin, userSessiontimeMax, userSessiontimeAVG, 
							bidPriceMax, bidCountMinute, auctionTimeAVG, auctionSuccessRatio;
	private Timestamp sessionStarted;
	
	public StatisticEventsDATABASE() {
		sessionStarted = new Timestamp(System.currentTimeMillis());
	}

	public void userLogin(AuctionEvent e) {
		
	}

	


}
