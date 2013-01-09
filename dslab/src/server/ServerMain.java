package server;

import java.io.IOException;

import server.logic.AuctionDATABASE;
import server.logic.UserDATABASE;

import network.tcp.server.TCPServerSocket;

public class ServerMain {

	private static ServerSetup setup;
	private static TCPServerSocket server;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		try{
		System.out.println("Welcome!\nstarting Server");
		setup= new ServerSetup(args);
		System.out.println(setup.toString());
		
		//initialize RMI stuff
		ServerStatus.getInstance().init(setup);
		
		//start TCP connection
		startServerSocket();
		
		// start UDP
		//System.out.println("UDP ready!");
		
		System.out.println("ready for input!");
		ServerInput input= new ServerInput();
		input.run();
		
		
		
		server.shutdown();
		
		System.out.println("ending auctions");
		AuctionDATABASE.getInstance().killAuctions();
		
		UserDATABASE.getInstance().killUsers();
		
		System.out.println("log out from billing server");
		ServerStatus.getInstance().logout();
		
		
		
		}catch(IOException e){
			
		}
		finally{
			System.out.println("\ngood-bye\n");
		}
		
	}

	public static ServerSetup getSetup() {
		return setup;
	}

	public static TCPServerSocket getServer() {
		return server;
	}
	
	public static void setServer(TCPServerSocket s) {
		server=s;
	}
	
	public static void startServerSocket() throws IOException {
		server= new TCPServerSocket(setup.getPort());
		server.init();
		server.start();
		System.out.println("TCP ready!");
	}
	
}
