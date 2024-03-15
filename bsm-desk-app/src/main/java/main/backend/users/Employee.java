package main.backend.users;

public class Employee extends Account {
	public Employee(String id, String username, String password, String mail) {
		super(id, username, password, mail);
	}
	public Employee(String id, String username, String password, String mail, boolean enabled) {
		super(id, username, password, mail, enabled);
	}
	public Employee(Account account) {
		super(account);
	}
}
