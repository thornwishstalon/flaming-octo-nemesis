package managementClient.commands;

import java.rmi.RemoteException;

import managementClient.ManagementClientStatus;
import billingServer.db.content.PriceStep;
import billingServer.db.content.PriceSteps;
import command.ICommand;

public class Steps implements ICommand {

	@Override
	public int numberOfParams() {
		// <!steps>
		return 0;
	}

	@Override
	public String execute(String[] params) {
		
		try{
			PriceSteps steps = ManagementClientStatus.getInstance().getbillingServerSecure().getPriceSteps();
			//System.out.println(steps.getFormattedString()); //something like that
			return ("!print "+steps.toString());
			
		}catch(Exception e){
			return ("!print "+ "ERROR: "+e.getMessage());
		}
		
		
		//return "!print " +"steps not supported yet";
	}

	@Override
	public boolean needsRegistration() {
		// TODO return true;
		return false;
	}



}
