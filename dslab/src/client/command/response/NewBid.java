package client.command.response;

import command.ICommand;


/**
 * notification that the user has been overbid on an auction
 *
 */
public class NewBid implements ICommand {

	@Override
	public int numberOfParams() {
		return 1;
	}

	@Override
	public String execute(String[] params) {
		String description= params[0];
		return " You have been overbid on '"+description+"'";
	}

	@Override
	public boolean needsRegistration() {
		return false;
	}

}
