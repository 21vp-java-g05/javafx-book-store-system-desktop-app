package main.backend.users;

public class Administrator extends Account {
	public Administrator(String id, String username, String password, String mail) {
		super(id, username, password, mail);
	}
	public Administrator(String id, String username, String password, String mail, boolean enabled) {
		super(id, username, password, mail, enabled);
	}
	public Administrator(Account account) {
		super(account);
	}
}
