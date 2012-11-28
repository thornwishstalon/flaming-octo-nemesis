package loadTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.NoSuchObjectException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
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

		long start = System.currentTimeMillis(),end = 0;

		System.out.println(feat.feat[r.nextInt(3)]); // futurama feature


		//startComponents(); //experimental

		LoadTestSetup setup= new LoadTestSetup(args);
		System.out.println(setup.toString());

		//blocking input
		BufferedReader reader= new BufferedReader(new InputStreamReader(System.in));


		TestClientSpawner spawner= new TestClientSpawner(setup);

		System.out.println("starting client spawner");
		SimpleDateFormat df= new SimpleDateFormat("dd.MM.yyyy kk:mm z");

		System.out.println("\nstarted at: "+df.format(new Date(start)));

		//spawn loadTest-clients...
		spawner.start();


		AnalyticsServer analyticsServer=null;
		LoadTestSubscriberCallback callback=null;
		long subID = 0;

		try {
			Registry registry = LocateRegistry.getRegistry(11269);


			//connect to analyticsServer
			try {
				analyticsServer= (AnalyticsServer) registry.lookup(setup.getAnalyticsBindingName());
				callback = new LoadTestSubscriberCallback();
				subID=analyticsServer.subscribe(".*", callback);

			} catch (NotBoundException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				System.out.println("the analytics-server is unavailable, there will be no statistic- feature!");
			}

			System.out.println("###########################\nPRESS ENTER TO CANCEL LOADTEST!!!!!!!!!\n###########################");

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


			try {
				//terminate RMI connections
				if(callback!=null){
					analyticsServer.unsubscribe(subID);

					callback.terminate();
				}
			} catch (NoSuchObjectException e) {
				//nothing to do
				//e.printStackTrace();
			} catch (RemoteException e) {
				//nothing to do
				//e.printStackTrace();
			}finally{
				end= System.currentTimeMillis();

				System.out.println("LOADTEST_INFO\n______________");

				System.out.println(setup.toString());

				System.out.println("started at: "+df.format(new Date(start)));
				System.out.println("ended at: "+df.format(new Date(end)));

				//statistics
				long diff= (end-start)/1000;

				System.out.println("_______\ntotal runtime: "+timespan(diff));
			}
		}

	}

	private static String timespan(long diffInSeconds){
		long sec = (diffInSeconds >= 60 ? diffInSeconds % 60 : diffInSeconds);
		long min = (diffInSeconds = (diffInSeconds / 60)) >= 60 ? diffInSeconds % 60 : diffInSeconds;
		long hrs = (diffInSeconds = (diffInSeconds / 60)) >= 24 ? diffInSeconds % 24 : diffInSeconds;
		long days = (diffInSeconds = (diffInSeconds / 24)) >= 30 ? diffInSeconds % 30 : diffInSeconds;
		long months = (diffInSeconds = (diffInSeconds / 30)) >= 12 ? diffInSeconds % 12 : diffInSeconds;
		long years = (diffInSeconds = (diffInSeconds / 12));

		StringBuilder sb= new StringBuilder();

		if (years > 0) {
			if (years == 1) {
				sb.append("a year ");
			} else {
				sb.append(years + " years ");
			}
			if (years <= 6 && months > 0) {
				if (months == 1) {
					sb.append(" and a month ");
				} else {
					sb.append( months + " months ");
				}
			}
		} else if (months > 0) {
			if (months == 1) {
				sb.append("a month ");
			} else {
				sb.append(months + " months ");
			}
			if (months <= 6 && days > 0) {
				if (days == 1) {
					sb.append(" and a day");
				} else {
					sb.append( days + " days ");
				}
			}
		} else if (days > 0) {
			if (days == 1) {
				sb.append("a day ");
			} else {
				sb.append(days + " days ");
			}
			if (days <= 3 && hrs > 0) {
				if (hrs == 1) {
					sb.append(" and an hour ");
				} else {
					sb.append( hrs + " hours ");
				}
			}
		} else if (hrs > 0) {
			if (hrs == 1) {
				sb.append("an hour ");
			} else {
				sb.append(hrs + " hours ");
			}
			if (min > 1) {
				sb.append( min + " minutes ");
			}
		} else if (min > 0) {
			if (min == 1) {
				sb.append("a minute");
			} else {
				sb.append(min + " minutes ");
			}
			if (sec > 1) {
				sb.append( sec + " seconds");
			}
		} else {
			if (sec <= 1) {
				sb.append("about a second");
			} else {
				sb.append( sec + " seconds");
			}
		}

		return sb.toString();

	}

}
