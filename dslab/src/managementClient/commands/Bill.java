package managementClient.commands;

import java.rmi.RemoteException;

import managementClient.ManagementClientStatus;

import command.ICommand;

public class Bill implements ICommand {

	@Override
	public int numberOfParams() {
		// <!bill <username>>
		return 1;
	}

	@Override
	public String execute(String[] params) {
		
		String username=params[0];
		
		billingServer.db.content.Bill bill=null;
		try{
			bill= ManagementClientStatus.getInstance().getbillingServerSecure().getBill(username);
			if(bill!=null)
				return "!print "+bill.toString(); //TODO
			else return "!print "+" error: Bill is null. Please check if the user exists and has items to bill.";
			
		}catch(RemoteException e){
			return "!print "+e.getMessage();
		}

	}

	@Override
	public boolean needsRegistration() {
		return true;
	}


}
