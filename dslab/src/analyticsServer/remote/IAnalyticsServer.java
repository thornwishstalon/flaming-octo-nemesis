package analyticsServer.remote;

import java.rmi.Remote;
import analyticsServer.event.Event;

public interface IAnalyticsServer  extends Remote  {
	public void subscribe(String regEx);
	public void processEvent(Event event);
	public void unsubscribe(String regEx);
}
