package server;

public class ServerSetup {
	private int port;
	
	public ServerSetup(String[] args){
		port= Integer.valueOf(args[0]);
	}
	
	public int getPort() {
		return port;
	}
	
	public String toString(){
		return "setup: \nport: "+port;
	}
}
