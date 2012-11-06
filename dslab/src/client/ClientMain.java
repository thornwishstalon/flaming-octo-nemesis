package client;

import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

import network.tcp.client.TCPConnection;
import network.udp.client.UDPSocket;

public class ClientMain {

	/**
	 * @param args
	 */
	/*
	private static boolean ack= false;
	private static String user="";
	private static boolean kill= false;
	
	*/
	private static Socket socket = null;
	private static UDPSocket udp=null;
	private static TCPConnection out=null;
	
	public static void main(String[] args) {
		System.out.println("Welcome");
		
		ClientSetup setup= new ClientSetup(args);
		System.out.println("setup: "+setup.toString());
		
		
		try {
			socket = new Socket(setup.getHost(), setup.getServerPort());
			//ClientStatus.getInstance().setSocket(socket);
			//start TCP connection
			//in= new TCPInputConnection(socket);
			//in.start();
			
			
			//start UDP
			udp = new UDPSocket(setup);
			udp.start();
			System.out.println("UDP ready");
			
			//start blocking input
			System.out.println("TCP ready");
			out= new TCPConnection(socket,setup);
			out.run();
			
			//while(!ClientStatus.getInstance().isKill()){
				
			//}
			
			System.out.println("client done");
			
		} catch (UnknownHostException e) {
			System.out.println("ERROR: unknown hostname!");
		} catch (ConnectException e) {
			System.out.println("Error: Server unreachable!");
		} catch (IOException e) {
			System.out.println("Error:....!");
		}
		finally{
			kill();
		}
		
		

	}
	
	/*
	public static boolean isAck(){
		return ClientMain.ack;
	}
	
	public static String getUser(){
		return ClientMain.user;
	}
	
	public static boolean isKill(){
		return ClientMain.kill;
	}
	
	public static void setAck(boolean ack){
		System.out.println("ack changed");
		ClientMain.ack=ack;
		System.out.println(ClientMain.ack+" | "+ack);
	}
	
	public static void setUser(String user){
		ClientMain.user=user;
	}
	*/
	
	public static synchronized void kill(){
		try {
			System.out.println("closing connections");
			if(socket!=null)
				socket.close();
			
			if(out!=null)
				out.close();

			if(socket!=null)
				socket.close();
			if(udp!=null)
				udp.close();
			
			System.out.println("connections successfully closed!\n\nHave a nice day!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
		
	

}
