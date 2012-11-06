package client;

public class ClientSetup {
	private int clientPort;
	private int serverPort;
	private String host;
	
	public ClientSetup(String[] args){
		host=args[0];
		serverPort = Integer.valueOf(args[1]);
		clientPort = Integer.valueOf(args[2]);
	}

	public String toString(){
		return host+":"+serverPort+"\n client port: "+clientPort;
	}

	public String getHost() {
		return host;
	}
	
	public int getClientPort() {
		return clientPort;
	}
	
	public int getServerPort() {
		return serverPort;
	}
	
}
