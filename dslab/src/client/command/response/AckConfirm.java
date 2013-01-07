package client.command.response;

import client.ClientStatus;
import command.ICommand;

public class AckConfirm implements ICommand {

	@Override
	public int numberOfParams() {
		return 0;
	}

	@Override
	public String execute(String[] params) {
		ClientStatus.getInstance().setBlocked(true);

		return "You have the poll confirmed!";
	}

	@Override
	public boolean needsRegistration() {
		return false;
	}

}
