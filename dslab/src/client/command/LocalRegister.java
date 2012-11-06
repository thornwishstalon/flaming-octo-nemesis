package client.command;

import command.ICommand;

public class LocalRegister implements ICommand{
	private int port=0;
	
	public LocalRegister(int port) {
		this.port=port;
	}
	
	
	@Override
	public int numberOfParams() {
		return 0;
	}

	@Override
	public String execute(String[] params) {
		return  params[0]+" "+port;
	}

	@Override
	public boolean needsRegistration() {
		return false;
	}
	

}
