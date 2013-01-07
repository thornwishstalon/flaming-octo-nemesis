package network.security;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Random;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


import org.bouncycastle.util.encoders.Base64;

public class AESStringDecorator extends AbstractStringStream {
	
	private byte[] secretKey, ivParam;
	private Cipher encryptCipher, decryptCipher;
	
	public AESStringDecorator(IStringStream stream, String secretKey, String ivParam) {
		super(stream);

		this.secretKey = Base64.decode(secretKey.getBytes());
		this.ivParam = Base64.decode(ivParam.getBytes());
		init();
	}
	
	public AESStringDecorator(IStringStream stream, byte[] secretKey, byte[] ivParam) {
		super(stream);
		this.secretKey=secretKey;
		this.ivParam=ivParam;
		init();
	}
	
	private void init() {
		try {
			// encryption cipher
			encryptCipher = Cipher.getInstance("AES/CTR/NoPadding", "BC");
			encryptCipher.init(Cipher.ENCRYPT_MODE, 
					new SecretKeySpec(secretKey, "AES"), new IvParameterSpec(ivParam));
			// decryption cipher
			decryptCipher = Cipher.getInstance("AES/CTR/NoPadding", "BC");
			decryptCipher.init(Cipher.DECRYPT_MODE, 
					new SecretKeySpec(secretKey, "AES"), new IvParameterSpec(ivParam));
			
		} catch (NoSuchAlgorithmException | NoSuchProviderException
				| NoSuchPaddingException | InvalidAlgorithmParameterException 
				| InvalidKeyException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public String getIncomingStream(String in) {
		
		in = stream.getIncomingStream(in);
		try {
			byte[] msg = Base64.decode(in.getBytes());
			in = new String(decryptCipher.doFinal(msg));
		} catch (IllegalBlockSizeException 
				| BadPaddingException e) {
			System.out.println("AES-Decryption failed. Invalid Input.");
			e.printStackTrace();
		}
		
		return in;
	}

	@Override
	public String putOutgoingStream(String out) {
		
		out = stream.getIncomingStream(out);
		try {
			out = new String(Base64.encode(encryptCipher.doFinal(out.getBytes())));
		} catch (IllegalBlockSizeException | BadPaddingException e) {
			System.out.println("AES-Encryption failed. Invalid Input.");
			e.printStackTrace();
		}
		
		return out;
	}
	
	/*
	 * HELPER - creates Base64 encoded IV Param
	 */
	public static String generateRandomIV() {
		byte[] iv = new byte[16];
		new Random().nextBytes(iv);
		return new String(Base64.encode(iv));
	}
	
	/*
	 * HELPER - creates Base64 encoded secret key
	 */
	public static String generateSecretKey() {
		KeyGenerator generator;
		try {
			generator = KeyGenerator.getInstance("AES");
			generator.init(256);
			SecretKey key = generator.generateKey();
			return new String(Base64.encode(key.getEncoded()));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} 
	}

}
