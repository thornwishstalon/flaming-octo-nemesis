package command;

public interface ICommandList {

	public boolean containsKey(String commandKey);
	public ICommand get(String commandKey);
}
