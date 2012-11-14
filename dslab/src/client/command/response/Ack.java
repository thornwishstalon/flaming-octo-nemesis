package client.command.response;

import client.ClientStatus;
import command.ICommand;

/**
 * the server has acknolodged something  
 *
 */

@Deprecated
public class Ack implements ICommand{

	@Override
	public int numberOfParams() {
		return 0;
	}

	@Override
	public String execute(String[] params) {
		ClientStatus.getInstance().setAck(true);
		System.out.println("acknowledged");
		return "\n";
	}

	@Override
	public boolean needsRegistration() {
		return false;
	}

}
