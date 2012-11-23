package managementClient.commands;

import managementClient.db.EventDATABASE;
import command.ICommand;

public class Hide implements ICommand {

	@Override
	public int numberOfParams() {
		return 0;
	}

	@Override
	public String execute(String[] params) {
		boolean auto =EventDATABASE.getInstance().isAuto();
		if(auto){
			EventDATABASE.getInstance().setAuto(false);
			return "!print "+" !auto-mode is off!";
		}else{
			return "!print "+" !auto-mode is already off!";
		}
		
		
	}

	@Override
	public boolean needsRegistration() {
		return true;
	}

}
