package analyticsServer.db.content;

import network.rmi.SubscriberCallback;

public class Subscription {

	private static long subscriptionID=0;
	private String regex;
	private SubscriberCallback client;
	private long id;
	
	
	public Subscription(String regex, SubscriberCallback client) {
		this.regex = regex;
		this.client = client;
		this.id = createId();
	}
	
	
	public String getRegex() {
		return regex;
	}


	/*
	 * Getter
	 */
	public SubscriberCallback getClient() {
		return client;
	}


	public long getId() {
		return id;
	}


	private static long createId() {
		return ++subscriptionID;
	}
	
}
