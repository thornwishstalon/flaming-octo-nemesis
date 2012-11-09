package managementClient;

/**
 * ManagementClient MAIN
 * 
 * 
 * @author f
 *
 */
public class ManagementClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ManagementClientSetup setup= new ManagementClientSetup(args);
		System.out.println("Setup ready:");
		System.out.println(setup.toString());
		
		ManagementClientStatus.getInstance().init(setup); //initialize remote stuff
		
		ManagementClientInputThread input = new ManagementClientInputThread(); //start blocking input thread
		input.run();
		
		
		System.out.println("Goodbye");
		
		
		
		
		
		

	}

}
