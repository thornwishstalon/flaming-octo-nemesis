package billingServer.db;

import java.util.concurrent.ConcurrentHashMap;
import billingServer.db.content.Bill;


public class BillingServerBillDATABASE {

	private ConcurrentHashMap<String, Bill> bills;
	
	public BillingServerBillDATABASE() {
		bills = new ConcurrentHashMap<String, Bill>();
	}
	
	public void billAuction(String user, long auctionID, double price) {		
		
		if(!bills.contains(user)) {
			Bill b = new Bill(user);
			bills.put(b.getUser(), b);
		}
		
		bills.get(user).putBill(auctionID, price);
	}
	
	public Bill getBill(String user) {
		return bills.get(user);
	}
	
}
