package server.command;

import java.io.IOException;

import server.ServerMain;
import command.ICommand;

public class Close implements ICommand {

	@Override
	public int numberOfParams() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String execute(String[] params) {

		try {
			ServerMain.getServer().closeConnections();
			ServerMain.setServer(null);
		} catch (IOException e) {
			return "!print An error occured while switching to OFFLINE-Mode.";
		}
		return "!print Server is now in OFFLINE-MODE. User !reconnect to activate ONLINE-MODE again.";
	}

	@Override
	public boolean needsRegistration() {
		// TODO Auto-generated method stub
		return false;
	}

}
