package server;

import java.io.IOException;

import server.logic.AuctionDATABASE;

import network.tcp.server.TCPServerSocket;

public class ServerMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TCPServerSocket server;
		try{
		System.out.println("Welcome!\nstarting Server");
		ServerSetup setup= new ServerSetup(args);
		System.out.println(setup.toString());
		
		//initialize RMI stuff
		ServerStatus.getInstance().init(setup);
		
		//start TCP connection
		server= new TCPServerSocket(setup.getPort());
		server.init();
		server.start();
		System.out.println("TCP ready!");
		
		// start UDP
		//System.out.println("UDP ready!");
		
		System.out.println("ready for input!");
		ServerInput input= new ServerInput();
		input.run();
		
		
		
		server.shutdown();
		
		ServerStatus.getInstance().logout();
		AuctionDATABASE.getInstance().killAuctions();
		
		}catch(IOException e){
			
		}
		finally{
			System.out.println("\ngood-bye\n");
		}
		
	}

}
