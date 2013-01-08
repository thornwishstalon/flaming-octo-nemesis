package client.command.response;

import client.ClientStatus;
import command.ICommand;


/**
 * print-command to simply transmit messages visible to the user
 *
 */
public class Print implements ICommand{

	@Override
	public int numberOfParams() {
		return 1;
	}

	@Override
	public String execute(String[] params) {
		String input= params[0];
		System.out.println(ClientStatus.getInstance().getUser() + "> "+input);
		return "";
	}

	@Override
	public boolean needsRegistration() {
		return false;
	}

}
