package client.command.response;

import security.hmac.Hesher;
import command.ICommand;

/**
 * FOR TESTING REASONS
 *
 */
public class DEHESH_TEST implements ICommand {
	private Hesher hesher;
	
	public DEHESH_TEST(String keyPath){
		hesher= new Hesher(keyPath);
	}
	
	@Override
	public int numberOfParams() {
		return 2; //!dehesh <hesh> <message>
	}

	@Override
	public String execute(String[] params) {
		String message=params[1];
		String serverHesh=params[0];
		
		String clientHesh= hesher.hashMessage(message);
		System.out.println(serverHesh);
		System.out.println(clientHesh);
		if(serverHesh.equals(clientHesh))
			return "!print"+" HMAC is valid";

		else return "!print"+" the message has been altered!!";
	}

	@Override
	public boolean needsRegistration() {
		// TODO Auto-generated method stub
		return false;
	}

}
