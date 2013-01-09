package server.command;

import java.io.IOException;

import server.ServerMain;
import command.ICommand;

public class Reconnect implements ICommand {

	@Override
	public int numberOfParams() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String execute(String[] params) {

		try {
			ServerMain.startServerSocket();
		} catch (IOException e) {
			return "!print An error occured while switching to ONLINE-Mode.";
		}
		return "!print Server is now in ONLINE-MODE.";
	}

	@Override
	public boolean needsRegistration() {
		// TODO Auto-generated method stub
		return false;
	}

}
