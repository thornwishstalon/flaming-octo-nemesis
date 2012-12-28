package security.hmac;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.util.encoders.Hex;

public abstract class AbstractHesher {
	protected Key key;
	protected Mac mac;
	protected String path;
	
	protected AbstractHesher() {
		
	}
	
	protected AbstractHesher(String keyPath){
		 setKeyPath(keyPath);
	}
	
	private void init(){
		byte[] keyBytes = new byte[1024];
		FileInputStream fis;
		try {
			fis = new FileInputStream(path);
			fis.read(keyBytes);
			fis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		byte[] input = Hex.decode(keyBytes);
		// TODO algorithm???
		key = new SecretKeySpec(input,"HmacSHA256");
		
		try {
			mac = Mac.getInstance("HmacSHA256");
			mac.init(key);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} 
	}
	
	protected void setKeyPath(String keyPath){
		this.path=keyPath;
		init();
	}
	
	
	protected byte[] hash(String message) {
		mac.update(message.getBytes());
		return mac.doFinal();
	}
	
	protected boolean compare(byte[] receivedHash, byte[] computedHash){
		return MessageDigest.isEqual(computedHash,receivedHash);

	}
	
	
}
