package client.command.response;

import java.text.SimpleDateFormat;
import java.util.Date;

import command.ICommand;

/**
 *  detailed information about an auction
 */
public class AuctionItem implements ICommand{

	@Override
	public int numberOfParams() {
		return 7;
	}

	@Override
	public String execute(String[] params) {
		SimpleDateFormat df= new SimpleDateFormat("dd.MM.yyyy kk:mm z");
		/*
		 * 
		 * 0 a.getID()+" "+
		 * 1 a.getCreation().getTime()+" "+
		 * 2 a.getDuration()*1000+
		   3 a.getOwner().getName()+" "+
		   4 a.getHighestBidder().getName()+" "+
		   5 a.getPrice()+" "+
		   6 a.getDescription());
		 */
		long expire= Long.valueOf(params[1].trim())+Long.valueOf(params[2].trim());
		
		String answer= "\t"+params[0].trim()+". '"+params[6].trim()+"' "+params[3].trim()+" "+ df.format(new Date(expire))+" "+
		params[4].trim()+" "+params[5].trim();
		
		return answer;
	}

	@Override
	public boolean needsRegistration() {
		return false;
	}

}
