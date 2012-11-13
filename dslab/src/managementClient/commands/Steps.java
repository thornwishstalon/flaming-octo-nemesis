package managementClient.commands;

import command.ICommand;

public class Steps implements ICommand {

	@Override
	public int numberOfParams() {
		// <!steps>
		return 0;
	}

	@Override
	public String execute(String[] params) {
		/*
		try{
			PriceSteps steps = ManagementClientStatus.getInstance().getbillingServerSecure().getPriceSteps();
			System.out.println(steps.getFormattedString()); //something like that
			
		}catch(RemoteException e){
			System.out.println("ERROR: RemoteException: "+e.getMessage());
		}catch(Exception e){
			System.out.println("ERROR: "+e.getMessage());
		}
		*/
		
		return "!print " +"steps not supported yet";
	}

	@Override
	public boolean needsRegistration() {
		// TODO return true;
		return false;
	}



}
