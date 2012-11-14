package managementClient.commands;

import java.rmi.RemoteException;

import managementClient.ManagementClientStatus;

import command.ICommand;

public class RemoveStep implements ICommand {

	@Override
	public int numberOfParams() {
		// <!removeStep <startPrice> <endPrice>>
		return 0; //TODO return 2;
	}

	@Override
	public String execute(String[] params) {
		
		try{
			double startPrice,endPrice=0;
			
			try{
				startPrice= Double.valueOf(params[0]);
			}catch(NumberFormatException e){
				return "!print "+params[0]+" not a number!";
			}
			try{
				endPrice= Double.valueOf(params[1]);
			}catch(NumberFormatException e){
				return "!print "+params[1]+" not a number!";
			}
			
			ManagementClientStatus.getInstance().getbillingServerSecure().deletePriceStep(startPrice,endPrice);


		}catch(RemoteException e){
			return "!print "+e.getMessage();
		}
		


		return "!print " +"removeStep not supported yet";
	}

	@Override
	public boolean needsRegistration() {
		return false; // TODO return true;
	}



}
