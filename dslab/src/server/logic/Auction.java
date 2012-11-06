package server.logic;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Timer;
import java.util.TimerTask;

public class Auction {
	private int ID;

	private String description;
	private double price=0;	

	private long duration;
	private User owner;
	private User highestBidder;

	private Timestamp creation;
	private Timestamp expiration;

	private boolean expired=false;
	private Timer timer;


	public Auction(User owner,String description, long duration){
		creation = new Timestamp(System.currentTimeMillis());
		this.description= description;
		this.owner= owner;
		this.duration=duration;

		expiration= new Timestamp(creation.getTime()+duration);

		timer= new Timer();

		timer.schedule(new ExpireTask(), duration, 10000); //10s check interval
	}

	private class ExpireTask extends TimerTask{

		@Override
		public void run() {
			timer.cancel();
			expired=true;
			//TODO notify bidder!!
			owner.addNotification(NotificationFactory.createNotification(getOwnerNote()));
			if(highestBidder!=null)
				highestBidder.addNotification(NotificationFactory.createNotification(getBidderNote()));
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

	public boolean isExpired(){
		return expired;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
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





}
