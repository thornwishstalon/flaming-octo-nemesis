/*
 * BillingServerPriceStepsDATABASE
 * -> renamed to PriceStep
 */

package billingServer.db.content;

import java.io.Serializable;

public class PriceStep implements Serializable, Comparable<PriceStep>{
	private static final long serialVersionUID = -2695912936694793137L;
	private double startPrice, endPrice, fixedPrice, variablePricePercent;
	
	public PriceStep(double startPrice, double endPrice, double fixedPrice, double variablePricePercent){
		this.startPrice = startPrice;
		this.endPrice = endPrice;
		this.fixedPrice = fixedPrice;
		this.variablePricePercent = variablePricePercent;
	}


	public boolean equalInterval(PriceStep o) {
		if((this.startPrice == o.getStartPrice()) && (this.endPrice == o.getEndPrice())) {
			return true;
		}
		return false;
	}
	
	public boolean inInterval(double price) {
		if((startPrice<price) && (endPrice>price)) 
			return true;
		
		return false;
	}
	

	@Override
	public int compareTo(PriceStep o) {
		if(this.startPrice == o.getStartPrice())
			return 0;
		else if(this.startPrice < o.getStartPrice())
			return 1;
		else return -1;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	/*
	 * Getter / Setter
	 */
	public double getStartPrice() {
		return startPrice;
	}

	public void setStartPrice(double startPrice) {
		this.startPrice = startPrice;
	}

	public double getEndPrice() {
		return endPrice;
	}

	public void setEndPrice(double endPrice) {
		this.endPrice = endPrice;
	}

	public double getFixedPrice() {
		return fixedPrice;
	}

	public void setFixedPrice(double fixedPrice) {
		this.fixedPrice = fixedPrice;
	}

	public double getVariablePricePercent() {
		return variablePricePercent;
	}

	public void setVariablePricePercent(double variablePricePercent) {
		this.variablePricePercent = variablePricePercent;
	}	
	
}
