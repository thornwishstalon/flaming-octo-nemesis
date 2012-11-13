package managementClient;

/**
 * holding managamentClient related setup informations
 * 
 *
 */
public class ManagementClientSetup {
	private String analyticsBindingName, billingBindingName;
	
	
	public ManagementClientSetup(String[] args){
		analyticsBindingName=args[0];
		billingBindingName=args[1];
	}

	/*
	 * GETTER
	 */

	public String getAnalyticsBindingName() {
		return analyticsBindingName;
	}
	
	public String getBillingBindingName() {
		return billingBindingName;
	}


	/*
	 * SETTER
	 */
	public void setAnalyticsBindingName(String analyticsBindingName) {
		this.analyticsBindingName = analyticsBindingName;
	}



	public void setBillingBindingName(String billingBindingName) {
		this.billingBindingName = billingBindingName;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	
	public String toString(){
		return "AnalyticServer: " + analyticsBindingName+"\nBillingServer: "+ billingBindingName;
	}
	
	

}
