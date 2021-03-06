package client.command.response;

import network.security.Hesher;

import org.bouncycastle.util.encoders.Base64;

import client.ClientSetup;
import client.ClientStatus;

import command.ICommand;

/**
 * FOR TESTING REASONS
 *
 */
public class DEHESH_TEST implements ICommand {
	private Hesher hesher;
	private ClientSetup setup;
	
	public DEHESH_TEST(ClientSetup setup){
		this.setup= setup;
	}
	
	@Override
	public int numberOfParams() {
		return 2; //!dehesh <hesh> <message>
	}

	@Override
	public String execute(String[] params) {
		hesher= new Hesher();
		hesher.setKey(setup.getAesSecretKey());
		
		String message=params[1];
		String serverHesh=params[0];
		
		String clientHesh= new String (Base64.encode(hesher.hashMessage(message).getBytes())) ; //
		//System.out.println(serverHesh);
		//System.out.println(clientHesh);
		//System.out.println(message);
		
		if(serverHesh.equals(clientHesh))
			return "!print "+message;

		else return "!sendLastCommand";
	}

	@Override
	public boolean needsRegistration() {
		// TODO Auto-generated method stub
		return false;
	}

}
