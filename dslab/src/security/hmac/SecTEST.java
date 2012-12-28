package security.hmac;

public class SecTEST {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		AbstractHesher hesher= new Hesher("keys/alice.key");
		byte[ ]tmp = hesher.hash("laal");
		for(byte b: tmp)
			System.out.println(b);

	}

}
