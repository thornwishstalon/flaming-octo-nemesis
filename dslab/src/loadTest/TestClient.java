package loadTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
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

	public TestClient(LoadTestSetup setup, int id, int delay)  {
		this.ID= id;
		this.setup= setup;

		parser= new CommandParser(true, this);
		parser.setCommandList(new LoadTestRespondCommandList(this));

		auctionList= new ArrayList<LoadTestAuction>();
	}

	public void run(){
		reader= null;
		try {

			sleep(delay); //generate asynchronized load for server

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

		}catch(SocketException e){

		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		finally{
			//if(socket!=null)
			shutdown();
		}		
	}

	public void shutdown() {
		
		
		if(bidTimer!=null)
			bidTimer.cancel();
		if(createTimer!=null)
			createTimer.cancel();
		if(updateTimer!=null)
			updateTimer.cancel();

		//logout client before closing
		printer.println("!logout");

		
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


	private class AuctionTask extends TimerTask{

		@Override
		public void run() {

			synchronized (printer) {
				//System.out.println("auctionTask");
				printer.println("!create "+setup.getAuctionDuration()+" test_auction"+ID+" "+auctionCounter++);
			}

		}

	}

	private class ListUpdateTask extends TimerTask{

		@Override
		public void run() {
			synchronized (printer) {
				//System.out.println("ListUpdateTask");
				auctionList.clear();
				printer.println("!list");
			}

		}	
	}

	private class BidTask extends TimerTask{

		@Override
		public void run() {
			System.out.println("bidTask");
			LoadTestAuction auction= getAuction();
			if(auction!=null){
				//System.out.println("id: "+auction.getID());
				long price= System.currentTimeMillis()-auction.getCreation();
				//System.out.println(price);
				
				synchronized(printer){
					printer.println("!bid "+auction.getID()+" "+price);
				}
			}

		}

		private LoadTestAuction getAuction(){

			System.out.println(ID+" size: "+auctionList.size());
			if(auctionList.size()==0){
				return null;
			}else{
				Random r= new Random();
				int a=r.nextInt(auctionList.size());

				return auctionList.get(a); 
			}

		}

	}

	@Override
	public String getUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setUser(String user) {
		// TODO Auto-generated method stub

	}

	public synchronized void pop(int id, String owner,String bidder, long creation,long duration, double price, String description){
		//if(!owner.equals("testClient"+ID)){ //uncomment to add only foreign auctions
			LoadTestAuction tmp= new LoadTestAuction(id, owner, bidder, creation, duration, price, description);
			auctionList.add(tmp);
		//}
	} 

}

