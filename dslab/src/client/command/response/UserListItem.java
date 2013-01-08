package client.command.response;

import client.ClientMain;
import client.LocalUserListItem;
import command.ICommand;

public class UserListItem implements ICommand {

	@Override
	public int numberOfParams() {
		// TODO Auto-generated method stub
		return 3;
	}

	@Override
	public String execute(String[] params) {

		String username = params[0];
		String ipAddress = params[1];
		int port = Integer.valueOf(params[2]);
		
		
		ClientMain.getClientSetup().addActiveClient(new LocalUserListItem(ipAddress, username, port));
		
		return ipAddress + ":" + port + " - " + username;

	}

	@Override
	public boolean needsRegistration() {
		return false;
	}

}
