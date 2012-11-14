package loadTest;

import java.io.IOException;
import java.util.TimerTask;

public class TestClient extends Thread{
	private int port;
	private int serverPort;
	private String serverName;
	 
	//connection relevant
	
	
	public TestClient(int port, int serverPort, String serverName, int updatesPerMinute, int auctionsPerMinute, int bidsPerMinute) {
		this.port=port;
		this.serverPort= serverPort;
		this.serverName= serverName;
	}
	
	public void run(){
		/*
		try{
			while(true){
			
			}
		}catch(SocketException e){
			
		}catch(IOException e){
			
		}
		*/
	}
	
	public void shutdown() {
		// TODO Auto-generated method stub
		
	}
	
	
	private class AuctionTask extends TimerTask{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	private class ListUpdateTask extends TimerTask{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			
		}	
	}
	
	private class BidTask extends TimerTask{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			
		}
		
	}
	
}

