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
	
	public String getUserList(){
		String output="";
		
		for(String key: users.keySet()){
			output = output + "User: " + users.get(key).toString();
		}
		return output;
	}
}
