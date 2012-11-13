package managementClient.commands;

import command.ICommand;

public class Subscribe implements ICommand {

	@Override
	public int numberOfParams() {
		//<!subscribe <regex> >
		return 0; //TODO 1;
	}

	@Override
	public String execute(String[] params) {
		return "!print " +"subscribe not supported yet";
	}

	@Override
	public boolean needsRegistration() {
		// TODO Auto-generated method stub
		return false;
	}

	
}
