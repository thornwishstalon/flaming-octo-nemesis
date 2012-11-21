package server.logic;

import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Timer;
import java.util.TimerTask;

import analyticsServer.event.AuctionEvent;
import analyticsServer.event.EventFactory;

import server.ServerSetup;
import server.ServerStatus;

public class Auction {
	private long ID;

	private String description;
	private double price=0;	

	private long duration;
	private User owner;
	private User highestBidder;

	private Timestamp creation;
	private Timestamp expiration;

	private boolean expired=false;
	private Timer timer;

	private boolean isBilled=false;
	private EventFactory ef;


	public Auction(User owner,String description, long duration){
		creation = new Timestamp(System.currentTimeMillis());
		this.description= description;
		this.owner= owner;
		this.duration=duration;

		expiration= new Timestamp(creation.getTime()+duration);

		timer= new Timer();

		timer.schedule(new ExpireTask(), duration, 10000); //10s check interval
		
		try {
			ServerStatus.getInstance().getAnalyticsServer().processEvent(ef.createAuctionEvent(ID,0));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private class ExpireTask extends TimerTask{

		@Override
		public void run() {
			timer.cancel();
			expired=true;
			
			owner.addNotification(NotificationFactory.createNotification(getOwnerNote()));
			
			if(highestBidder!=null){
				highestBidder.addNotification(NotificationFactory.createNotification(getBidderNote()));
				
				try {
					ServerStatus.getInstance().getAnalyticsServer().processEvent(ef.createBidEvent(highestBidder.getName(), ID, price, 2)); //bidder won
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}
			
			//notify analytics-server
			try {
				ServerStatus.getInstance().getAnalyticsServer().processEvent(ef.createAuctionEvent(ID,1)); //auction ended
				
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			//notify billing-server
			try {
				ServerStatus.getInstance().getBillingServer().billAuction(owner.getName(), ID, price);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			
			
		}
	}

	private String getOwnerNote(){

		if(highestBidder!=null)
			return "!auction-ended "+ highestBidder.getName()+" "+ price+" "+description;
		else  return "!auction-ended "+ "none "+" "+ "0.0 "+" "+description;

		//return "!auction-ended "+ highestBidder.getName()+" "+ price+" "+description;
	}

	private String getBidderNote(){
		return "!auction-ended "+ highestBidder.getName()+" "+ price+" "+description;//owner.getName()+"> "+"The auction '"+description.trim()+"' has ended. You won with "+price;
	}

	public String toString(){
		//DateFormat df = DateFormat.getDateInstance(DateFormat.LONG);
		SimpleDateFormat df= new SimpleDateFormat("dd.MM.yyyy kk:mm z");

		String s= ID+". '"+description+"' "+owner.getName()+" "+ df.format(expiration);
		if(highestBidder!=null){
			s+=" "+price+ " "+highestBidder.getName();  
		}
		else s+=" 0.0 none";
		return  s; 
	}

	public String toDetailString(){
		//DateFormat df = DateFormat.getDateInstance(DateFormat.LONG);
		SimpleDateFormat df= new SimpleDateFormat("dd.MM.yyyy kk:mm:ss z");

		String s= ID+". '"+description+"' "+owner.getName()+" "+ df.format(expiration);
		if(highestBidder!=null){
			s+=" "+price+ " "+highestBidder.getName();  
		}
		else s+=" 0.0 none";
		return  s; 
	}
	
	
	
	
	
	/*
	 * GETTER//SETTER
	 * 
	 */
	
	public boolean isExpired(){
		return expired;
	}

	public long getID() {
		return ID;
	}

	public void setID(long iD) {
		ID = iD;
	}

	public User getHighestBidder() {
		return highestBidder;
	}

	public void setHighestBidder(User highestBidder) {
		if(this.highestBidder!=null){
			this.highestBidder.addNotification(NotificationFactory.createNotification("!new-bid "+description.trim()));
		}
		this.highestBidder = highestBidder;
	}

	public String getDescription() {
		return description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price= price;
	}

	public long getDuration() {
		return duration;
	}

	public User getOwner() {
		return owner;
	}

	public Timestamp getCreation() {
		return creation;
	}

	public Timestamp getExpiration() {
		return expiration;
	}

	public boolean isBilled() {
		return isBilled;
	}

	public void setBilled(boolean isBilled) {
		this.isBilled = isBilled;
	}





}
