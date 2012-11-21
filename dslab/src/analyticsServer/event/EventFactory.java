package analyticsServer.event;

public class EventFactory {
	private static int idCounter=0;


	/**
	 * 
	 * 
	 * @param auctionID
	 * @param type 0 for auction_started, 1 for auction_ended
	 * @return new AuctionEvent
	 */
	public static AuctionEvent createAuctionEvent(long auctionID, int type){
		AuctionEvent event=null;
		switch(type){
		case 0: event= new AuctionEvent(String.valueOf(++idCounter), 
				AuctionEvent.AUCTION_STARTED, 
				System.currentTimeMillis(), 
				auctionID);
		break;

		case 1: event= new AuctionEvent(String.valueOf(++idCounter),
				AuctionEvent.AUCTION_ENDED, 
				System.currentTimeMillis(), 
				auctionID);
		break;
		}
		return event;
	}
	/**
	 * 
	 * @param username
	 * @param auctionID
	 * @param price
	 * @param type
	 * @return
	 */

	public static BidEvent createBidEvent(String username, long auctionID, double price,int type){
		BidEvent event=null;
		switch(type){
		case 0: event= new BidEvent(String.valueOf(++idCounter), 
				BidEvent.BID_PLACED, 
				System.currentTimeMillis(),
				username,
				auctionID,
				price);
		break;

		case 1: event= new BidEvent(String.valueOf(++idCounter), 
				BidEvent.BID_OVERBID, 
				System.currentTimeMillis(),
				username,
				auctionID,
				price);
		break;

		case 2:event= new BidEvent(String.valueOf(++idCounter), 
				BidEvent.BID_WON, 
				System.currentTimeMillis(),
				username,
				auctionID,
				price);
		break;
		}
		return event;
	}

	/**
	 * 
	 * @param value
	 * @param type
	 * @return
	 */
	public static StatisticsEvent createStatisticsEvent(double value, int type){
		StatisticsEvent event=null;

		switch(type){
		case 0: event= new StatisticsEvent(String.valueOf(++idCounter), 
				StatisticsEvent.USER_SESSIONTIME_MIN, 
				System.currentTimeMillis(),
				value);
		break;

		case 1: event= new StatisticsEvent(String.valueOf(++idCounter), 
				StatisticsEvent.USER_SESSIONTIME_MAX, 
				System.currentTimeMillis(),
				value);
		break;

		case 2: event= new StatisticsEvent(String.valueOf(++idCounter), 
				StatisticsEvent.USER_SESSIONTIME_AVERAGE, 
				System.currentTimeMillis(),
				value);
		break;

		case 3: event= new StatisticsEvent(String.valueOf(++idCounter), 
				StatisticsEvent.BID_COUNT_PER_MINUTE, 
				System.currentTimeMillis(),
				value);
		break;

		case 4: event= new StatisticsEvent(String.valueOf(++idCounter), 
				StatisticsEvent.BID_PRICE_MAX, 
				System.currentTimeMillis(),
				value);
		break;

		case 5: event= new StatisticsEvent(String.valueOf(++idCounter), 
				StatisticsEvent.AUCTION_SUCCESS_RATIO, 
				System.currentTimeMillis(),
				value);
		break;

		case 6: event= new StatisticsEvent(String.valueOf(++idCounter), 
				StatisticsEvent.AUCTION_TIME_AVERAGE, 
				System.currentTimeMillis(),
				value);
		break;
		}


		return event;
	}

	/**
	 * 
	 * @param username
	 * @param type
	 * @return
	 */
	public static UserEvent createUserEvent(String username, int type){
		UserEvent event=null;
		switch(type){
		case 0: event= new UserEvent(String.valueOf(++idCounter), 
				UserEvent.USER_LOGIN, 
				System.currentTimeMillis(),
				username);
		break;

		case 1:event= new UserEvent(String.valueOf(++idCounter), 
				UserEvent.USER_LOGOUT, 
				System.currentTimeMillis(),
				username);
		break;
		
		case 2:event= new UserEvent(String.valueOf(++idCounter), 
				UserEvent.USER_DISCONNECTED, 
				System.currentTimeMillis(),
				username);
		break;
		}


		return event;

	}

}
