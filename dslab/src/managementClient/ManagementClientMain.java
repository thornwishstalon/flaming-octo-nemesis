package managementClient;

/**
 * ManagementClient MAIN
 * 
 *
 */
public class ManagementClientMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ManagementClientSetup setup= new ManagementClientSetup(args);
		System.out.println("Setup ready:");
		System.out.println(setup.toString());
		
		//initialize remote stuff
		ManagementClientStatus.getInstance().init(setup); 
		
		//start blocking input thread
		ManagementClientInputThread input = new ManagementClientInputThread(); 
		input.run();
		
		//.... wait
		
		
		//disconnect
		ManagementClientStatus.getInstance().disconnect(); 
		
		//ending
		System.out.println("Goodbye");
		

	}

}
