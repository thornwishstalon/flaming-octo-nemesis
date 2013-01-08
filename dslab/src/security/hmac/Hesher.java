package security.hmac;

public class Hesher extends AbstractHesher{

	public Hesher(String keyPath) {
		super(keyPath);
		
	}
	
	public Hesher(){
		
	}
	public void setKey(String key){
		super.setKey(key);
	}
	
	public void setKeyPath(String path){
		super.setKeyPath(path);
	}
	
	public String hashMessage(String message){
		return new String(super.hash(message));
	}



}
