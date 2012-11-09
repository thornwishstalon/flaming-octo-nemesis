package billingServer.db.content;

import java.util.StringTokenizer;

public class ManagementUser {
	private String userName;
	private String password;

	public ManagementUser(String name, String password) {
		this.userName=name;
		this.password=password;
	}

	public ManagementUser(String line) {
		StringTokenizer token= new StringTokenizer(line, "=");
		
		
		userName=token.nextToken().trim();
		//System.out.println(userName);
		
		password= token.nextToken().trim();	
		
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String toString(){
		return userName+" "+password+"\n";
	}



}
