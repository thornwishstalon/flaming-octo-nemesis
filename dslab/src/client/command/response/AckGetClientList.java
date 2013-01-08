package client.command.response;

import command.ICommand;

public class AckGetClientList implements ICommand {

	@Override
	public int numberOfParams() {
		return 0;
	}

	@Override
	public String execute(String[] params) {
		return "Active Clients";
	}

	@Override
	public boolean needsRegistration() {
		return false;
	}

}
