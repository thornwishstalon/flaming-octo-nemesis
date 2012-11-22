package billingServer.db.content;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import billingServer.db.BillingServerPriceDATABASE;



public class Bill implements Serializable {

	private static final long serialVersionUID = 2398795574464591626L;
	private ArrayList<BillingLine> bills;
	private String user;
	
	public Bill(String user) {
		bills = new ArrayList<BillingLine>();
		this.user = user;
	}

	public void putBill(long auctionID, double price) {
		
		synchronized (bills) {
			BillingLine b = new BillingLine(auctionID, price);
			bills.add(b);
		}
		
	}

	public String getUser() {
		return user;
	}

	public synchronized void setUser(String user) {
		this.user = user;
	}	
	
	public String toString() {
		String out = "auction_ID	strike_price	fee_fixed	fee_variable	fee_total \n";
		

		synchronized (bills) {

			Iterator<BillingLine>  it= bills.iterator();
			BillingLine b;
			PriceStep p;
			double pPercent;

			while (it.hasNext()) {
				b = it.next();
				p = BillingServerPriceDATABASE.getInstance().getPriceStepForPrice(b.getPrice());
				pPercent = (p.getVariablePricePercent() * b.getPrice()) / 100;

				out += b.getAuctionID() + "       " + b.getPrice() + "       " + p.getFixedPrice()
						+ "        " + pPercent + "       " + (pPercent+p.getFixedPrice()) + "\n";
			}

		}
		
		return out;
	}
}
