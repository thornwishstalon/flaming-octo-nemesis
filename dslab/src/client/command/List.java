package client.command;

import java.util.ArrayList;

import org.bouncycastle.util.encoders.Base64;

import network.security.Hesher;
import network.tcp.server.TCPServerConnection;
import server.logic.Auction;
import server.logic.AuctionDATABASE;
import command.ICommand;

public class List implements ICommand{
	private TCPServerConnection connection;
	private Hesher hesher;
	
	public List(TCPServerConnection connection){
		this.connection=connection;
	}

	@Override
	public int numberOfParams() {
		return 0;
	}

	@Override
	public String execute(String[] params) {
		/*
		//USE THIS if snippet if you want to the loadTest to work
		ArrayList<Auction> tmp= AuctionDATABASE.getInstance().getAuctionList();
		int c=0;
		for(Auction a:tmp){
			if(!a.isExpired()){
				c++;
				if(a.getHighestBidder()!=null){
					connection.print("!auction-item "+a.getID()+" "+a.getCreation().getTime()+" "+a.getDuration()*1000+" "+
							a.getOwner().getName()+" "+a.getHighestBidder().getName()+" "+a.getPrice()+" "+a.getDescription());
				}else{
					connection.print("!auction-item "+a.getID()+" "+a.getCreation().getTime()+" "+a.getDuration()*1000+" "+
							a.getOwner().getName()+" "+"none"+" "+"0.0"+" "+a.getDescription());
				}
			}
		}
		if(c==0){
			return "!print"+" There are currently no auctions!";
		}
		*/
		//OTHERWISE :
		String list=AuctionDATABASE.getInstance().getList();
		String answer="";
		
		if(!connection.getUser().equals("")){
			hesher= new Hesher();
			hesher.setKey(connection.getAesSecretKey());
			//answer= "!dehesh "+ hesher.hashMessage(new String( Base64.decode(list.getBytes())))+" "+list;
			answer= "!dehesh "+ new String (Base64.encode(hesher.hashMessage(list.trim()).getBytes()))+" "+list;
			return answer;
		}
		
		return "!print "+list;
	}

	@Override
	public boolean needsRegistration() {
		return false;
	}
	
	



}
