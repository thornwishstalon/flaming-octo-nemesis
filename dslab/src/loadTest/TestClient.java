package loadTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketException;
import java.util.TimerTask;

import loadTest.command.LoadTestRespondCommandList;

import server.logic.IUserRelated;

import command.CommandParser;


public class TestClient extends Thread implements IUserRelated{
	private int port;
	private int serverPort;
	private String serverName;
	private CommandParser parser;
	
	private Socket socket=null;
	private BufferedReader reader=null;

	//connection relevant


	public TestClient(int port, int serverPort, String serverName, int updatesPerMinute, int auctionsPerMinute, int bidsPerMinute)  {
		this.port=port;
		this.serverPort= serverPort;
		this.serverName= serverName;
		parser= new CommandParser(true, this);
		parser.setCommandList(new LoadTestRespondCommandList());
	}

	public void run(){
		reader= null;
		try {
			socket = new Socket(serverName, serverPort); 

			reader= new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String input;

			while((input= reader.readLine())!=null){
				parser.parse(input);
				//System.out.println(ClientStatus.getInstance().getUser() + "> "+answer);
			}

		}catch(SocketException e){

		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} 
		finally{
			if(socket!=null)
				shutdown();
		}		
	}

	public void shutdown() {
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
			// TODO Auto-generated method stub

		}

	}

	private class ListUpdateTask extends TimerTask{

		@Override
		public void run() {
			// TODO Auto-generated method stub

		}	
	}

	private class BidTask extends TimerTask{

		@Override
		public void run() {
			// TODO Auto-generated method stub

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

}

