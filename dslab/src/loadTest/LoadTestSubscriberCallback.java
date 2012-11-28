package loadTest;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import network.rmi.SubscriberCallback;

import analyticsServer.event.Event;

public class LoadTestSubscriberCallback extends UnicastRemoteObject implements SubscriberCallback  {

	protected LoadTestSubscriberCallback() throws RemoteException {
		super();
	}


	/**
	 * 
	 */
	private static final long serialVersionUID = 4866875271279781490L;
	
	
	@Override
	public void notify(Event event) throws RemoteException {
		System.out.println(event.toString());
		
	}


	@Override
	public void terminate() throws RemoteException {
		this.unexportObject(this, true);
		try {
			this.finalize();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
