package managementClient.commands;

import managementClient.db.EventDATABASE;
import command.ICommand;

public class Hide implements ICommand {

	@Override
	public int numberOfParams() {
		return 0;
	}

	@Override
	public String execute(String[] params) {
		
		EventDATABASE.getInstance().setAuto(true);
		
		return "!print "+" in auto mode!";
	}

	@Override
	public boolean needsRegistration() {
		return true;
	}

}
