package main.backend.objects;

public class Category {
	private String id, name, description;
	private boolean enabled;
	
	public Category(String id, String name, String description, boolean enabled) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.enabled = enabled;
	}
	public Category(String id, String name, String description) {
		this(id, name, description, true);
	}
	public Category(Category other) {
		this(other.id, other.name, other.description, other.enabled);
	}

	public String getId() { return id; }
	public String getCategoryName() { return name; }
	public String getCategoryDescription() { return description; }
	public boolean isEnabled() { return enabled; }

	public void setId(String id) { this.id = id; }
	public void setCategoryName(String name) { this.name = name; }
	public void setCategoryDescription(String description) { this.description = description; }
	public void setEnable(boolean enabled) { this.enabled = enabled; }
	
	@Override
	public String toString() {
		String idStr = "\tID: " + id + "\n";
		String nameStr = "\tCategory name: " + name + "\n";
		String disStr = "\tCategory description: " + description + "\n";
		String stsStr = "\tStatus: " + (enabled ? "enable" : "disable") + "\n";
		
		return idStr + nameStr + disStr + stsStr;
	}
}
