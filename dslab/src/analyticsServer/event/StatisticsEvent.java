package analyticsServer.event;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StatisticsEvent extends Event {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2683420560112386917L;
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
	
	
	public String toString() {
		SimpleDateFormat df= new SimpleDateFormat("dd.MM.yyyy kk:mm:ss z");
		String s=type+": "+ df.format(new Date(timestamp))+" - ";
		
		if(type.equals(USER_SESSIONTIME_MIN))
			s+= "minimum session time is "+ value +" seconds ";
		else if(type.equals(USER_SESSIONTIME_MAX)) s+= "maximum session time is "+ value +" seconds ";
		else if(type.equals(USER_SESSIONTIME_AVERAGE)) s+= "average session time is "+ value +" seconds ";
		else if(type.equals(BID_PRICE_MAX)) s+= "maximum bid price is "+ value;
		else if(type.equals(BID_COUNT_PER_MINUTE)) s+= "current bids per minute is "+ value;
		else if(type.equals(AUCTION_TIME_AVERAGE)) s+= "average auction time is "+ value +" seconds ";
		else s+= "auction success ratio is "+ value;

		return s;
	}

}
