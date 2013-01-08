package client.command.response;

import client.ClientStatus;
import command.ICommand;

public class Confirmed implements ICommand {

	@Override
	public int numberOfParams() {
		return 0;
	}

	@Override
	public String execute(String[] params) {
		ClientStatus.getInstance().setBlocked(false);
		//System.out.println("<BLOCK>");
		
		return "!print Poll was confirmed. Your bid will be placed.";
	}

	@Override
	public boolean needsRegistration() {
		// TODO Auto-generated method stub
		return true;
	}

}
