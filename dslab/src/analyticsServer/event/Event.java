package analyticsServer.event;

import java.io.Serializable;

public abstract class Event implements Serializable{
	protected String ID;
	protected String type;
	protected long timestamp;
	
	
	protected Event(String ID, String type, long timestamp){
		this.ID=ID;
		this.type=type;
		this.timestamp=timestamp;
	}

	public String getID() {
		return ID;
	}

	public String getType() {
		return type;
	}

	public long getTimestamp() {
		return timestamp;
	}
	
	
	
	
	
}
