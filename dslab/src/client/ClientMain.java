package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

import network.security.SimpleStringStream;
import network.security.StaticStream;
import network.tcp.client.TCPInputConnection;
import network.tcp.client.TCPOutputConnection;
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
	private static TCPOutputConnection out=null;
	private static TCPInputConnection in=null;
	private static ClientSetup setup = null;
	private static boolean disconnected=false;

	public static void main(String[] args) throws InterruptedException {
		System.out.println("Welcome");

		setup= new ClientSetup(args);
		System.out.println("setup: "+setup.toString());

		int ii=0;
		while(!ClientStatus.getInstance().isKill()) {
			try {
				socket = new Socket(setup.getHost(), setup.getServerPort());

				if(!disconnected) {
					startOutputConn();
				}	
				else {
					out.resetSocket(socket);
				}

				startInputConn();

				System.out.println("client done");
			} catch (UnknownHostException e) {
				System.out.println("ERROR: unknown hostname!");
			} catch (ConnectException e) {
				disconnected();
				System.out.println("Error: Server unreachable!");
			} catch (IOException e) {
				System.out.println("Error:....!");
			}
			finally{
				if(ClientStatus.getInstance().isKill())
					kill();
				else {
					System.out.println("Reconnection attempt # " + (++ii));
					Thread.sleep(2000);
				}
			}	
		}
	}


	private static void startOutputConn() {
		out= new TCPOutputConnection(socket,setup);
		ClientStatus.getInstance().setConnection(out);
		out.start();
	}

	private static void startInputConn() {
		in= new TCPInputConnection(socket,setup);
		System.out.println("TCP ready");
		in.run();
	}


	private static void disconnected() {
		StaticStream.getStaticStreamInstance().setDecoderStream(new SimpleStringStream());
		StaticStream.getStaticStreamInstance().setEncoderStream(new SimpleStringStream());
		disconnected=true;
	}

	public static void printToOutputstream(String query) {
		out.printToOutputstream(query);
	}


	public static synchronized void kill(){
		try {
			System.out.println("closing connections");

			ClientStatus.getInstance().end();
			
			if(in!=null)
				in.close();

			if(out!=null)
				out.close();

			if(socket!=null)
				socket.close();

			

			System.out.println("connections successfully closed!\n\nHave a nice day!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	public static ClientSetup getClientSetup() {
		return setup;
	}

	public static boolean isDisconnected() {
		return disconnected;
	}

	public static void setDisconnected(boolean disconnected) {
		ClientMain.disconnected = disconnected;
	}

}
