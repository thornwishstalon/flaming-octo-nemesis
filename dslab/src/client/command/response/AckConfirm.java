package client.command.response;

import client.ClientStatus;
import command.ICommand;

public class AckConfirm implements ICommand {

	@Override
	public int numberOfParams() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String execute(String[] params) {
		
		ClientStatus.getInstance().setBlocked(true);
		
		return "You have confirmed the poll!";
	}

	@Override
	public boolean needsRegistration() {
		// TODO Auto-generated method stub
		return false;
	}

}
