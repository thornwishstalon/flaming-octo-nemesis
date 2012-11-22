package network.rmi;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.ExportException;

public class RMIRegistry {

	
	private static boolean create=true;
	private static Registry registry;
	
	public static Registry getRegistry() {
		
		if(create) {
			create=false;
			int port= Integer.parseInt(getRegistryProperty("registry.port"));
			try {
				registry = LocateRegistry.createRegistry(port);
			}catch(ExportException e){
				try {
					registry= LocateRegistry.getRegistry(port);
				} catch (RemoteException e1) {
					System.out.println("Registry could not be found.");
					e1.printStackTrace();
				}
			}
			catch (RemoteException e) {
				System.out.println("Registry could not be created.");
				e.printStackTrace();
			}
		} 
		
		return registry;
		
	}
	
	private static String getRegistryProperty(String propName) {
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
