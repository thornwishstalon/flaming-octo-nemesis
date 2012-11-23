package managementClient.commands;

import java.rmi.RemoteException;

import managementClient.ManagementClientStatus;
import command.ICommand;

public class Unsubscribe implements ICommand {

	@Override
	public int numberOfParams() {
		return 0;
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
			return "!print"+" success!";
			
		} catch (RemoteException e) {
			e.printStackTrace();
			return "!print"+" error!";
		}
		
		//return "!print " +"unsubscribe not supported yet";
	}

	@Override
	public boolean needsRegistration() {
		return true;
	}


}
