package managementClient.commands;

import managementClient.db.EventDATABASE;
import command.ICommand;

public class Print implements ICommand {

	@Override
	public int numberOfParams() {
		return 0;
	}

	@Override
	public String execute(String[] params) {
		
		return "!print "+ EventDATABASE.getInstance().printBuffer();
	}

	@Override
	public boolean needsRegistration() {
		return true;
	}

}
