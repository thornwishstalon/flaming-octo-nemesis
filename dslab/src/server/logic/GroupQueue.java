package server.logic;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public class GroupQueue {
	private Timer timer;
	private HashMap<String, Integer> queue;
	private final int maxPerMin=1;
	
	public GroupQueue(){
		timer= new Timer();
		queue= new HashMap<String, Integer>();
		timer.schedule(new CleanUpTask(), 60000, 60000);
		
	}
	
	public synchronized boolean queue(String intitiator){
		System.out.println(intitiator+" enqueued");
		Integer x = queue.get(intitiator);
		
		if(x == null){
			queue.put(intitiator, 1);
			return true;
		}else if(x >= maxPerMin){
			return false;
		}else{
			queue.put(intitiator, ++x);
			return true;
		}
		

	}
	
	public synchronized void cancel(){
		timer.cancel();
		timer.purge();
	}
	
	
	
	private class CleanUpTask extends TimerTask{

		@Override
		public void run() {
			System.out.println("clean queue");
			queue.clear();
		}
		
	}
}
