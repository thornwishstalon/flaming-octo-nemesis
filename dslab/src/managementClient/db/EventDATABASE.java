package managementClient.db;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import network.rmi.SubscriberCallback;

import analyticsServer.event.Event;

/**
 * Singleton DATABASE used to deal with events coming from subscription-callback.
 * If 'isAuto' is true the events toString()-method is called and printed directly to the commandLine.
 * Otherwise the event is stored in an arraylist until a '!print'-command occurs.
 * 
 *
 */
public class EventDATABASE {
	private static EventDATABASE instance=null;
	private boolean isAuto=true;
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
	
	/**
	 * gets called by the subscriberCallback implementation @see {@link managementClient.db.ManagementClientSubscriberCallback}
	 * @param event 		the event the subscriber is getting notified about
	 */
	public synchronized void notify(Event event){
		
			if(isAuto){
				System.out.println(event.toString());
			}
			else{
				eventBuffer.add(event);
			}
	}
	
	/**
	 * returns the content of the evenBuffer in a formatted and readable String representation
	 * (for each :event.toString()+"\n" )
	 * @return 
	 */
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

	/**
	 * sets the auto-mode on/off
	 * @param isAuto
	 */
	public void setAuto(boolean isAuto) {
		this.isAuto = isAuto;
	}
	/**
	 * returns the callback associated with this instance
	 * @return
	 */
	
	public SubscriberCallback getCallback(){
		return callback;
	}

	/**
	 * terminates the callback remote object
	 */
	public void killCallback() {
		try {
			UnicastRemoteObject.unexportObject(callback, true);
		} catch (RemoteException e) {
			//nothing to do
			//e.printStackTrace();
		}
		
	}
	
	
	
	
	
	
	
}
