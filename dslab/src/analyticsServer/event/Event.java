package analyticsServer.event;

public abstract class Event {
	protected String ID;
	protected String type;
	protected long timestamp;
	

	//statistics_event
	protected final String USER_SESSIONTIME_MIN="USER_SESSIONTIME_MIN";
	protected final String USER_SESSIONTIME_MAX="USER_SESSIONTIME_MAX";
	protected final String USER_SESSIONTIME_AVERAGE="USER_SESSIONTIME_AVERAGE";
	protected final String BID_PRICE_MAX="BID_PRICE_MAX";
	protected final String BID_COUNT_PER_MINUTE="BID_COUNT_PER_MINUTE";
	protected final String AUCTION_TIME_AVERAGE="AUCTION_TIME_AVERAGE";
	protected final String AUCTION_SUCCESS_RATIO="AUCTION_SUCCESS_RATIO";
	
	protected Event(String ID, String type, long timestamp){
		this.ID=ID;
		this.type=type;
		this.timestamp=timestamp;
	}

	public String getID() {
		return ID;
	}

	public String getType() {
		return type;
	}

	public long getTimestamp() {
		return timestamp;
	}
	
	
	
	
	
}
