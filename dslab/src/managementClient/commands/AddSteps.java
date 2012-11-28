package managementClient.commands;

import java.rmi.RemoteException;

import managementClient.ManagementClientStatus;
import command.ICommand;

public class AddSteps implements ICommand {

	@Override
	public int numberOfParams() {
		// <!addstep <startPrice> <endPrice> <fixedPrice> <variablePricePercent>> ---- 4 parameters
		return 4;
	}

	@Override
	public String execute(String[] params) {

		double startPrice=0, endPrice=0, fixedPrice, variablePricePercent;
		
		try{

			try{
				startPrice= Double.valueOf(params[0].trim());
			}catch(NumberFormatException e){
				return "!print "+params[0]+ " not a number!";
			}
			try{
				endPrice= Double.valueOf(params[1].trim());
			}catch(NumberFormatException e){
				return "!print "+params[1]+ " not a number!";
			}
			try{
				fixedPrice= Double.valueOf(params[2].trim());
			}catch(NumberFormatException e){
				return "!print "+params[2]+ " not a number!";
			}
			try{
				variablePricePercent= Double.valueOf(params[3].trim());
			}catch(NumberFormatException e){
				return "!print "+params[3]+ " not a number!";
			}

			ManagementClientStatus.getInstance().getbillingServerSecure().createPriceStep(
					startPrice,endPrice,fixedPrice,variablePricePercent);

			
			if(endPrice==0)
				return "!print Price step [" + startPrice + " INFINITY] successfully added ";
			else
				return "!print Price step [" + startPrice + " " + endPrice + "] successfully added ";
			

		}catch(RemoteException e){
			return "!print ERROR: Price step ["+startPrice+ " " + endPrice +"] could not be created.";
		}


		//return "!print " +"addSteps not supported yet";
	}

	@Override
	public boolean needsRegistration() {
		return true;
	}


}
