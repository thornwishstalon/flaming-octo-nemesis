package managementClient;

public class ManagementClientSetup {
	private String analyticsBindingName, billingBindingName;
	
	
	public ManagementClientSetup(String[] args){
		analyticsBindingName=args[0];
		billingBindingName=args[1];
	}


	public String getAnalyticsBindingName() {
		return analyticsBindingName;
	}


	public void setAnalyticsBindingName(String analyticsBindingName) {
		this.analyticsBindingName = analyticsBindingName;
	}


	public String getBillingBindingName() {
		return billingBindingName;
	}


	public void setBillingBindingName(String billingBindingName) {
		this.billingBindingName = billingBindingName;
	}
	
	public String toString(){
		return "AnalyticServer: " + analyticsBindingName+"\nBillingServer: "+ billingBindingName;
	}
	
	

}
