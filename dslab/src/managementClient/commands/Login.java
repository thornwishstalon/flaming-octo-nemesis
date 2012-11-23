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
		String message="";
		
		try {
			System.out.println("trying to login!");
			BillingServerSecure sec = ManagementClientStatus.getInstance().getBillingServer().login(username, MD5Helper.StringToMD5(password));
			
			System.out.println("lalala");
			if(sec!=null){
				ManagementClientStatus.getInstance().setbillingServerSecure(sec);
				System.out.println("login succss!");
				message= "!print "+"login successful";
			}
			else{
				System.out.println("sec null");
				message= "!print "+ "wrong username or password!";
			}
			System.out.println();
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			message= "!print "+e.getMessage();
		} catch (Exception e){
			message= "!print "+e.getMessage();
		}
		
		System.out.println(message);
		return message;
		
	}

	@Override
	public boolean needsRegistration() {
		return false;
	}

}
