package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import server.command.ServerCommandList;
import server.command.ServerRespondList;
import server.logic.IUserRelated;

import command.CommandParser;

public class ServerInput extends Thread implements IUserRelated{
	private BufferedReader in;
	private CommandParser parser;
	private CommandParser respondParser;
	
	public void run(){
		
		try {
		in= new BufferedReader(new InputStreamReader(System.in));
		String input=null;
		parser= new CommandParser(true,this);
		parser.setCommandList(new ServerCommandList());
		
		respondParser = new CommandParser(true, this);
		respondParser.setCommandList(new ServerRespondList());
		
			while((input= in.readLine())!=null){
				//if(in.) break;
				break;
				/* //more commands
				if(input.contains("!exit")) break;
				
				if(input.length()>1)
					System.out.println("server> "+input+"\n"+respondParser.parse(parser.parse(input)));
				*/
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally{
			System.out.println("input thread closing");
			try {
				
				in.close();
				this.finalize();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}

	@Override
	public String getUser() {
		return "";
	}

	@Override
	public void setUser(String user) {
		
	}

}
