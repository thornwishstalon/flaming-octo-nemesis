package client.command;


import java.util.StringTokenizer;

import command.ICommand;

public class LocalCreate implements ICommand {

	@Override
	public int numberOfParams() {
		return 0; //don't care 
	}

	@Override
	public String execute(String[] params) {
		String inputLine= params[0];
		
		return fixDescriptionString(inputLine);
	}

	@Override
	public boolean needsRegistration() {
		return false;
	}
	
	private String fixDescriptionString(String input){
		StringTokenizer tokenizer= new StringTokenizer(input," ");

		String command= tokenizer.nextToken();
		String duration = "", description="";
		if(tokenizer.hasMoreTokens()){
			duration= tokenizer.nextToken();
			if(tokenizer.hasMoreTokens()){
				description="";
				while(tokenizer.hasMoreElements()){
					description+= tokenizer.nextToken().trim();
					if(tokenizer.hasMoreElements())
						description+="!_!";
				}

			}

		}
	
		return command+" "+duration+" "+description;
	}
	
	
	

}
