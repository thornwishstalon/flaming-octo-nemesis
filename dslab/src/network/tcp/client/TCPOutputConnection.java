package network.tcp.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;

import command.CommandException;
import command.CommandParser;

import client.ClientSetup;
import client.ClientStatus;
import client.command.ClientLocalCommandList;

import server.logic.IUserRelated;
import server.logic.User;

public class TCPOutputConnection extends Thread implements IUserRelated{
	private Socket socket=null;
	private PrintWriter writer=null;
	private BufferedReader reader=null;
	private User user=null;
	private CommandParser parser=null;


	public TCPOutputConnection(Socket socket, ClientSetup setup){
		this.socket = socket;
		parser= new CommandParser(false,this);
		parser.setCommandList(new ClientLocalCommandList(setup.getClientPort()));
	}

	public void run(){
		//long start=System.currentTimeMillis();

		try {
			writer = new PrintWriter(socket.getOutputStream(), true);

			reader = new BufferedReader(new InputStreamReader(System.in));	
			String input;	

			/*
			//registering
			writer.println(parser.parse("!register"));
			System.out.println("waiting for registration");
			boolean ack=ClientStatus.getInstance().isAck();
			while(!ack){

				//System.out.println(ack);
				if(System.currentTimeMillis()-start > 5000){// timeout
					throw new CommandException("registration failed! Timeout!");
				}
				ack= ClientStatus.getInstance().isAck();

			}
			 */


			System.out.println("READY for Input!");

			while((input = reader.readLine()) != null) {

				if(input.equals("!end")) {					
					if(!ClientStatus.getInstance().getUser().equals("")){
						writer.println("!logout");
						ClientStatus.getInstance().setKill(true);
					}
					break;
				}
				else{
					if(input.length()>0){
						String query=parser.parse(input.trim());
						if(query.length()>1 && !ClientStatus.getInstance().isBlocked()){
							//TODO HMAC data structure add input line...
							writer.println(query);
						}
					}
					else System.out.println("");

				}

			}
		}catch(SocketException e){
			System.out.println("socket gone");
			return;
		}
		catch (IOException e) {
			System.out.println("IO:....");
			return;
		}
		/*catch (CommandException e) {
			System.out.println(e.getMessage());
			return;
		}
		 */
		finally{
			if(!socket.isClosed())
				close();
		}
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

		System.out.println("tcp output Connection closed");
	}

	@Override
	public String getUser() {
		if(user!=null)
			return user.getName();
		else return "";
	}

	@Override
	public void setUser(String user) {
		//this.user.
	}







}
