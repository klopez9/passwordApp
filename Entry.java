package passwordApp;

public class Entry {
	
	String title;
	String username;
	String password;
	
	public Entry() {
		this.title = "";
		this.username = "";
		this.password = "";
	}
	
	public Entry(String title, String username, String password) {
		this.title = title;
		this.username = username;
		this.password = password;
	}

	@Override
	public String toString() {
		return /*Title: */ this.title + "\n" +
				"Username: " + this.username + "\n" +
				"Password: " + this.password;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean hasFullFields() {
		boolean retVal = false;
		if (title != null && title != "" &&
				username != null && username != "" &&
				password != null && password != "")
			retVal = true;
		return retVal;
	}
	
}
