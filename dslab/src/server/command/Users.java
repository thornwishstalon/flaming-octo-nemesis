package server.command;

import server.logic.UserDATABASE;
import command.ICommand;

public class Users implements ICommand{

	@Override
	public int numberOfParams() {
		return 0;
	}

	@Override
	public String execute(String[] params) {
		
		return "!print "+UserDATABASE.getInstance().getUserList();
	}

	@Override
	public boolean needsRegistration() {
		return false;
	}

	

}
