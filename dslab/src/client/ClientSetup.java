package client;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.PublicKey;

import org.bouncycastle.openssl.PEMReader;

public class ClientSetup {
	private int clientPort;
	private int serverPort;
	private String host;
	private int rsaChallenge=0;
	private PublicKey pubKeyServer;
	private String clientKeyDir;
	
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
	
}
