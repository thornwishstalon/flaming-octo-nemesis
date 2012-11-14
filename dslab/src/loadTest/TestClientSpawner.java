package loadTest;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestClientSpawner extends Thread{
	private ExecutorService executor;
	private LoadTestSetup setup;
	private ArrayList<TestClient> clients;
	
	public TestClientSpawner(LoadTestSetup setup) {
		this.setup=setup;
		executor= Executors.newFixedThreadPool(setup.getClients());
		
	}
	
	public void run(){
		//start n client tasks
		
		
	}
	
	public void kill(){
		executor.shutdown();
		for(TestClient client: clients){
			client.shutdown();
		}
		
		//kill clients!
	}

}
