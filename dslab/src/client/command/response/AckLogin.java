package client.command.response;

import client.ClientStatus;
import command.ICommand;

/**
 * the server has acknowledged a login
 *
 */

public class AckLogin implements ICommand {
	//private UDPSocket connection=null;
	
	
	public AckLogin() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public int numberOfParams() {
		return 1;
	}

	@Override
	public String execute(String[] params) {
		String username=params[0].trim();
		ClientStatus.getInstance().setUser(username);
		return "Successfully logged in as "+username;
	}

	@Override
	public boolean needsRegistration() {
		return false;
	}

}
