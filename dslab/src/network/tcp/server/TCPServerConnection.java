package network.tcp.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;

import network.udp.server.UDPNotificationThread;

import client.command.ClientCommandList;


import command.CommandParser;

import server.logic.IUserRelated;
import server.logic.User;

public class TCPServerConnection implements Runnable, IUserRelated{
	private Socket client;
	private static final int NTHREADS=10;

	private BufferedReader in = null;
	private CommandParser parser = null;
	private User user = null;
	private InetAddress address=null;
	private int clientPort;
	private ExecutorService executor;
	private boolean listening=true;

	public TCPServerConnection(Socket client){
		this.client=client;
		parser= new CommandParser(true,this);
		parser.setCommandList(new ClientCommandList(this));
		address=client.getInetAddress();

		executor = Executors.newFixedThreadPool(NTHREADS);

	}

	@Override
	public void run() {
		try {
			//out= new PrintWriter(client.getOutputStream(),true);
			in= new BufferedReader(new InputStreamReader(client.getInputStream()));
			String input;
			String answer;
			while(((input=in.readLine())!=null)){
				if(!listening)
					break;
				if(input.equals("!end")){
					if(!user.equals(""))
						parser.parse("!logout");
					break;
					
				}
				answer= parser.parse(input);
				//out.println("server says: "+ answer);
				notify(answer);
				//System.out.println(answer);
			}

		} catch (IOException e) {

		} catch(RejectedExecutionException e){
			
		}
		finally{
			shutdown();
		}
	}

	public synchronized void shutdown(){
		System.out.println("TCP connection shutdown");
		listening=false;

		try {
			//System.out.println("remote-kill client");
			notify("!kill");
			//out.close();
			
			executor.shutdown();
			
			executor.awaitTermination(1, TimeUnit.SECONDS);


			//System.out.println("waiting done");

			client.close();
			/*
			System.out.println("close in!");
			in.close();
			System.out.println("in closed");
			client.shutdownInput();
			System.out.println("shutdownInput");
			client.shutdownOutput();
			System.out.println("shutdownOutput");
			 */

			
		}catch(RejectedExecutionException e){
			
		}
		catch (IOException e) {
			
		} catch (InterruptedException e) {
			
		}
		finally{ 
			//System.out.println("shutdown accomplished");
			try {
				this.finalize();
			} catch (Throwable e) {

			}finally{
				//System.out.println(TCPServerConnection.class.getSimpleName()+" finalized");
			}
		}
	}

	public void setUser(User user){
		this.user=user;
	}

	public User getUserObject() {
		return user;
	}

	public InetAddress getInetAddress(){
		return client.getInetAddress();
	}

	public void setPort(int port) {
		this.clientPort = port;
	}
	public int getClientPort() {
		return clientPort;
	}

	private synchronized void notify(String message) throws RejectedExecutionException{
		UDPNotificationThread thread;
		thread= new UDPNotificationThread(address,clientPort,message);
		executor.execute(thread);
	}

	@Override
	public void setUser(String user) {
		this.user=new User(user);
		this.user.setAddress(address);
		this.user.setPort(clientPort);
		this.user.setLoggedIn(true);
	}

	@Override
	public String getUser() {
		if(user!=null)
			return user.getName();
		else return "";
	}

	public void setUserObject(User user) {
		this.user=user;

	}


}
