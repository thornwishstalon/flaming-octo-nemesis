package server.logic;

import java.util.Timer;
import java.util.TimerTask;

public class TentativeBid {
	private long timeout=90000; //90 secs timeout 
	private int auctionId;
	private boolean isConfirmed=false;
	private boolean timedOut=false;
	private double price;

	private int confirmCounter=0;
	private long timeTillTimeout=timeout;
	private String initiator;


	private Timer timer;


	public TentativeBid(int id, String initiator, double price){
		this.auctionId=id;
		this.initiator=initiator;
		this.price=price;

		timer= new Timer();
		timer.schedule(new TimeoutTask(), timeout); //timeout= delay in that case
		timer.schedule(new CountDownTask(), 0, 1000);

		//notify all logged in Users except the initiator
		UserDATABASE.getInstance().notifyLoggedInUsers("!print "+"A new group-bid poll has been started by "+initiator+" on auction "+auctionId+" with "+price, initiator);
	}

	public boolean isConfirmed() {
		return isConfirmed;
	}

	public void setConfirmed(boolean isConfirmed) {
		this.isConfirmed = isConfirmed;
	}

	public boolean isTimedOut(){
		return timedOut;
	}
	public int getAuctionId() {
		return auctionId;
	}
	public double getPrice() {
		return price;
	}
	public String getInitiator() {
		return initiator;
	}

	public synchronized void confirm(){
		
		confirmCounter++;
		
		if(confirmCounter >= 2 && !isConfirmed){
			isConfirmed=true;
			
			int x= AuctionDATABASE.getInstance().bidOnAuction(auctionId, UserDATABASE.getInstance().getUser("group"), price);
			String message="";
			switch(x){
			case AuctionDATABASE.NEEDS_MORE_MONEY:
				message= "!rejected Amount of money is not sufficient to overbid!";
				break;
				
			case AuctionDATABASE.NO_AUCTION_WITH_ID_FOUND:
				message= "!rejected There is no auction with that id!";
				break;
				
			case AuctionDATABASE.AUCTION_EXPIRED:
				message= "!rejected This auction has expired";
				break;
				
			case AuctionDATABASE.SUCCESSFULLY_PLACED_BID:
				//solve blocks
				UserDATABASE.getInstance().notifyLoggedInUsers("!confirmed");
				//build success message
				//message= "!print You successfully bid with " +price+" on '"+AuctionDATABASE.getInstance().getDescription(auctionId)+"'.";
				break;
			/*	
			case AuctionDATABASE.OWNER_SAME_AS_BIDDER:
				message= "!rejected Don't bid on your own items!";
			*/
			}
			
			
			UserDATABASE.getInstance().notifyLoggedInUsers(message);

			timer.cancel();
			timer.purge();

		}

	}



	private class TimeoutTask extends TimerTask{

		@Override
		public void run() {	

			timedOut=true; 

			UserDATABASE.getInstance().notifyLoggedInUsers("!rejected "+"Poll was timed out!");
			timer.cancel();
			timer.purge();
		}

	}
	private class CountDownTask extends TimerTask{

		@Override
		public void run() {	
			timeTillTimeout=timeTillTimeout-1000;
		}

	}
	

	public synchronized void cancel(){
		timer.cancel();
		timer.purge();
	}
	
	public String toString(){
		
		if(!isTimedOut())
			return "\t auction: "+auctionId+" by "+initiator+", confirmed by: "+confirmCounter+"\n\t\t time till timeout: "+timeTillTimeout/1000+" seconds";
		else return "\t auction: "+auctionId+" by "+initiator+", confirmed by: "+confirmCounter;
	}







}
