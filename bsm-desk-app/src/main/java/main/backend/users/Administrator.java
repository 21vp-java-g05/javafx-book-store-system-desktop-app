package main.backend.users;

public class Administrator extends Account {
	public Administrator(String id, String username, String password, String mail) {
		super(id, username, password, mail);
	}
	public Administrator(String id, String username, String password, String mail, boolean enabled) {
		super(id, username, password, mail, enabled);
	}
	public Administrator(Administrator other) {
		super(other);
	}

	@Override
	public String toString() {
		String str = "Administrator:" + "\n";
		return str + super.toString();
	}
}
