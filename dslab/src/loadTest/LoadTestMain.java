package loadTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LoadTestMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("STARTING LOADTEST:\n____________#_#");

		LoadTestSetup setup= new LoadTestSetup(args);
		System.out.println(setup.toString());

		//blocking input
		BufferedReader reader= new BufferedReader(new InputStreamReader(System.in));

		//spawn loadTest clients...
		TestClientSpawner spawner= new TestClientSpawner(setup);

		System.out.println("starting client spawner");
		SimpleDateFormat df= new SimpleDateFormat("dd.MM.yyyy kk:mm z");
		
		System.out.println("\nstarted at: "+df.format(new Date(System.currentTimeMillis())));
		
		spawner.start();

		try {
			String input="";
			while((input = reader.readLine())!=null){
				if(input.equals("!end")){
					System.out.println(input+"ENDING LOADTEST");
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			System.out.println("ending spawners");
			spawner.kill();
		}

	}

}
