package network.rmi;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIRegistry {

	
	private static boolean create=true;
	private static Registry registry;
	
	public static Registry getRegistry(int port) {
		
		if(create) {
			create=false;
			try {
				registry = LocateRegistry.createRegistry(port);
			} catch (RemoteException e) {
				System.out.println("Registry could not be created.");
				e.printStackTrace();
			}
		} 
		
		return registry;
		
	}
	
}
