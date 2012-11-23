package managementClient.commands;

import java.rmi.RemoteException;

import managementClient.ManagementClientStatus;
import managementClient.db.EventDATABASE;
import command.ICommand;

public class Subscribe implements ICommand {

	@Override
	public int numberOfParams() {
		//<!subscribe <regex> >
		return 1; //TODO 1;
	}

	@Override
	public String execute(String[] params) {
		String regex=params[0];
		
		try {
			long subID= ManagementClientStatus.getInstance().getAnalyticsServer()
								.subscribe(regex, EventDATABASE.getInstance().getCallback());
			return "!print"+" subscription with id "+subID+" created.";
		} catch (RemoteException e) {
			e.printStackTrace();
			return "!print "+"an error has occured.";
		}
		
		//return "!print " +"subscribe not supported yet";
	}

	@Override
	public boolean needsRegistration() {
		return false;
	}

	
}
