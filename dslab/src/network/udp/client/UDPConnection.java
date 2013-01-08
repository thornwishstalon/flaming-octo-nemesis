package network.udp.client;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

import network.security.StaticStream;

import server.logic.IUserRelated;

import client.ClientMain;
import client.ClientSetup;
import client.ClientStatus;
import client.command.response.ResponseList;

import command.CommandParser;


public class UDPConnection extends Thread implements IUserRelated{
	//private DatagramSocket socket=null;
	private DatagramPacket packet=null;
	private CommandParser parser;
	private UDPSocket home;

	public UDPConnection(DatagramSocket socket, DatagramPacket packet, ClientSetup setup){
		//this.socket=socket;
		this.packet= packet;
		parser= new CommandParser(true,this);
		parser.setCommandList(new ResponseList(setup));
	}

	public UDPConnection(UDPSocket udpSocket, DatagramSocket socket,
			DatagramPacket packet2, ClientSetup setup) {
		
		home=udpSocket;
		this.packet= packet2;
		parser= new CommandParser(true,this);
		parser.setCommandList(new ResponseList(setup));
	}

	@Override
	public void run() {
		String message=new String(packet.getData(),0,packet.getLength());
		
		// Decode notifications
		//message=StaticStream.getStaticStreamInstance().useDecoder(message);
		
		String answer= parser.parse(message);
		if(answer.contains("!kill")){
			//home.close();
		}
			
		System.out.println(ClientStatus.getInstance().getUser() + "> "+answer);
		//System.out.println("back: "+message);
		return;
	}

	public synchronized void close(){
		//socket.close();
	}

	@Override
	public String getUser() {
		return ClientStatus.getInstance().getUser();
	}

	@Override
	public void setUser(String user) {
		ClientStatus.getInstance().setUser(user);
		
	}



}
