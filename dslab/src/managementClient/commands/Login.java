package managementClient.commands;

import command.ICommand;

public class Login implements ICommand {

	@Override
	public int numberOfParams() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String execute(String[] params) {
		// TODO Auto-generated method stub
		return "!print " +"login not supported yet";
	}

	@Override
	public boolean needsRegistration() {
		// TODO Auto-generated method stub
		return false;
	}

}