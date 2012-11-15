package loadTest.command;

import loadTest.TestClient;
import command.ICommand;

/**
 * 
 * NOT SURE IF NECESSARY //TODO
 *
 */
public class LoadTestACKLogout implements ICommand {
	private TestClient client;
	
	public LoadTestACKLogout(TestClient client) {
		this.client=client;
	}
	
	@Override
	public int numberOfParams() {
		return 0;
	}

	@Override
	public String execute(String[] params) {
		//System.out.println("logout ack");
		client.setAckLogout(true);
		return "";
	}

	@Override
	public boolean needsRegistration() {
		// TODO Auto-generated method stub
		return false;
	}

}
