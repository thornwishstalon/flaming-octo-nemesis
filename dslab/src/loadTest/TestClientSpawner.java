package loadTest;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
		//start n client tasks
		TestClient tmp=null;
		Random r= new Random();
		
		for(int i=0;i<setup.getClients();i++){
			tmp= new TestClient(setup, i,r.nextInt(2000));
			clients.add(tmp);
			
			executor.execute(tmp);
			
		}
		
		while(!executor.isShutdown()){
			//waiting
		}
		
	}
	
	public void kill(){
		executor.shutdown();
		for(TestClient client: clients){
			client.shutdown();
		}
		
		System.out.println("clients killed!");
	}

}
