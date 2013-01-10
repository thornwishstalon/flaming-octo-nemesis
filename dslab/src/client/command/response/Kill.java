package client.command.response;


import client.ClientMain;
import client.ClientStatus;
import command.ICommand;

/**
 * remote kill command
 *
 */
public class Kill implements ICommand {

	@Override
	public int numberOfParams() {
		return 0;
	}

	@Override
	public String execute(String[] params) {
		
		System.out.println("Server has gone offline!");
		ClientStatus.getInstance().killClient();
		ClientMain.kill();
		
		return "\n";
	}

	@Override
	public boolean needsRegistration() {
		return false;
	}

}
