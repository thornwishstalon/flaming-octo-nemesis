package client.command.response;

import command.ICommand;

public class Print implements ICommand{

	@Override
	public int numberOfParams() {
		return 1;
	}

	@Override
	public String execute(String[] params) {
		String input= params[0];
		return input;
	}

	@Override
	public boolean needsRegistration() {
		return false;
	}

}
