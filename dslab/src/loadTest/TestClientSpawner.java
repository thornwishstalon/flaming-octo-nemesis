package loadTest;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 
 * Responsible for starting and stopping the testClient-army
 */
public class TestClientSpawner extends Thread{
	private ExecutorService executor;
	private LoadTestSetup setup;
	private ArrayList<TestClient> clients;
	
	public TestClientSpawner(LoadTestSetup setup) {
		this.setup=setup;
		clients= new ArrayList<TestClient>();
		executor= Executors.newFixedThreadPool(setup.getClients());
		
	}
	
	public void run(){
		TestClient tmp=null;
		Random r= new Random();
		int delay;
		
		//for n clients
		for(int i=0;i<setup.getClients();i++){
			//generate a random delay
			delay=r.nextInt(50)*100; 
			
			//create a new TestClient
			tmp= new TestClient(setup, i,delay);
			
			//add TestClient to the spawned-list
			clients.add(tmp);
			
			//start the testClient-thread using an executor-service
			executor.execute(tmp);
			
		}
		
		while(!executor.isShutdown()){
			//waiting
		}
		
	}
	
	/**
	 * shut down the loadTest
	 */
	public void kill(){
		//stop the threadPool from startin any new threads
		executor.shutdown();
		
		//kill all testClients in the spawned-list
		for(TestClient client: clients){
			client.shutdown();
		}
		
		System.out.println("clients logged out and killed!");
	}

}
