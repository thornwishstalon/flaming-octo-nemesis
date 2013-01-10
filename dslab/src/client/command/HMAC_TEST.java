package client.command;

import network.security.Hesher;
import command.ICommand;


/**
 * FOR TESTING REASONS
 *
 */

public class HMAC_TEST implements ICommand {
	private Hesher hesher;
	public HMAC_TEST(String path){
		hesher= new Hesher();
	}
	
	@Override
	public int numberOfParams() {
		// !hesh <message>
		return 1;
	}

	@Override
	public String execute(String[] params) {
		String message= params[0];
		String hesh= hesher.hashMessage(message);
		
		System.out.println(hesh);
		
		return "!dehesh "+hesh+" "+message;
	}

	@Override
	public boolean needsRegistration() {
		return false;
	}

}
