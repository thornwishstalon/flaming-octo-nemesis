package loadTest.command;

import loadTest.TestClient;
import command.ICommand;

public class LoadTestAuctionItem implements ICommand {
	private TestClient client;
	
	public LoadTestAuctionItem(TestClient client){
		this.client=client;
	}
	
	@Override
	public int numberOfParams() {
		return 7;
	}

	@Override
	public String execute(String[] params) {
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
		
		int id= Integer.valueOf(params[0].trim());
		long creation= Long.valueOf(params[1].trim());
		long duration= Long.valueOf(params[2].trim());
		double price= Double.valueOf(params[5].trim());
		
		//pop auction item to testClient's list
		client.pop(id, params[3], params[4], creation, duration, price, params[6].trim());
		
		return "";
	}

	@Override
	public boolean needsRegistration() {
		return false;
	}

}
