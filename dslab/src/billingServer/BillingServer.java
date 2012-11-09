package billingServer;

import billingServer.db.BillingServerUserDATABASE;
import billingServer.db.values.MD5Helper;

public class BillingServer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BillingServerSetup setup= new BillingServerSetup(args);
		
		BillingServerUserDATABASE users= new BillingServerUserDATABASE();
		
		System.out.println("BillingServer READY!");
		System.out.println(users.getUserList());
		
		//System.out.println(MD5Helper.StringToMD5("dslab2012"));
		
		

	}

}
