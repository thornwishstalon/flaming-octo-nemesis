package billingServer;

public class BillingServerSetup {
	private String bindingName;
	
	public BillingServerSetup(String[] args) {
		this.bindingName=args[0];
	}
	
	public String getBindingName() {
		return bindingName;
	}
	
	public void setBindingName(String bindingName) {
		this.bindingName = bindingName;
	}
}
