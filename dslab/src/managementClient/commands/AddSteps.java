package managementClient.commands;

import command.ICommand;

public class AddSteps implements ICommand {

	@Override
	public int numberOfParams() {
		// <!addstep <startPrice> <endPrice> <fixedPrice> <variablePricePercent>> ---- 4 parameters
		return 0; // TODO return 4
	}

	@Override
	public String execute(String[] params) {
		/*
		try{
			try{
				double startPrice= Double.valueOf(params[0]);
			}catch(NumberFormatException e){
				return "!print "+params[0]+ " not a number!";
			}
			try{
				double endPrice= Double.valueOf(params[1]);
			}catch(NumberFormatException e){
				return "!print "+params[1]+ " not a number!";
			}
			try{
				double fixedPrice= Double.valueOf(params[2]);
			}catch(NumberFormatException e){
				return "!print "+params[2]+ " not a number!";
			}
			try{
				double variablePricePercent= Double.valueOf(params[3]);
			}catch(NumberFormatException e){
				return "!print "+params[3]+ " not a number!";
			}

			ManagementClientStatus.getInstance().getbillingServerSecure().createPriceStep(
			startPrice,endPrice,fixedPrice,variablePricePercent);

			return "!print"+"Success...."; //TODO create success message

		}catch(RemoteException e){
			return "!print "+"ERROR: RemoteException: "+e.getMessage();
		}
		*/

		return "!print " +"addSteps not supported yet";
	}

	@Override
	public boolean needsRegistration() {
		// TODO return TRUE!!
		return false;
	}


}
