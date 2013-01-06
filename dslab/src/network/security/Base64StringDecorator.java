package network.security;

import java.io.UnsupportedEncodingException;

import org.bouncycastle.util.encoders.Base64;

public class Base64StringDecorator extends AbstractStringStream {

	public Base64StringDecorator(IStringStream stream) {
		super(stream);
	}
	
	
	@Override
	public String getIncomingStream(String in) {		
		return decodeBase64Helper(stream.getIncomingStream(in));
	}

	@Override
	public String putOutgoingStream(String out) {		
		return encodeBase64Helper(stream.putOutgoingStream(out));
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
