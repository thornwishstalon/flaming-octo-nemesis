package managementClient.commands;

import java.rmi.RemoteException;

import javax.xml.stream.events.StartDocument;

import managementClient.ManagementClientStatus;

import command.ICommand;

public class RemoveStep implements ICommand {

	@Override
	public int numberOfParams() {
		// <!removeStep <startPrice> <endPrice>>
		return 2;
	}

	@Override
	public String execute(String[] params) {
		double startPrice=0,endPrice=0;
		
		try{
			
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

			if(endPrice==0)
				return "!print Price step [" + startPrice + " INFINITY] successfully removed ";
			else
				return "!print Price step [" + startPrice + " " + endPrice + "] successfully removed ";

		}catch(RemoteException e){
			return "!print ERROR: Price step ["+startPrice+ " " + endPrice +"] does not exist.";
		}
		

	}

	@Override
	public boolean needsRegistration() {
		return true;
	}



}
