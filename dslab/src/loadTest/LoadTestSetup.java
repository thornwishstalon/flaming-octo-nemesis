package loadTest;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class LoadTestSetup {
	//DEFAULT Setup
	private int clients = 100;
	private int	auctionsPerMin = 1;
	private int auctionDuration = 60;
	private int updateIntervalSec = 20;
	private int bidsPerMin = 2;
	
	private String serverHost;
	private int serverPort;
	private String analyticsBindingName;

	public LoadTestSetup(String[] params) {
		serverHost= params[0];
		serverPort= Integer.valueOf(params[1]);
		analyticsBindingName= params[2];
		
		init();
	}
	
	/**
	 * load testing properties from the loadtest.properties file
	 */
	private void init() {
		InputStream in= ClassLoader.getSystemResourceAsStream("loadtest.properties");
		if(in!=null){
			Properties props= new Properties();
			try{
				props.load(in);
				this.clients= Integer.valueOf(props.getProperty("clients").trim());
				this.auctionsPerMin =Integer.valueOf(props.getProperty("auctionsPerMin").trim());
				this.auctionDuration= Integer.valueOf(props.getProperty("auctionDuration").trim());
				this.updateIntervalSec= Integer.valueOf(props.getProperty("updateIntervalSec").trim());
				this.bidsPerMin= Integer.valueOf(props.getProperty("bidsPerMin").trim());

			}catch(NumberFormatException e){
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}

	}
	
	/*
	 * GETTER
	 */
	public int getClients() {
		return clients;
	}

	public int getAuctionsPerMin() {
		return auctionsPerMin;
	}

	public int getAuctionDuration() {
		return auctionDuration;
	}

	public int getUpdateIntervalSec() {
		return updateIntervalSec;
	}

	public int getBidsPerMin() {
		return bidsPerMin;
	}
	
	
	public String getServerHost() {
		return serverHost;
	}

	public int getServerPort() {
		return serverPort;
	}

	public String getAnalyticsBindingName() {
		return analyticsBindingName;
	}

	
	/*
	 *toSTRING
	 */
	
	public String toString(){
		return  "LOADTEST-SETUP:\n"+
				"\tclients = "+clients+"\n"+
				"\tauctionsPerMin =" + auctionsPerMin+"\n"+
				"\tauctionDuration = "+auctionDuration+"\n"+
				"\tupdateIntervalSec: "+updateIntervalSec+"\n"+
				"\tbidsPerMin = "+bidsPerMin+"\n";
	}
}
