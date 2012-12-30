package server.logic;

import java.util.Timer;
import java.util.TimerTask;

public class TentativeBid {
	private long timeout=15000; //15 secs 
	private int auctionId;
	private boolean isConfirmed=false;
	private boolean timedOut=false;
	private double price;

	private int confirmCounter=0;

	private String initiator;


	private Timer timer;


	public TentativeBid(int id, String initiator, double price){
		this.auctionId=id;
		this.initiator=initiator;
		this.price=price;

		timer= new Timer();
		timer.schedule(new TimeoutTask(), timeout); //timeout= delay in that case


		//notify all logged in Users
		UserDATABASE.getInstance().notifyLoggedInUsers("!print "+"A new group-bid poll has been started by "+initiator+" on auction "+auctionId+" with "+price);
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

	public void confirm(){

		if(confirmCounter++ >= 2 && !isConfirmed){
			isConfirmed=true;
			

			int x= AuctionDATABASE.getInstance().bidOnAuction(auctionId, UserDATABASE.getInstance().getUser(initiator), price);
			String message="";
			switch(x){
			case AuctionDATABASE.NEEDS_MORE_MONEY:
				message= "!rejected Amount of money is not sufficient to overbid!";
				
			case AuctionDATABASE.NO_AUCTION_WITH_ID_FOUND:
				message= "!rejected There is no auction with that id!";
				
			case AuctionDATABASE.AUCTION_EXPIRED:
				message= "!rejected This auction has expired";
				
			case AuctionDATABASE.SUCCESSFULLY_PLACED_BID:
				message= "!confirmed You successfully bid with " +price+" on '"+AuctionDATABASE.getInstance().getDescription(auctionId)+"'.";
				
			case AuctionDATABASE.OWNER_SAME_AS_BIDDER:
				message= "!rejected Don't bid on your own items!";
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

			UserDATABASE.getInstance().notifyLoggedInUsers("!rejected ");

		}

	}

	public synchronized void cancel(){
		timer.cancel();
		timer.purge();
	}







}
