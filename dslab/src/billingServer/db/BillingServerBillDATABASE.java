package billingServer.db;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import billingServer.db.content.Bill;


public class BillingServerBillDATABASE {

	private HashMap<String, Bill> bills;
	private static BillingServerBillDATABASE instance;
	
	private BillingServerBillDATABASE() {
			bills = new HashMap<String, Bill>();	
	}
	
	public static BillingServerBillDATABASE getInstance(){
		if(instance==null){
			instance= new BillingServerBillDATABASE();
		}
		return instance;
	}
	
	
	/**
	 * Adds another element to the users bill.
	 * First element entered creates a new Bill for the user.
	 * 
	 * @param user			Name of the user
	 * @param auctionID 	ID of auction that ended
	 * @param price			Final bid
	 */
	public void billAuction(String user, long auctionID, double price) {	

		synchronized (bills) {
			if(!bills.containsKey(user)) {	
				bills.put(user, new Bill(user));
			}

			bills.get(user).putBill(auctionID, price);
			
		}
	}
	

	/**
	 * @param user
	 * @return
	 */
	public Bill getBill(String user) {
		
		synchronized (bills) {			
			return bills.get(user);
		}
	}
	
}
