package main.frontend.backend.objects;

import main.frontend.backend.utils.DBconnect;

import java.sql.SQLException;

public class Author {
	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	private int id;
	private String name, description;
	private boolean enabled;

	public Author() {}
	public Author(int id, String name, String description, boolean enabled) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.enabled = enabled;
	}
	public Author(int id, String name, boolean enabled) {
		// Call another constructor within the same class
		this(id, name, "", enabled);
	}

	// Add another constructor to handle the initialization

	public int getId() { return id; }
	public String getAuthorName() { return name; }
	public String getAuthorDescription() { return description; }
	public boolean isEnabled() { return enabled; }

	public void changeInfo(int id, String name, String description, boolean enabled) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.enabled = enabled;
	}
	
	public boolean addAuthor_toDatabase() {
		boolean rs;
		try (DBconnect db = new DBconnect();) {
			rs = db.add("AUTHOR", "");
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return rs;
	}
	public boolean updateAuthor_toDatabase() {
		
		return true;
	}

	@Override
	public String toString() {
		String idStr = "\tID: " + String.valueOf(id) + "\n";
		String nameStr = "\tAuthor name: " + name + "\n";
		String disStr = "\tAuthor description: " + description + "\n";
		String stsStr = "\tStatus: " + (enabled ? "enable" : "disable") + "\n";
		
		return idStr + nameStr + disStr + stsStr;
	}
}
