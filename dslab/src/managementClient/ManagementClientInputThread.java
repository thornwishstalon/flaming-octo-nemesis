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
		System.out.println("READY for user-input\n");
		
			while((input= in.readLine())!=null){
				
				if(input.contains("!exit")){
					break;
				}
				
				if(input.length()>1) { 
					System.out.println(ManagementClientStatus.getInstance().getUser()+
							"> "+respondParser.parse(parser.parse(input)));
				}
			}
		} catch (IOException e) {
			//e.printStackTrace();
		}
		finally{
			System.out.println("input thread closing");
			try {
				in.close();
				
			} catch (IOException e) {
				//e.printStackTrace();
			} 
		}
		
		
	
	}



	@Override
	public String getUser() {
		return ManagementClientStatus.getInstance().getUser();
	}



	@Override
	public void setUser(String user) {
		ManagementClientStatus.getInstance().setUser(user);
		
	}
	

}
