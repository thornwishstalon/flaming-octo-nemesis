package network.tcp.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;

import client.ClientStatus;
import client.command.response.ResponseList;

import command.CommandParser;

import server.logic.IUserRelated;

public class TCPInputConnection extends Thread implements IUserRelated {
	private Socket socket=null;
	private PrintWriter writer=null;
	private BufferedReader reader=null;
	
	private CommandParser parser=null;
	
	public TCPInputConnection(Socket socket) {
		this.socket=socket;
		parser= new CommandParser(true, this);
		parser.setCommandList(new ResponseList() );	
	}
	
	public void run(){
		try {
			reader= new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String input,answer;
			
			while((input= reader.readLine())!=null){
				answer=parser.parse(input);
				System.out.println(ClientStatus.getInstance().getUser() + "> "+answer);
			}
			
		}catch(SocketException e){
			
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} 
		finally{
			if(socket!=null)
				close();
		}
		
		
	}
	
	@Override
	public String getUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setUser(String user) {
		// TODO Auto-generated method stub

	}
	
	public synchronized void close() {
		//System.out.println("tcpConnection close");
		try {
			System.in.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if(writer!=null){
			writer.close();
		}
		
		if(reader!=null){
			try {
				System.out.println("closing reader");
				reader.close();
				System.out.println("reader closed");
			} catch (IOException e) {
				System.err.println("io");
				e.printStackTrace();
			}
			
		}
		socket=null;
		System.out.println("tcp input Connection closed");
	}

}
