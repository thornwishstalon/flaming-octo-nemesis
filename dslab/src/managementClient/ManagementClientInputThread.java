package managementClient;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import managementClient.commands.ManagementClientCommandList;
import managementClient.commands.ManagementClientRespondCommandList;

import server.logic.IUserRelated;

import command.CommandParser;

/**
 * Thread handling User input 
 * 
 */

public class ManagementClientInputThread extends Thread implements IUserRelated{
	private BufferedReader in;
	private CommandParser parser;
	private CommandParser respondParser;
	
	
	
	public void run(){

		
		try {
		in= new BufferedReader(new InputStreamReader(System.in));
		String input=null;
		parser= new CommandParser(true,this);
		parser.setCommandList(new ManagementClientCommandList());
		
		respondParser = new CommandParser(true, this);
		respondParser.setCommandList(new ManagementClientRespondCommandList());
		
			while((input= in.readLine())!=null){
				
				if(input.contains("!exit")){
					//TODO Logout first!!!!
					break;
				}
				
				if(input.length()>1) //TODO 
					System.out.println("MANAGEMENT> "+input+"\n"+respondParser.parse(parser.parse(input)));
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
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public void setUser(String user) {
		// TODO Auto-generated method stub
		
	}
	

}
