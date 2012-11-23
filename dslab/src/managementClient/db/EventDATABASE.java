package managementClient.db;

import java.rmi.RemoteException;
import java.util.ArrayList;

import network.rmi.SubscriberCallback;

import analyticsServer.event.Event;

public class EventDATABASE {
	private static EventDATABASE instance=null;
	private boolean isAuto=false;
	private SubscriberCallback callback;
	
	
	private ArrayList<Event> eventBuffer;
	
	private EventDATABASE() {
		eventBuffer=new ArrayList<Event>();

		//initialise subscriptions-callback
		try {
			callback= new ManagementClientSubscriberCallback();
		} catch (RemoteException e) {
			//e.printStackTrace();
		}
		
	}
	
	public static EventDATABASE getInstance(){
		if(instance==null)
			instance= new EventDATABASE();
		return instance;
	}
	
	
	public synchronized void notify(Event event){
		if(isAuto){
			System.out.println(event.toString());
		}
		else{
			eventBuffer.add(event);
		}
	}
	
	public synchronized String printBuffer(){
		
		//TODO order eventBuffer by event.timestamp
		String list="\n";
		for(Event e: eventBuffer){
			list+=e.toString()+"\n";
		}
		eventBuffer.clear();
		
		return list;
	}

	public boolean isAuto() {
		return isAuto;
	}

	public void setAuto(boolean isAuto) {
		this.isAuto = isAuto;
	}
	
	public SubscriberCallback getCallback(){
		return callback;
	}
	
	
	
	
	
	
	
}
