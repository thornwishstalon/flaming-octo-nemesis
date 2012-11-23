package managementClient.commands;

import managementClient.db.EventDATABASE;
import command.ICommand;

public class Auto implements ICommand {

	@Override
	public int numberOfParams() {
		return 0;
	}

	@Override
	public String execute(String[] params) {
		EventDATABASE.getInstance().setAuto(false);
		return "!print "+" no longer in auto mode!";
	}

	@Override
	public boolean needsRegistration() {
		return true;
	}

}
