package network.tcp.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;

import network.security.StaticStream;

import client.ClientSetup;
import client.ClientStatus;
import client.command.response.ResponseList;

import command.CommandParser;

import server.logic.IUserRelated;

public class TCPInputConnection extends Thread implements IUserRelated {
	private Socket socket=null;
	private PrintWriter writer=null;
	private BufferedReader reader=null;

	private CommandParser parser=null;
	private ClientSetup setup;

	public TCPInputConnection(Socket socket, ClientSetup setup) {
		this.socket=socket;
		this.setup= setup;
		parser= new CommandParser(true, this);
		parser.setCommandList(new ResponseList(setup) );	
	}

	public void run(){
		try {
			reader= new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String input,answer;

			while((input= reader.readLine())!=null){

				// Use current decoding (Plain, RSA, AES ...) on input-stream
				input=StaticStream.getStaticStreamInstance().useDecoder(input);		
				answer=parser.parse(parser.parse(input));

				if(answer.length()>0)
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
		return ClientStatus.getInstance().getUser();
	}

	@Override
	public void setUser(String user) {
		// TODO Auto-generated method stub

	}

	public synchronized void close() {

		/*
		try {
			System.in.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	*/
		if(writer!=null){
			writer.close();
		}

		if(reader!=null){
			try {
				System.out.println("closing input-reader");
				reader.close();
				System.out.println("input-reader closed");
			} catch (IOException e) {
				System.err.println("io");
				e.printStackTrace();
			}

		}
		socket=null;
		System.out.println("tcp input Connection closed");
	}

}
