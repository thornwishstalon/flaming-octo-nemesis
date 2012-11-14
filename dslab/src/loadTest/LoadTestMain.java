package loadTest;

public class LoadTestMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("STARTING LOADTEST:\n____________#_#");
		
		LoadTestSetup setup= new LoadTestSetup(args);
		System.out.println(setup.toString());
		
		
		
		//spawn loadTest clients...

	}

}
