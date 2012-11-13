package managementClient.commands;

import command.ICommand;

public class Subscribe implements ICommand {

	@Override
	public int numberOfParams() {
		//<!subscribe <regex> >
		return 1; //TODO 1;
	}

	@Override
	public String execute(String[] params) {
		System.out.println(params[0]);
		
		return "!print " +"subscribe not supported yet";
	}

	@Override
	public boolean needsRegistration() {
		return false;
	}

	
}
