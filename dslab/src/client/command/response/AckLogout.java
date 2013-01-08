package client.command.response;

import network.security.SimpleStringStream;
import network.security.StaticStream;
import client.ClientMain;
import client.ClientStatus;
import command.ICommand;

/**
 * 
 * the server has acknowledged a logout
 *
 */
public class AckLogout implements ICommand{

	@Override
	public int numberOfParams() {
		return 0;
	}

	@Override
	public String execute(String[] params) {
		String name= ClientStatus.getInstance().getUser();
		ClientStatus.getInstance().setUser("");

		
		StaticStream.getStaticStreamInstance().setDecoderStream(new SimpleStringStream());
		StaticStream.getStaticStreamInstance().setEncoderStream(new SimpleStringStream());
		
		return "!print Successfully logged out as "+name+"!";

	}

	@Override
	public boolean needsRegistration() {
		return false;
	}

}
