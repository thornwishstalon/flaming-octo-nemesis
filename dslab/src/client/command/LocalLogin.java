package client.command;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.KeyPair;
import java.security.PrivateKey;

import org.bouncycastle.openssl.PEMReader;
import org.bouncycastle.openssl.PasswordFinder;

import network.security.Base64StringDecorator;
import network.security.IStringStream;
import network.security.RSAStringDecorator;
import network.security.SimpleStringStream;
import network.security.StaticStream;
import client.ClientMain;
import client.ClientStatus;
import command.ICommand;

public class LocalLogin implements ICommand {

	
	@Override
	public int numberOfParams() {
		return 1;
	}

	@Override
	public String execute(String[] params) {
		
		if(!ClientStatus.getInstance().getUser().equals("")){
			System.out.println("Error: "+ClientStatus.getInstance().getUser()+" still logged in!");
			return "";
		}
		else {
			
			// read private key + password
			PrivateKey privateKey;
			PEMReader in;
			
			try {
				if(params[0].length()<7) {
					System.out.println("Please specify a valid username!");
					return "";
				}
				
				String pathToPrivateKey = ClientMain.getClientSetup().getClientKeyDir() + params[0].substring(7) + ".pem";
				System.out.println("Opening RSA-File " + pathToPrivateKey);
				
				in = new PEMReader(new FileReader(pathToPrivateKey), new PasswordFinder() {
					
					@Override
					public char[] getPassword() {
						System.out.println("Enter pass phrase:");
						try {
							return new BufferedReader(new InputStreamReader(System.in)).readLine().toCharArray();
						} catch (IOException e) {
							return null;
						}
					} 
				});

				KeyPair keyPair = (KeyPair) in.readObject();
				privateKey = keyPair.getPrivate(); 
				in.close();
			} catch (FileNotFoundException e) {
				System.out.println("An RSA-Key for the specified username could not be found.");
				return "";
			} catch (IOException e1) {
				System.out.println("RSA-Key could not be accessed. Please retry.");
				return "";
			}
			
			// set username
			ClientStatus.getInstance().setUser(params[0].substring(7));
			
			// Basic command params [!login <username> <tcpPort>]
			String loginMsg = params[0] + " " + ClientMain.getClientSetup().getClientPort() + " ";
			
			// <client-challenge>
			int challenge = RSAStringDecorator.secureRand();
			ClientMain.getClientSetup().setRSAChallenge(challenge);
			loginMsg += Base64StringDecorator.encodeBase64Helper(String.valueOf(challenge));	
			
			// set encoder (RSA(Base64))
			IStringStream s = new RSAStringDecorator(new SimpleStringStream(), ClientMain.getClientSetup().getPubKeyServer(), privateKey);
			StaticStream.getStaticStreamInstance().setEncoderStream(s);
			// set decoder (Base64(RSA))
			StaticStream.getStaticStreamInstance().setDecoderStream(s);
			
			
			return loginMsg;
		}
	}

	@Override
	public boolean needsRegistration() {
		return false;
	}

}
