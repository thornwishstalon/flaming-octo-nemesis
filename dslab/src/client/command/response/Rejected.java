package client.command.response;

import client.ClientStatus;
import command.ICommand;

public class Rejected implements ICommand {

	@Override
	public int numberOfParams() {
		return 1; //!rejected <error message>
	}

	@Override
	public String execute(String[] params) {
		ClientStatus.getInstance().setBlocked(false);
		
		if(ClientStatus.getInstance().isBlocked()){
			ClientStatus.getInstance().setBlocked(false);
			System.out.println("<BLOCK SOLVED>");
		}
		return "!print poll rejected: "+params[0];
	}

	@Override
	public boolean needsRegistration() {
		return true;
	}

}
