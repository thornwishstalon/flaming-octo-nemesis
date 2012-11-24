package loadTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import loadTest.command.LoadTestRespondCommandList;

import server.logic.IUserRelated;

import command.CommandParser;


public class TestClient extends Thread implements IUserRelated{
	private int ID;
	private LoadTestSetup setup;
	
	/*
	 * basic CommandParser like used in any other part of this application, but stripped of the normal client behavior. 
	 * see @see loadTest.command.LoadTestRespondCommandList for more details
	 */
	private CommandParser parser; 

	private Socket socket=null;
	private BufferedReader reader=null;
	private PrintWriter printer=null;

	private ArrayList<LoadTestAuction> auctionList;
	//connection relevant
	private int auctionCounter=0;

	
	
	private Timer bidTimer;
	private Timer updateTimer;
	private Timer createTimer;
	private int delay;
	
	private boolean ACKLogout=false;

	public TestClient(LoadTestSetup setup, int id, int delay)  {
		this.ID= id;
		this.setup= setup;
		this.delay=delay;
		
		parser= new CommandParser(true, this);
		parser.setCommandList(new LoadTestRespondCommandList(this));

		auctionList= new ArrayList<LoadTestAuction>();
	}

	public void run(){
		reader= null;
		try {

			sleep(delay); //--> generates asynchronized load for server(delay is a randomized value)

			socket = new Socket(setup.getServerHost(), setup.getServerPort()); 

			reader= new BufferedReader(new InputStreamReader(socket.getInputStream()));
			printer= new PrintWriter(socket.getOutputStream(),true);

			String input;

			//login testClient
			printer.println("!login testClient"+ID);


			//create TASKS
			bidTimer= new Timer();
			createTimer= new Timer();
			updateTimer= new Timer();

			//scheduleTASKS
			createTimer.scheduleAtFixedRate(new AuctionTask(), 100, (60/setup.getAuctionsPerMin())*1000);
			updateTimer.scheduleAtFixedRate(new ListUpdateTask(), 200, setup.getUpdateIntervalSec()*1000);
			bidTimer.scheduleAtFixedRate(new BidTask(), 300, (60/setup.getBidsPerMin())*1000);
			

			//timer.scheduleAtFixedRate(new MessageBrokerTask(), 100, 1000);

			//wait for responses
			while((input= reader.readLine())!=null){
				parser.parse(input);
				//System.out.println(ClientStatus.getInstance().getUser() + "> "+answer);
			}

		}catch(UnknownHostException e){
			System.out.println("SERVER UNREACHABLE");
		}
		catch(SocketException e){
			//nothing to do
		}
		catch (IOException e) {
			//nothing to do			
		} catch (InterruptedException e) {
			//nothing to do
		} 
		finally{	
			shutdown();
		}		
	}

	/**
	 *  kill this thread plus freeing all it's ressources
	 */
	public void shutdown() {
		
		if(bidTimer!=null)
			bidTimer.cancel();
		if(createTimer!=null)
			createTimer.cancel();
		if(updateTimer!=null)
			updateTimer.cancel();
		
		if(printer!=null){
			//logout client before closing
			printer.println("!logout");
			
			boolean ack=false;
			long start= System.currentTimeMillis();
			
			while(!ack){
				//System.out.println(ack);
				if(System.currentTimeMillis()-start > 1000){// timeout
					break;
				}
				ack= isACKLogout();

			}

			printer.close();
			
		}

		
		if(socket!=null){
			try {
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(reader!=null){
			try {
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		

	}
	
	/**
	 * TimerTask to send a create-command to the server
	 *
	 */

	private class AuctionTask extends TimerTask{

		@Override
		public void run() {

			synchronized (printer) {
				//System.out.println("auctionTask");
				printer.println("!create "+setup.getAuctionDuration()+" test_auction"+ID+" "+(auctionCounter++));
			}

		}

	}
	
	/**
	 * 
	 * TimerTask to request the current list of auctions 
	 *
	 */

	private class ListUpdateTask extends TimerTask{

		@Override
		public void run() {
			synchronized (printer) {
				//System.out.println("ListUpdateTask");
				auctionList.clear(); //drop old values from the list
				printer.println("!list");
			}

		}	
	}

	/**
	 * 
	 * TimerTask to send a !bid command (bids for a random auction in the clienT's list ) to the server
	 *
	 */
	private class BidTask extends TimerTask{

		@Override
		public void run() {
			LoadTestAuction auction= getAuction();
			if(auction!=null){
				//determine the bid-value(= time passed since the auction'S creation in milliseconds)
				long price= System.currentTimeMillis()-auction.getCreation();
				
				//send command to the server
				synchronized(printer){
					printer.println("!bid "+auction.getID()+" "+price);
				}
			}

		}
		/**
		 * 
		 * @return a random auction from the client's list
		 */
		private LoadTestAuction getAuction(){
			//System.out.println(ID+" size: "+auctionList.size());
			if(auctionList.size()==0){
				return null;
			}else{
				Random r= new Random();
				int a=r.nextInt(auctionList.size());

				return auctionList.get(a); 
			}

		}

	}
	
	
	/**
	 * adds an AuctionItem to the client's auction list
	 * 
	 * @param id 			auction id
	 * @param owner			auction owner
	 * @param bidder		auction current top bidder
	 * @param creation		auction's creation as a long value
	 * @param duration		auction's duration in milliseconds (also long)
	 * @param price			auction's current highest bid (double)
	 * @param description   auction's description-string
	 */
	public synchronized void push(int id, String owner,String bidder, long creation,long duration, double price, String description){
		//if(!owner.equals("testClient"+ID)){ //uncomment to add only foreign auctions
			LoadTestAuction tmp= new LoadTestAuction(id, owner, bidder, creation, duration, price, description);
			auctionList.add(tmp);
		//}
	}

	public void setAckLogout(boolean b) {
		ACKLogout= true;
	} 
	
	public boolean isACKLogout() {
		return ACKLogout;
	}
	
	//IUserRelevant methods
	@Override
	public String getUser() {
		return "testClient"+ID;
	}

	
	@Override
	public void setUser(String user) {
		//notihing to do

	}

}

