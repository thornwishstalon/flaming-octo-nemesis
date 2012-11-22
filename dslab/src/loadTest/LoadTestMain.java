package loadTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.NotBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import analyticsServer.remote.AnalyticsServer;

/**
 * LoadTestMain. starts the loadTEst
 *
 */

public class LoadTestMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("STARTING LOADTEST:\n____________#_#");
		Random r= new Random();
		Feature feat= new Feature();
	
		
		System.out.println(feat.feat[r.nextInt(3)]); // futurama feature

		
		//startComponents(); //experimental
		
		LoadTestSetup setup= new LoadTestSetup(args);
		System.out.println(setup.toString());

		//blocking input
		BufferedReader reader= new BufferedReader(new InputStreamReader(System.in));

		
		TestClientSpawner spawner= new TestClientSpawner(setup);

		System.out.println("starting client spawner");
		SimpleDateFormat df= new SimpleDateFormat("dd.MM.yyyy kk:mm z");
		
		System.out.println("\nstarted at: "+df.format(new Date(System.currentTimeMillis())));

		//spawn loadTest-clients...
		spawner.start();
		
		
		
		
		
		try {
			Registry registry = LocateRegistry.getRegistry(11269);
			AnalyticsServer analyticsServer=null;
			
			//connect to analyticsServer
			try {
				analyticsServer= (AnalyticsServer) registry.lookup(setup.getAnalyticsBindingName());
			} catch (NotBoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			LoadTestSubscriberCallback callback = new LoadTestSubscriberCallback();
			analyticsServer.subscribe("*", callback);
			
			
			//listening to input
			String input="";
			while((input = reader.readLine())!=null){
				//if(input.equals("!end")){
					//System.out.println(input+"ENDING LOADTEST");
					break;
				//}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			System.out.println("ending spawners");
			spawner.kill();
		}

	}

}
