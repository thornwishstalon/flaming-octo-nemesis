package client.command.response;

import command.ICommand;

public class Rejected implements ICommand {

	@Override
	public int numberOfParams() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String execute(String[] params) {
		// TODO Auto-generated method stub
		return "poll rejected";
	}

	@Override
	public boolean needsRegistration() {
		// TODO Auto-generated method stub
		return false;
	}

}
