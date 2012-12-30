package client.command;


import server.logic.UserDATABASE;
import command.ICommand;

public class SendAll implements ICommand {

	@Override
	public int numberOfParams() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public String execute(String[] params) {
		String message= params[0];
		
		UserDATABASE.getInstance().notifyLoggedInUsers("!print "+message);
		
		
		return "";
	}

	@Override
	public boolean needsRegistration() {
		// TODO Auto-generated method stub
		return false;
	}

}
