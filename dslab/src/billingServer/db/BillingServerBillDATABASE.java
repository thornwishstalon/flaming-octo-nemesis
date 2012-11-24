package billingServer.db;

import java.util.concurrent.ConcurrentHashMap;
import billingServer.db.content.Bill;


public class BillingServerBillDATABASE {

	private ConcurrentHashMap<String, Bill> bills;
	
	public BillingServerBillDATABASE() {
			bills = new ConcurrentHashMap<String, Bill>();
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
			if(!bills.contains(user)) {
				Bill b = new Bill(user);
				bills.put(b.getUser(), b);
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
			if(bills.contains(user))
				return bills.get(user);
			else return null;
		}
	}
	
}
