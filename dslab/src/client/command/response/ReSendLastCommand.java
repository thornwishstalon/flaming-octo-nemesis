package client.command.response;

import client.ClientStatus;
import network.tcp.client.TCPOutputConnection;
import command.ICommand;

public class ReSendLastCommand implements ICommand {
	private TCPOutputConnection connection;

	public ReSendLastCommand(TCPOutputConnection con){
		this.connection=con;
	}


	@Override
	public int numberOfParams() {
		return 0;
	}

	@Override
	public String execute(String[] params) {
		int tryR=ClientStatus.getInstance().getResendTry();

		if(tryR<2){
			ClientStatus.getInstance().setResendTry(tryR+1);
			connection.printToOutputstream(ClientStatus.getInstance().getLastCommand());
			System.out.println("[DEBUG]resend Command");
		}
		return "";
	}

	@Override
	public boolean needsRegistration() {
		return false;
	}

}
