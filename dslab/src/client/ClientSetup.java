package client;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.PublicKey;
import java.util.ArrayList;

import org.bouncycastle.openssl.PEMReader;

import client.content.LocalUserListItem;

public class ClientSetup {
	private int clientPort;
	private int serverPort;
	private String host;
	private int rsaChallenge=0;
	private PublicKey pubKeyServer;
	private String clientKeyDir;
	private String aesSecretKey, aesIVParam; 
	private ArrayList<LocalUserListItem> ActiveClients;
	
	public ClientSetup(String[] args){
		host=args[0];
		serverPort = Integer.valueOf(args[1]);
		clientPort = Integer.valueOf(args[2]);
		
		PEMReader in;
		try {
			in = new PEMReader(new FileReader(args[3]));
			pubKeyServer = (PublicKey) in.readObject();
		} catch (FileNotFoundException e) {
			System.out.println("File " + args[3] + " could not be found.");
			e.printStackTrace();
		} catch (IOException e1) {
			System.out.println("RSA-Key could not be loaded.");
			e1.printStackTrace();
		}
		
		clientKeyDir = args[4];
		ActiveClients = new ArrayList<LocalUserListItem>();
	}

	public String toString(){
		return host+":"+serverPort+"\n client port: "+clientPort;
	}

	public String getHost() {
		return host;
	}
	
	public int getClientPort() {
		return clientPort;
	}
	
	public int getServerPort() {
		return serverPort;
	}
	
	public int getRSAChallenge() {
		return rsaChallenge;
	}

	public void setRSAChallenge(int r) {
		rsaChallenge = r;
	}
	
	public PublicKey getPubKeyServer() {
		return pubKeyServer;
	}

	public String getClientKeyDir() {
		return clientKeyDir;
	}
	
	public String getAesSecretKey() {
		return aesSecretKey;
	}

	public void setAesSecretKey(String aesSecretKey) {
		this.aesSecretKey = aesSecretKey;
	}

	public String getAesIVParam() {
		return aesIVParam;
	}

	public void setAesIVParam(String aesIVParam) {
		this.aesIVParam = aesIVParam;
	}
	
	public void addActiveClient(LocalUserListItem c) {
		ActiveClients.add(c);
	}
	
	public ArrayList<LocalUserListItem> getActiveClients() {
		return ActiveClients;
	}
	
}
