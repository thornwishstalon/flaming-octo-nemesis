package managementClient.commands;

import java.rmi.RemoteException;

import command.ICommand;

public class Bill implements ICommand {

	@Override
	public int numberOfParams() {
		// <!bill <username>>
		return 0; //TODO return 1;
	}

	@Override
	public String execute(String[] params) {
		/*
		String username=params[0];
		Bill bill=null;
		try{
			bill= ManagementClientStatus.getInstance().getbillingServerSecure().getBill(username);
			return "!print "+bill.toString(); //TODO
			
		}catch(RemoteException e){
			return "!print "+e.getMessage();
		}
		*/
		 return "!print " +"bill not supported yet";
	}

	@Override
	public boolean needsRegistration() {
		return false; // TODO return true;
	}


}
