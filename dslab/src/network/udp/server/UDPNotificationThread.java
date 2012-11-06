package network.udp.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;

import server.logic.User;
import server.logic.UserNotification;

public class UDPNotificationThread extends Thread{
	private ArrayList<UserNotification> notifications;
	private User user;
	private InetAddress address;
	private int port=0;
	private boolean note=false;
	private String message=null;
	private int delay=0;

	public UDPNotificationThread(User user){
		this.notifications= user.getNotifications();
		this.user=user;

		this.address=user.getAddress();
		this.port=user.getPort();
		note=true;
	}

	public UDPNotificationThread(InetAddress addreass, int port, String message){
		//this.notifications= user.getNotifications();
		this.user=null;
		this.address=addreass;
		this.port=port;
		this.message=message;
	}




	public UDPNotificationThread(User user, int delay) {
		this.notifications= user.getNotifications();
		this.user=user;

		this.address=user.getAddress();
		this.port=user.getPort();
		note=true;
		this.delay=delay;
	}

	@Override
	public void run() {
		try {
			sleep(delay);
			DatagramSocket socket = new DatagramSocket();

			if(note){
				for(UserNotification note: notifications){
					byte[]data= note.getMessage().getBytes();

					DatagramPacket packet= new DatagramPacket(data, data.length,address, port);

					socket.send(packet);
					note.setSent(true);
				}
			}else{
				byte[]data= message.getBytes();
				DatagramPacket packet= new DatagramPacket(data, data.length,address, port);
				socket.send(packet);
			}
			
			socket.close();
			//System.out.println("ending udp connection");
			
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			//System.out.println("finally ending udp connection");
			
		}
	}

}
