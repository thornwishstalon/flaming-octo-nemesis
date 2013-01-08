package client;

public class LocalUserListItem {
	private String ipAddress, username;
	private int port;
	
	public LocalUserListItem(String ipAddress, String username, int port) {
		this.ipAddress = ipAddress;
		this.username = username;
		this.port = port;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
	
	
}
