package server;

public class ServerSetup {
	private int port;
	private String analyticsServerName;
	private String billingServer;
	
	public ServerSetup(String[] args){
		port= Integer.valueOf(args[0]);
		analyticsServerName= args[1];
		billingServer= args[2];
		
	}
	
	public int getPort() {
		return port;
	}
	
	public String toString(){
		return "setup: \nport: "+port;
	}

	public String getAnalyticsServerName() {
		return analyticsServerName;
	}

	public void setAnalyticsServerName(String analyticsServerName) {
		this.analyticsServerName = analyticsServerName;
	}

	public String getBillingServer() {
		return billingServer;
	}

	public void setBillingServer(String billingServer) {
		this.billingServer = billingServer;
	}
	
	
}
