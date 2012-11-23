package billingServer.db;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import billingServer.db.content.PriceStep;


public class PriceSteps implements Serializable {
	
	private static final long serialVersionUID = 4355105344497302603L;
	private List<PriceStep> steps;


	public PriceSteps(ArrayList<PriceStep> steps) {
		this.steps = steps;
	}

	
	/* List-representation of all available Price-Steps
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		
		String out = "Min_Price	   Max_Price	   Fee_Fixed	   Fee_Variable \n";
		
		synchronized (steps) {
			Iterator<PriceStep> it = steps.iterator();
			PriceStep s;

			while(it.hasNext()) {
				s=it.next();
				out += s.getStartPrice() + "       " + s.getEndPrice() + "       " 
						+ s.getFixedPrice() + "       " + s.getVariablePricePercent() + "\n";
			}
		}
		return out;
	}
}
