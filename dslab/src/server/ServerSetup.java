package server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.HashMap;
import java.util.Properties;

import org.bouncycastle.openssl.PEMReader;
import org.bouncycastle.openssl.PasswordFinder;



public class ServerSetup {
	// SERVER
	private int port;
	private String analyticsServerName;
	private String billingServerName;
	//
	private String username;
	private String password;
	private String registry;
	private int registryPort=11269;
	// RSA
	private String serverKeyfile;
	private String clientKeyDir;
	private String RSApassword;
	private PrivateKey serverKey;
	private HashMap<String, PublicKey> userPubRSAKeys;
	
	public ServerSetup(String[] args){
		port= Integer.valueOf(args[0]);
		analyticsServerName=args[1];
		billingServerName=args[2];
		serverKeyfile=args[3];
		clientKeyDir=args[4];
		
		userPubRSAKeys = new HashMap<String, PublicKey>();
		loadServerUsername();
		loadRSAKeys();
	}
	
	public int getPort() {
		return port;
	}
	
	public String toString(){
		return "setup: \nport: "+port;
	}

	public String getAnalyticsServerName() {
		return analyticsServerName;
	}

	public void setAnalyticsServerName(String analyticsServerName) {
		this.analyticsServerName = analyticsServerName;
	}

	public String getBillingServerName() {
		return billingServerName;
	}

	public void setBillingServerName(String billingServer) {
		this.billingServerName = billingServer;
	}
	
	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	private void loadServerUsername() {
		InputStream in= ClassLoader.getSystemResourceAsStream("server.properties");
		if(in!=null){
			Properties props= new Properties();
			try{
				props.load(in);
				this.username= props.getProperty("server.name").trim();
				this.password= props.getProperty("server.password").trim();
				this.RSApassword= props.getProperty("server.RSApassword").trim();
				
			}catch(NumberFormatException e){
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	

		}else System.err.println("File not found!");
	}
	
	private void loadRSAKeys() {
		
		// Server - Private Key
		PEMReader in;
		try {
			//System.out.println("SERVER KEYFILE " + serverKeyfile);
			
			in = new PEMReader(new FileReader(serverKeyfile), new PasswordFinder() {
				@Override
				public char[] getPassword() {
					return RSApassword.toCharArray();
				} 
			});

			KeyPair keyPair;
			keyPair = (KeyPair) in.readObject();
			serverKey = keyPair.getPrivate();

		} catch (FileNotFoundException e) {
			System.out.println("RSA-Keyfile not found.");
			e.printStackTrace();
		} catch (IOException e1) {
			System.out.println("RSA-Key could not be loaded.");
			e1.printStackTrace();
		}
		

		// User - Public Keys
		File folder = new File(clientKeyDir);
		File[] listOfFiles = folder.listFiles(); 
		String name;
		
		for(int i=0; i<listOfFiles.length; i++) {
			if( (listOfFiles[i].isFile()) && (listOfFiles[i].getName().endsWith(".pub.pem")) ) {
				 try {
					in = new PEMReader(new FileReader(clientKeyDir + listOfFiles[i].getName()));
					name = listOfFiles[i].getName();
					
					userPubRSAKeys.put(name.substring(0, name.indexOf(".pub")), (PublicKey) in.readObject());
					
				} catch (FileNotFoundException e) {
					System.out.println("File " + listOfFiles[i].getPath() + " could not be found.");
					e.printStackTrace();
				} catch (IOException e1) {
					System.out.println("RSA-Key could not be loaded.");
					e1.printStackTrace();
				}
			}
		}	
		
		
	}
	
	// get RSA keys
	
	public PublicKey getSinglePubRSAKey(String username) {
		return userPubRSAKeys.get(username);
	}
	
	public PrivateKey getServerKey() {
		return serverKey;
	}
}
