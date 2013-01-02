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
		ClientStatus.getInstance().setBlocked(true);
		System.out.println("<BLOCK>");
		
		return "poll confirmed by you";
	}

	@Override
	public boolean needsRegistration() {
		// TODO Auto-generated method stub
		return true;
	}

}
