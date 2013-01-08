package client.command.response;

import org.bouncycastle.util.encoders.Base64;
import client.ClientMain;
import client.ClientStatus;
import network.security.AESStringDecorator;
import network.security.Base64StringDecorator;
import network.security.IStringStream;
import network.security.SimpleStringStream;
import network.security.StaticStream;
import command.ICommand;

/*
 * Processes the server-answer (2nd message) in the RSA handshake 
 * and sends the challenge back to the server (3rd message)
 */

public class RSAServerConfirmation implements ICommand {

	@Override
	public int numberOfParams() {
		return 4;
	}

	@Override
	public String execute(String[] params) {
		
		// check if the challenge is valid
		if(ClientMain.getClientSetup().getRSAChallenge() == 
				Integer.parseInt(Base64StringDecorator.decodeBase64Helper(params[0]))) {
			
			/* DEBUG -> read 2nd msg of handshake & answer challenge
			System.out.println("VALID: " + ClientMain.getClientSetup().getRSAChallenge() + " == " + Base64StringDecorator.decodeBase64Helper(params[0]));
			System.out.println("ServerChallenge " + Base64StringDecorator.decodeBase64Helper(params[1]));
			System.out.println(params[2]);
			System.out.println(params[3]);
			*/
			
			IStringStream stream = new AESStringDecorator(new SimpleStringStream(), params[2], params[3]);
			StaticStream.getStaticStreamInstance().setEncoderStream(stream);
			StaticStream.getStaticStreamInstance().setDecoderStream(stream);
			
			// save AESKey + ivParam
			ClientMain.getClientSetup().setAesSecretKey(params[2]);
			ClientMain.getClientSetup().setAesIVParam(params[3]);
			
			// send confirmation to server
			ClientMain.printToOutputstream(Base64StringDecorator.decodeBase64Helper(params[1]));
			
			return "!print Successfully logged in as "+ ClientStatus.getInstance().getUser();
				
		} else {
			StaticStream.getStaticStreamInstance().setEncoderStream(new SimpleStringStream());
			StaticStream.getStaticStreamInstance().setDecoderStream(new SimpleStringStream());
			ClientStatus.getInstance().setUser("");
			return "!print Invalid Server-Response. Login canceled due to security issues.";
		}
		
		
		
		
		
	}

	@Override
	public boolean needsRegistration() {
		return false;
	}

}
