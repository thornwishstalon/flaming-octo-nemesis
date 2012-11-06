package command;


public interface ICommand {
	public int numberOfParams();
	public  String execute(String[] params);
	public boolean needsRegistration();
}
