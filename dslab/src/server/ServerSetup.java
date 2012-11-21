package server;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;



public class ServerSetup {
	private int port;
	private String analyticsServerName;
	private String billingServer;
	
	private String username;
	private String password;
	
	public ServerSetup(String[] args){
		port= Integer.valueOf(args[0]);
		analyticsServerName= args[1];
		billingServer= args[2];
		
		loadServerUsername();
		
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
	
	
	
	
	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	private void loadServerUsername() {
		InputStream in= ClassLoader.getSystemResourceAsStream("server.properties");
		if(in!=null){
			Properties props= new Properties();
			try{
				props.load(in);
				this.username= props.getProperty("server.username").trim();
				this.username= props.getProperty("server.password").trim();
				
			}catch(NumberFormatException e){
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	

		}else System.err.println("File not found!");
	}
	
}
