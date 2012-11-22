package analyticsServer;

import java.io.IOException;

public class AnalyticsServerSetup {
	private String bindingName;

	public AnalyticsServerSetup(String[] args) {
		this.bindingName=args[0];
	}

	public String getBindingName() {
		return bindingName;
	}

	public void setBindingName(String bindingName) {
		this.bindingName = bindingName;
	}

	public String toString(){
		return "AnalyticsServer SETUP:\nBinding-name: "+bindingName;
	}


	public String getRegistryProperty(String propName) {
		java.io.InputStream is = ClassLoader.getSystemResourceAsStream("registry.properties");
		String prop="";

		if (is != null) {
			java.util.Properties props = new java.util.Properties();
			try {
				props.load(is);
				prop = props.getProperty(propName);
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} else {
			System.err.println("Properties file not found!");
		}

		return prop;
	}

}
