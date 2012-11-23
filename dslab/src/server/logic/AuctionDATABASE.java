package server.logic;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

import analyticsServer.event.EventFactory;

import server.ServerStatus;

public class AuctionDATABASE {
	private static AuctionDATABASE instance=null;
	public final static int NO_AUCTION_WITH_ID_FOUND=0;
	public final static int SUCCESSFULLY_PLACED_BID=1;
	public final static int NEEDS_MORE_MONEY=2;
	public final static int AUCTION_EXPIRED=3;
	public final static int OWNER_SAME_AS_BIDDER=4;

	private int idCounter=0;
	private ConcurrentHashMap<Integer,Auction> auctionList;

	private AuctionDATABASE(){
		auctionList= new ConcurrentHashMap<Integer, Auction>();
	}

	public static AuctionDATABASE getInstance(){
		if(instance==null)
			instance= new AuctionDATABASE();
		return instance;
	}

	public synchronized int createAuction(User owner, String description, long duration){
		Auction tmp= new Auction(owner, description, duration*1000,++idCounter);
		//tmp.setID(++idCounter);


		auctionList.put(idCounter,tmp);

		UserDATABASE.getInstance().getUser(owner.getName()).getConnection().print("!ack-create "+
				tmp.getID()+
				" "+tmp.getCreation().getTime()+
				" "+duration+
				" "+description);
		return idCounter;
	}

	@SuppressWarnings("unused")
	public synchronized int bidOnAuction(int id, User bidder, double money){
		Auction tmp= auctionList.get(id);
		if(bidder.getID()== tmp.getOwner().getID())
			return OWNER_SAME_AS_BIDDER;

		if(tmp!=null){
			if(tmp.isExpired())
				return AUCTION_EXPIRED;

			if(tmp.getPrice()>= money){
				return NEEDS_MORE_MONEY;
			}else{
				// BID_OVERBID event
				
				if(tmp.getHighestBidder()!=null)
					ServerStatus.getInstance().notifyAnalyticsServer(EventFactory.createBidEvent(tmp.getHighestBidder().getName(), id, tmp.getPrice(), 1));



				tmp.setHighestBidder(bidder);
				tmp.setPrice(money);
				auctionList.remove(id);
				auctionList.put(id, tmp);

				// BID_PLACED event

				ServerStatus.getInstance().notifyAnalyticsServer(EventFactory.createBidEvent(bidder.getName(), id, money, 0));

				return SUCCESSFULLY_PLACED_BID;
			}
		}else return NO_AUCTION_WITH_ID_FOUND;
	}

	public synchronized String getList(){
		String result = "";
		Auction auction=null;
		for(Integer key: auctionList.keySet()){
			auction= auctionList.get(key);
			if(!auction.isExpired()){
				result= result+"\t"+auction.toString()+"\n";				
			}			
		}

		if(result.length()==0)
			result="There are currently no auctions.";
		return result;
	}

	public synchronized ArrayList<Auction> getAuctionList(){
		ArrayList<Auction> tmp = new ArrayList<Auction>();
		Auction a=null;
		for(Integer key: auctionList.keySet()){
			a= auctionList.get(key);
			if(!a.isExpired()){
				tmp.add(a);
			}

		}
		return tmp;
	}


	public synchronized String getFullList(){
		String result = "";
		Auction auction=null;
		for(Integer key: auctionList.keySet()){
			auction= auctionList.get(key);
			result= result+auction.toDetailString()+"\n";				
		}
		if(result.length()==0)
			result="There are currently no auctions.";
		return result;
	}

	public synchronized String getDescription(int id){
		if(auctionList.containsKey(id))
			return auctionList.get(id).getDescription();
		else return null;
	}



}
