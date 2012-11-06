package command;

public class CommandList {
	private ICommandList commands;
	
	
	public boolean hasCommand(String commandKey){
		return commands.containsKey(commandKey);
	}
	
	public ICommand getCommand(String commandKey){
		return commands.get(commandKey);
	}
	
	public void setCommandList(ICommandList list){
		this.commands= list;
	}
	
	
	

}
