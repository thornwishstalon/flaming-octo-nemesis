package client;


public class ClientStatus {
	private static ClientStatus instance=null;
	
	private  boolean ack= false;
	private  String user="";
	private  boolean kill= false;
	private boolean blocked=false;
	//private Socket socket=null;
	
	
	public synchronized static ClientStatus getInstance(){
		if(instance==null)
			instance= new ClientStatus();
		return instance;
	}
	
	
	public synchronized  boolean isAck(){
		return ack;
	}
	
	public  synchronized String getUser(){
		return this.user;
	}
	
	public synchronized boolean isKill(){
		return this.kill;
	}
	
	public synchronized void setAck(boolean ack){
		//System.out.println("ack changed");
		this.ack=ack;
		//System.out.println(this.ack+" | "+ack);
	}
	
	public synchronized void setUser(String user){
		this.user=user;
	}

	
	public void killClient(){
		kill= true;
		//ClientMain.kill();
	}


	public void setKill(boolean b) {
		kill=b;
		
	}


	public boolean isBlocked() {
		return blocked;
	}


	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}
	
	
	
}
