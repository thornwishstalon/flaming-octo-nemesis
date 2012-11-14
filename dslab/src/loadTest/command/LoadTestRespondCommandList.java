package loadTest.command;

import java.util.HashMap;

import command.ICommand;
import command.ICommandList;

public class LoadTestRespondCommandList implements ICommandList {
	private HashMap<String, ICommand> commands;
	
	public LoadTestRespondCommandList() {
		commands= new HashMap<String, ICommand>();
		
		/*
		 * handle !list responses
		 */
		
		
	}
	
	@Override
	public boolean containsKey(String commandKey) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ICommand get(String commandKey) {
		// TODO Auto-generated method stub
		return null;
	}

}
