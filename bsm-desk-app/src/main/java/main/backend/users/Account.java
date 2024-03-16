package main.backend.users;

public class Account {
	private String id, username, password, mail;
	private boolean enabled;

	public Account(String id, String username, String password, String mail, boolean enabled) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.mail = mail;
		this.enabled = enabled;
	}
	public Account(String id, String username, String password, String mail) {
		this(id, username, password, mail, true);
	}
	public Account(Account other) {
		this(other.id, other.username, other.password, other.mail, other.enabled);
	}

	public String getId() { return id; }
	public String getAccountUsername() { return username; }
	public String getAccountPassword() { return password; }
	public String getMail() { return mail; }
	public boolean isEnabled() { return enabled; }

	public void setId(String id) { this.id = id; }
	public void setAccountUsername(String username) { this.username = username; }
	public void setAccountPassword(String password) { this.password = password; }
	public void setMail(String mail) { this.mail = mail; }
	public void setEnable(boolean enabled) { this.enabled = enabled; }

	@Override
	public String toString() {
		String idStr = "\tID: " + id + "\n";
		String nameStr = "\tUser name: " + username + "\n";
		String disStr = "\tPassword: " + password + "\n";
		String mailStr = "\tMail: " + mail + "\n";
		String stsStr = "\tStatus: " + (enabled ? "enable" : "disable") + "\n";
		
		return idStr + nameStr + disStr + mailStr + stsStr;
	}
}
