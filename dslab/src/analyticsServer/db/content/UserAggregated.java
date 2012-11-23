package analyticsServer.db.content;

import java.util.HashMap;

public class UserAggregated {
	
	private double timeMin=Double.POSITIVE_INFINITY, timeMax=0, timeAVG=0;
	private int sessionCount=0;
	private HashMap<String, Double> userLog;
	
	public UserAggregated() {
		userLog = new HashMap<String, Double>();
	}

	public void loginUser(String name, double timestamp) {
		synchronized (userLog) {
			userLog.put(name, timestamp);
		}
	}
	
	public void logoutUser(String name, double timestamp) {
		
		double sessionTime;
		
		synchronized (userLog) {
			sessionTime = timestamp - userLog.get(name);
			userLog.remove(name);
		}
		
		if(timeMin>sessionTime)
			timeMin=sessionTime;
		if(timeMax<sessionTime)
			timeMax=sessionTime;
		
		timeAVG = (timeAVG*sessionCount + sessionTime) / (++sessionCount);
	}
	
	/*
	 * GETTER
	 */
	public double getTimeMin() {
		return timeMin;
	}

	public double getTimeMax() {
		return timeMax;
	}

	public double getTimeAVG() {
		return timeAVG;
	}
}
