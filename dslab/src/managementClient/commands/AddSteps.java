package managementClient.commands;

import command.ICommand;

public class AddSteps implements ICommand {

	@Override
	public int numberOfParams() {
		
		return 0;
	}

	@Override
	public String execute(String[] params) {
		return "!print " +"addSteps not supported yet";
	}

	@Override
	public boolean needsRegistration() {
		// TODO Auto-generated method stub
		return false;
	}


}