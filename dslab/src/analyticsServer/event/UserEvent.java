package analyticsServer.event;

public class UserEvent extends Event {
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

}
