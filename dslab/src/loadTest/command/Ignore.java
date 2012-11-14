package loadTest.command;

import command.ICommand;

public class Ignore implements ICommand {

	@Override
	public int numberOfParams() {
		return 0;
	}

	@Override
	public String execute(String[] params) {
		return "";
	}

	@Override
	public boolean needsRegistration() {
		return false;
	}

}
