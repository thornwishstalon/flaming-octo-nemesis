package network.security;

import org.bouncycastle.util.encoders.Base64;

public class Base64StringDecorator extends AbstractStringStream {

	public Base64StringDecorator(IStringStream stream) {
		super(stream);
	}
	
	
	@Override
	public String getIncomingStream(String in) {		
		return stream.getIncomingStream(decodeBase64Helper(in));
	}

	@Override
	public String putOutgoingStream(String out) {		
		return stream.putOutgoingStream(encodeBase64Helper(out));
	}
	
	/*
	 * HELPER - can be accessed from everywhere
	 */
	public static String decodeBase64Helper(String s) {
		byte[] msg = Base64.decode(s);
		return new String(msg);
	}
	
	public static String encodeBase64Helper(String s) {
		byte[] msg = s.getBytes();
		byte[] encMsg = Base64.encode(msg);
		return new String(encMsg);
	}
}
