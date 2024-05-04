package main.frontend.backend.users;

import main.frontend.backend.lists.AccountList;
import main.frontend.backend.utils.DBconnect;

public class Administrator extends Account {
	public Administrator(int id, String fullname, String mail, String username, String password, int role) {
		super(id, fullname, mail, username, password, role);
		checkRole();
	}
	public Administrator(int id, String fullname, String mail, String username, String password, int role, boolean status) {
		super(id, fullname, mail, username, password, role, status);
		checkRole();
	}
	public Administrator(Administrator other) { super(other); }
	public Administrator(Account other) {
		super(other);
		checkRole();
	}

	private void checkRole() {
		if (role != 0) throw new IllegalArgumentException("Role must be administrator");
	}

	public AccountList loadAccounts_fromDatabase() {
		AccountList accounts = new AccountList();
		return accounts.load_fromDatabase(null) ? accounts : null;
	}
	public boolean addAccount_toDatabase(Account account) {
		DBconnect db = new DBconnect();
		String value = "(DEFAULT, " + account.toString() + ")";

		try { return (id = db.add_getAuto("ACCOUNT", value)) > 0; }
		finally { db.close(); }
	}
	public boolean editAccount_fromDatabase(Account account) {
		return account.updateAccount_toDatabase();
	}

	@Override
	public String toString() {
		return super.toString();
	}
}
