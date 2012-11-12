package analyticsServer.event;

public class StatisticsEvent extends Event {
	//statistics_event
	public static final String USER_SESSIONTIME_MIN="USER_SESSIONTIME_MIN";
	public static final String USER_SESSIONTIME_MAX="USER_SESSIONTIME_MAX";
	public static final String USER_SESSIONTIME_AVERAGE="USER_SESSIONTIME_AVERAGE";
	public static final String BID_PRICE_MAX="BID_PRICE_MAX";
	public static final String BID_COUNT_PER_MINUTE="BID_COUNT_PER_MINUTE";
	public static final String AUCTION_TIME_AVERAGE="AUCTION_TIME_AVERAGE";
	public static final String AUCTION_SUCCESS_RATIO="AUCTION_SUCCESS_RATIO";
	
	private double value;
	
	protected StatisticsEvent(String ID, String type, long timestamp, double value) {
		super(ID, type, timestamp);	
		this.value=value;
	}
	
	public double getValue(){
		return value;
	}

}
