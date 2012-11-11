package managementClient.commands;

import java.rmi.RemoteException;

import billingServer.db.content.MD5Helper;
import billingServer.remote.BillingServerSecure;
import managementClient.ManagementClientStatus;
import command.ICommand;

public class Login implements ICommand {

	@Override
	public int numberOfParams() {
		return 2; // username and password
	}

	@Override
	public String execute(String[] params) {
		String username = params[0];
		String password = params[1];
		
		try {
			
			BillingServerSecure sec = ManagementClientStatus.getInstance().getBillingServer().login(username, MD5Helper.StringToMD5(password));
			if(sec!=null){
				ManagementClientStatus.getInstance().setbillingServerSecure(sec);
				return "!print "+"login successful";
			}
			else return "!print "+ "an error has occured.";
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return e.getMessage();
		} catch (Exception e){
			return e.getMessage();
		}
		
		
		//return "!print " +"login not supported yet";
	}

	@Override
	public boolean needsRegistration() {
		return false;
	}

}
