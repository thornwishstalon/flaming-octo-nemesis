package client.command;

import network.security.Hesher;
import network.tcp.server.TCPServerConnection;

import org.bouncycastle.util.encoders.Base64;

import server.logic.AuctionDATABASE;
import command.ICommand;

public class Polls implements ICommand {

	private TCPServerConnection connection;
	private Hesher hesher;
	
	public Polls(TCPServerConnection connection){
		this.connection=connection;
	}
	@Override
	public int numberOfParams() {
		return 0;
	}

	@Override
	public String execute(String[] params) {
		
		
		String list=AuctionDATABASE.getInstance().getPollList();
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
		return true;
	}

}
