/*
 * Gets user from properties-file and handles the 
 * management users
 */

package billingServer.db;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.ConcurrentHashMap;
import billingServer.db.content.ManagementUser;

public class BillingServerUserDATABASE {

	private ConcurrentHashMap<String, ManagementUser> users;

	public BillingServerUserDATABASE() {
		users=new ConcurrentHashMap<String, ManagementUser>();
		initUsers();
	}

	/*
	 * loads all users from the properties to the "database" (ConcurrentHashMap users)
	 */
	private void initUsers() {
		InputStream in= ClassLoader.getSystemResourceAsStream("user.properties");
		if(in!=null){
			BufferedReader reader= new BufferedReader(new InputStreamReader(in));
			String line;
			ManagementUser user;
			try {
				while((line=reader.readLine())!=null){
					if(!line.startsWith("#")){
						user= new ManagementUser(line);
						users.put(user.getUserName(), user);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}


		}else System.err.println("File not found!");
	}
	
	/*
	 * checks for a user in the "database" and compares the login password
	 */
	public boolean verifyUser(String username, String password) {
		
		synchronized (users) {
			ManagementUser user = users.get(username);
			
			if(user==null)
				return false;
			if(!user.getPassword().equals(password))
				return false;

			return true;
		}
	}
	
	/*
	 * produces a string-representation of all users in the system
	 */
	public String getUserList(){
		String output="";
		
		synchronized (users) {
			for(String key: users.keySet()){
				output = output + "User: " + users.get(key).toString();
			}
		}
		
		return output;
	}
}
