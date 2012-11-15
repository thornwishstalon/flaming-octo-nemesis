package billingServer.db;

import java.util.ArrayList;
import java.util.Iterator;

import billingServer.db.content.PriceStep;
import billingServer.db.content.PriceSteps;

public class BillingServerPriceDATABASE {
	private static BillingServerPriceDATABASE instance=null;
	
	private ArrayList<PriceStep> steps;
	private BillingServerPriceDATABASE(){
		steps= new ArrayList<PriceStep>();
	}
	
	public static BillingServerPriceDATABASE getInstance(){
		if(instance==null){
			instance= new BillingServerPriceDATABASE();
		}
		return instance;
	}
	
	
	/** 
	 * Validates and adds a new interval
	 * 
	 * @param startPrice				Interval begin
	 * @param endPrice					Interval end
	 * @param fixedPrice				Fixed part of pricing
	 * @param variablePricePercent		Percentage of pricing
	 * @return							true if the interval was added to the list
	 */
	public boolean createPriceStep(double startPrice, double endPrice, double fixedPrice, double variablePricePercent) {
		
		if(endPrice==0)
			endPrice=Double.POSITIVE_INFINITY;
		
		// validate values
		if((startPrice<0)||(endPrice<0)||(fixedPrice<0)||(variablePricePercent<0)||(startPrice>endPrice))
			return false;
		
		PriceStep s = new PriceStep(startPrice, endPrice, fixedPrice, variablePricePercent);
		
		// empty list / greater than last element
		if((steps.isEmpty()) || (steps.get(steps.size()-1).getEndPrice()<startPrice)) {
			steps.add(s);
			return true;
		} 
		// new first element
		else if(steps.get(0).getStartPrice()<startPrice) {
			steps.add(0, s);
			return true;
		} 
		// between existing elements
		else {		
			for(int i=1; i<=steps.size(); i++) {
				if((steps.get(i-1).getEndPrice() < startPrice) && (steps.get(i).getStartPrice() > endPrice)) {
					steps.add(i, s);
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Removes an existing interval from the list
	 * 
	 * @param startPrice		Interval begin
	 * @param endPrice			Interval end
	 * @return					true if the interval was removed from the list
	 */
	public boolean deletePriceStep(double startPrice, double endPrice)  {
		if(steps.isEmpty())
			return false;
		
		Iterator<PriceStep> it = steps.iterator();
		PriceStep s, remove;
		remove = new PriceStep(startPrice, endPrice, 1, 1);
		
		while(it.hasNext()) {
			s = it.next();
			if(s.equalInterval(remove)) {
				it.remove();
				return true;
			}
		}
	
		return false;
	}
	
	
	public PriceStep getPriceStepForPrice(double price) {
		Iterator<PriceStep> it = steps.iterator();
		PriceStep s;
		
		while(it.hasNext()) {
			s=it.next();
			if(s.inInterval(price))
				return s;
		}
		
		return null;
	}
	
//	/* List-representation of all available Price-Steps
//	 * @see java.lang.Object#toString()
//	 */
//	public String toString() {
//		String out = "Min_Price	   Max_Price	   Fee_Fixed	   Fee_Variable \n";
//		Iterator<PriceStep> it = steps.iterator();
//		PriceStep s;
//		
//		while(it.hasNext()) {
//			s=it.next();
//			out += s.getStartPrice() + "       " + s.getEndPrice() + "       " 
//				+ s.getFixedPrice() + "       " + s.getVariablePricePercent() + "\n";
//		}
//		
//		return out;
//	}
	
	public synchronized PriceSteps getPriceSteps(){
		return new PriceSteps(steps);
	}
}
