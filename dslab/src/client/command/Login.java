package client.command;

import java.security.PublicKey;

import server.ServerMain;
import server.logic.UserDATABASE;
import network.security.AESStringDecorator;
import network.security.Base64StringDecorator;
import network.security.IStringStream;
import network.security.RSAStringDecorator;
import network.security.SimpleStringStream;
import network.security.StaticStream;
import network.tcp.server.TCPServerConnection;
import command.ICommand;

public class Login implements ICommand {
	//!login alice
	private TCPServerConnection connection;

	public Login(TCPServerConnection connection) {
		this.connection=connection;
	}

	@Override
	public int numberOfParams() {
		return 3;
	}

	@Override
	public synchronized String execute(String[] params) {
		
		String username= params[0].trim();		
		
		int x = UserDATABASE.getInstance().loginUser(username, connection.getInetAddress(), connection.getClientPort(),connection );//Integer.valueOf(port));
		switch(x){
		case UserDATABASE.NO_USER_WITH_THAT_NAME:
			
		case UserDATABASE.SUCCESSFULLY_LOGGED_IN: 
			connection.setUser(UserDATABASE.getInstance().getUser(username));
			break;
			//return "!ack-login "+username;
			
		case UserDATABASE.ALREADY_LOGGED_IN: return "!print Error: user is already logged in!";

		case UserDATABASE.SUCCESSFULLY_LOGGED_IN_HAS_NOTIFICATIONS:
			connection.setUser(UserDATABASE.getInstance().getUser(username));
			break;
			//return "!ack-login "+username+"\n"+UserDATABASE.getInstance().getNotifactions(username);
		}

		/* 
		 * RSA / AES Handshake
		 */
		PublicKey userPK = ServerMain.getSetup().getSinglePubRSAKey(username);
		if(!userPK.equals(null)) {
			
			// create and send answer to RSA-coded !login
			connection.setOutputEncoder(new RSAStringDecorator
					(new SimpleStringStream(), userPK, ServerMain.getSetup().getServerKey()));
			
			String secretKey = AESStringDecorator.generateSecretKey();
			String ivParam = AESStringDecorator.generateRandomIV();
			int serverChallenge = RSAStringDecorator.secureRand();
			
			connection.confirmAESHandshake(serverChallenge);
			connection.setAesSecretKey(secretKey);
			connection.setAesIVParam(ivParam);
			
			// send 2nd message: !ok <client-challenge> <server-challenge> <secret-key> <iv-parameter>
			String responseMsg = "!ok " + params[2] + " " + 
					Base64StringDecorator.encodeBase64Helper(String.valueOf(serverChallenge)) + " " +
					secretKey + " " + ivParam;
			
			connection.print(responseMsg);
			
			// change encoding / decoding to AES
			IStringStream s = new AESStringDecorator(new SimpleStringStream(), secretKey, ivParam);
			connection.setOutputEncoder(s);
			connection.setInputDecoder(s);
			
			
			return "";
		}
		
		return "!print ERROR: something is missing here";
	}

	@Override
	public boolean needsRegistration() {
		return false;
	}

	
	/*
	 * 
	 * return "Successfully logged in as "+username;

		}else if((user != null)&&(user.isLoggedIn())){
			return "Error: user is already logged in!";
		}else{
			user.setLoggedIn(true);
			return "Successfully logged in as "+username;
		}
	 * 
	 */



}
