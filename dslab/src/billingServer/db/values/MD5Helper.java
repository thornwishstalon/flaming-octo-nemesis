package billingServer.db.values;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Helper {
	private static MessageDigest md=null;

	/*
	public static String md5ToString(String hash){
		String md5=null;
		if(md==null){
			initMD();
		}

		md.update(hash.getBytes(), 0, hash.length());
		md5 = new BigInteger(1, md.digest()).toString(16); // Encrypted
		return md5;
	}
	*/

	public static String StringToMD5(String input) {
		String md5 = null;
		if(null == input) return null;
		if(md==null){
			initMD();
		}

		md.update(input.getBytes(), 0, input.length());
		md5 = new BigInteger(1, md.digest()).toString(16);

		return md5;
	}

	private static void initMD(){

		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

	}
}
