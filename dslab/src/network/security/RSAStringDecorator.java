package network.security;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.MGF1ParameterSpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.OAEPParameterSpec;
import javax.crypto.spec.PSource;

import org.bouncycastle.util.encoders.Base64;

public class RSAStringDecorator extends AbstractStringStream {

	private Cipher encrypt, decrypt;
	private PublicKey publicKey;
	private PrivateKey privateKey;

	public RSAStringDecorator(IStringStream stream, PublicKey publicKey, PrivateKey privateKey) {
		super(stream);
		this.publicKey = publicKey;
		this.privateKey = privateKey;
	}

	@Override
	public String getIncomingStream(String out) {		

		out = stream.getIncomingStream(out);

		try {
			decrypt = Cipher.getInstance("RSA/NONE/OAEPWithSHA256AndMGF1Padding");
			decrypt.init(Cipher.DECRYPT_MODE, privateKey, new OAEPParameterSpec("SHA-256", "MGF1", MGF1ParameterSpec.SHA1,PSource.PSpecified.DEFAULT));
		} catch (NoSuchAlgorithmException e) {
			System.out.println("RSA encryption/decryption could not be initialised.");
		} catch (NoSuchPaddingException e) {
			System.out.println("RSA encryption/decryption could not be initialised.");
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			System.out.println("RSA encryption/decryption could not be initialised.");
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			System.out.println("RSA encryption/decryption could not be initialised.");
			e.printStackTrace();
		}

		try {
			byte[] decrypted = out.getBytes();
			decrypted = Base64.decode(decrypted);
			decrypted = decrypt.doFinal(decrypted);
			out = new String(decrypted);
		} catch (IllegalBlockSizeException   e) {
			System.out.println("RSA-Decryption failed. Incoming message was ignored.");
			//e.printStackTrace();
		}  catch (BadPaddingException e) {
			System.out.println("RSA-Decryption failed. Incoming message was ignored.");
			//e.printStackTrace();
		}

		return out;
	}

	@Override
	public String putOutgoingStream(String out) {	

		out = stream.putOutgoingStream(out);

		try {
			encrypt = Cipher.getInstance("RSA/NONE/OAEPWithSHA256AndMGF1Padding");
			encrypt.init(Cipher.ENCRYPT_MODE, publicKey, new OAEPParameterSpec("SHA-256", "MGF1", MGF1ParameterSpec.SHA1,PSource.PSpecified.DEFAULT));
		} catch (NoSuchAlgorithmException  e) {
			System.out.println("RSA encryption/decryption could not be initialised.");
		} catch ( NoSuchPaddingException  e) {
			System.out.println("RSA encryption/decryption could not be initialised.");
		}catch ( InvalidKeyException e) {
			System.out.println("RSA encryption/decryption could not be initialised.");
		}catch (InvalidAlgorithmParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			byte[] encrypted = encrypt.doFinal(out.getBytes());
			encrypted = Base64.encode(encrypted);
			out = new String(encrypted);
		} catch (IllegalBlockSizeException  e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}

		return out;
	}

	/*
	 * HELPER - can be accessed from everywhere
	 */
	public static int secureRand() {
		SecureRandom secureRandom = new SecureRandom();
		final byte[] number = new byte[32];
		secureRandom.nextBytes(number);
		return secureRandom.nextInt();
	}
}
