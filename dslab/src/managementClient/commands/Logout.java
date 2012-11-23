package managementClient.commands;

import java.rmi.RemoteException;

import managementClient.ManagementClientStatus;
import command.ICommand;

public class Logout implements ICommand {

	@Override
	public int numberOfParams() {
		// <!logout>
		return 0;
	}

	@Override
	public String execute(String[] params) {
		/*
		try{
			ManagementClientStatus.getInstance().getbillingServerSecure().logout();
			ManagementClientStatus.getInstance().setUser("");
		}catch(RemoteException e){
			
		}
		*/
		
		return "!print " +"logout not supported yet";
	}

	@Override
	public boolean needsRegistration() { 
		return true;
	}


}
