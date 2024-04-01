package main.frontend.backend.objects;

public class Publisher {
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
	
	public Publisher(int id, String name, String description, boolean enabled) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.enabled = enabled;
	}
	public Publisher(int id, String name, String description) { this(id, name, description, true); }
	public Publisher(Publisher other) { this(other.id, other.name, other.description, other.enabled); }

	public Publisher() {

	}

	public int getId() { return id; }
	public String getPublisherName() { return name; }
	public String getPublisherDescription() { return description; }
	public boolean isEnabled() { return enabled; }

	public void changeInfo(int id, String name, String description, boolean enabled) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.enabled = enabled;
	}

	@Override
	public String toString() {
		String idStr = "\tID: " + String.valueOf(id) + "\n";
		String nameStr = "\tPublisher name: " + name + "\n";
		String disStr = "\tPublisher description: " + description + "\n";
		String stsStr = "\tStatus: " + (enabled ? "enable" : "disable") + "\n";
		
		return idStr + nameStr + disStr + stsStr;
	}
}