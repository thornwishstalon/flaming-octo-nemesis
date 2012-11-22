package network.rmi;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.ExportException;

public class RMIRegistry {

	
	private static boolean create=true;
	private static Registry registry;
	
	public static Registry getRegistry(int port) {
		
		if(create) {
			create=false;
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
	
}
