package server;

import java.io.IOException;

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
		
		}catch(IOException e){
			
		}
		finally{
			System.out.println("\ngood-bye\n");
		}
		
	}

}
