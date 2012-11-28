package managementClient.commands;

import java.rmi.RemoteException;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import managementClient.ManagementClientStatus;
import managementClient.db.EventDATABASE;
import command.ICommand;

public class Subscribe implements ICommand {

	@Override
	public int numberOfParams() {
		//<!subscribe <regex> >
		return 1;
	}

	@Override
	public String execute(String[] params) {
		String regex=params[0];
		
		try{
			Pattern.compile(regex);
		}catch(PatternSyntaxException e){
			return "!print "+"your regex-syntax is not valid!";
		}
		
		try {
			long subID= ManagementClientStatus.getInstance()
						.getAnalyticsServer().subscribe(regex, EventDATABASE.getInstance().getCallback());
			ManagementClientStatus.getInstance().addSubscription(subID);
							
			
			return "!print"+" subscription with id "+subID+" created.";
		} catch (RemoteException e) {
			e.printStackTrace();
			return "!print "+"an error has occured.";
		}
		
		//return "!print " +"subscribe not supported yet";
	}

	@Override
	public boolean needsRegistration() {
		return true;
	}

	
}
