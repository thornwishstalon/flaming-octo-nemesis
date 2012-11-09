package managementClient;


public class ManagementClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ManagementClientSetup setup= new ManagementClientSetup(args);
		System.out.println("Setup ready:");
		System.out.println(setup.toString());
		ManagementClientInputThread input = new ManagementClientInputThread();
		input.run();
		
		
		System.out.println("Goodbye");
		
		
		
		
		
		

	}

}
