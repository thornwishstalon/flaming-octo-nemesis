package client.command;

import client.ClientMain;
import client.ClientStatus;
import command.ICommand;

public class LocalLogin implements ICommand {

	
	@Override
	public int numberOfParams() {
		return 1;
	}

	@Override
	public String execute(String[] params) {
		if(!ClientStatus.getInstance().getUser().equals("")){
			System.out.println("Error: "+ClientStatus.getInstance().getUser()+" still logged in!");
			return "";
		}
		else return params[0];
	}

	@Override
	public boolean needsRegistration() {
		return false;
	}

}
