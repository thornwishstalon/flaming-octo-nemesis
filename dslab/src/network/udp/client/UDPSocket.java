package network.udp.client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;

import client.ClientMain;
import client.ClientSetup;
import client.ClientStatus;

public class UDPSocket extends Thread{
	private static final int NTHREDS = 5;
	private int port;
	private boolean listening=true;
	private DatagramSocket socket=null;
	private ExecutorService executor=null;
	private ClientSetup setup;


	public UDPSocket(ClientSetup setup) {
		this.port= setup.getClientPort();
		this.setup=setup;
		try {
			this.socket= new DatagramSocket(port);
			executor= Executors.newFixedThreadPool(NTHREDS);
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {

		try {
			while((listening)){
				if(ClientStatus.getInstance().isKill()){
					System.out.println("udp-kill");
					socket.close();
				}

				byte[] data= new byte[1024];
				DatagramPacket packet= new DatagramPacket(data, data.length);
				socket.receive(packet);
				executor.execute(new UDPConnection(this,socket, packet, setup));
 
			}
		}catch(RejectedExecutionException e){

		}
		catch (IOException e) {
			//e.printStackTrace();
		}
		//catch(SocketException e){
		//e.printStackTrace();
		//}
		finally{
			close();
		}
	}

	public synchronized void close(){
		listening=false;

		executor.shutdown();
		try {
			executor.awaitTermination(1, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		/*
		while(!executor.isTerminated()){
			//wait for termination
			//System.out.println("waiting for termination");
		}
		*/
		socket.close();
		//System.out.println("Terminated!");

	}

}
