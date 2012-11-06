package command;

public class CommandException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6670526090128484851L;
	private String message;
	
	public CommandException(String message){
		this.message= message;
	}
	
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return message;//super.getMessage();
	}
}
