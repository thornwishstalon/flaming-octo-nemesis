package managementClient.commands;

import java.rmi.RemoteException;

import managementClient.ManagementClientStatus;
import command.ICommand;

public class Unsubscribe implements ICommand {

	@Override
	public int numberOfParams() {
		return 1;
	}

	@Override
	public String execute(String[] params) {
		String sId= params[0];
		long id=0;
		try{
			id= Long.valueOf(sId);
		}catch(NumberFormatException e){
			return "!print "+sId+" is not a number";
		}
		
		try {
			ManagementClientStatus.getInstance().getAnalyticsServer().unsubscribe(id);
			ManagementClientStatus.getInstance().removeSubscription(id);
			return "!print subscription " + id + " terminated";
			
		} catch (RemoteException e) {
			e.printStackTrace();
			return "!print ERROR: subscription "+id+" could not be removed";
		}
	}

	@Override
	public boolean needsRegistration() {
		return true;
	}


}
