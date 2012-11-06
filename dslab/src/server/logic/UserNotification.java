package server.logic;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class UserNotification implements Comparable<UserNotification> {
	private int ID;
	private String message;
	private Timestamp timestamp;
	private boolean sent;
	
	public UserNotification(int id, String message){
		this.ID= id;
		this.message=message;
		timestamp= new Timestamp(System.currentTimeMillis());
	}

	public String getMessage() {
		return message;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	@Override
	public int compareTo(UserNotification o) {
		if(o.getTimestamp().after(this.timestamp))
			return 1;
		else if(o.getTimestamp().before(this.timestamp))
			return -1;
		else return 0;
	}
	
	public void setSent(boolean value){
		this.sent=value;
	}
	
	public boolean isSent() {
		return sent;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}
	
	public String toString(){
		SimpleDateFormat df= new SimpleDateFormat("dd.MM.yyyy kk:mm:ss z");
		return ID+" "+df.format(timestamp)+": "+message+"    ..sent: "+sent;
	}
	
	
	
	
	
}
