package client.command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

import server.logic.Auction;
import server.logic.AuctionDATABASE;
import server.logic.UserDATABASE;
import network.tcp.server.TCPServerConnection;
import command.ICommand;

public class GetClientList  implements ICommand {

	private TCPServerConnection connection;

	public GetClientList(TCPServerConnection connection) {
		this.connection=connection;
	}
	
	@Override
	public int numberOfParams() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String execute(String[] params) {
		
		String tmp = UserDATABASE.getInstance().getClientList();
		BufferedReader bufReader = new BufferedReader(new StringReader(tmp));
		
		String line=null;
		int c=0;
		
		try {
			connection.print("!ack-getClientList");
			
			while( (line=bufReader.readLine()) != null )
			{
				c++;
				connection.print("!userlist-item " +  line);
			}
		} catch (IOException e) {
			System.out.println("Userlist was requested but could not be created!");
		}
		
		if(c==0){
			return "!print"+" There are no other active users!";
		}

		return "";//"!print "+AuctionDATABASE.getInstance().getList();
	}

	@Override
	public boolean needsRegistration() {
		return true;
	}

}
