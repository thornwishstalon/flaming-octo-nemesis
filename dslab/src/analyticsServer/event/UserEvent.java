package analyticsServer.event;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UserEvent extends Event {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1628673395613978433L;
	//user_event
	public static final String USER_LOGIN="USER_LOGIN";
	public static final String USER_LOGOUT="USER_LOGOUT";
	public static final String USER_DISCONNECTED="USER_DISCONNECTED";

	private String userName;


	protected UserEvent(String ID, String type, long timestamp, String username) {
		super(ID, type, timestamp);
		this.userName=username;
	}

	public String getUsername(){
		return userName;
	}


	public String toString() {
		SimpleDateFormat df= new SimpleDateFormat("dd.MM.yyyy kk:mm:ss z");
		String s=type+": "+ df.format(new Date(timestamp))+" - ";
		if(type.equals(USER_LOGIN))
			s+= "user "+userName+" logged in.";
		else if(type.equals(USER_LOGOUT)) s+= "user "+userName+" logged out.";
		else s+= "user "+userName+" disconnected.";

		return s;
	}

}
