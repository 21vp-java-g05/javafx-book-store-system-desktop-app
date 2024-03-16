package main.backend.objects;

public class Publisher {
	private String id, name, description;
	private boolean enabled;
	
	public Publisher(String id, String name, String description, boolean enabled) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.enabled = enabled;
	}
	public Publisher(String id, String name, String description) {
		this(id, name, description, true);
	}
	public Publisher(Publisher other) {
		this(other.id, other.name, other.description, other.enabled);
	}

	public String getId() { return id; }
	public String getPublisherName() { return name; }
	public String getPublisherDescription() { return description; }
	public boolean isEnabled() { return enabled; }

	public void setId(String id) { this.id = id; }
	public void setPublisherName(String name) { this.name = name; }
	public void setPublisherDescription(String description) { this.description = description; }
	public void setEnable(boolean enabled) { this.enabled = enabled; }

	@Override
	public String toString() {
		String idStr = "\tID: " + id + "\n";
		String nameStr = "\tPublisher name: " + name + "\n";
		String disStr = "\tPublisher description: " + description + "\n";
		String stsStr = "\tStatus: " + (enabled ? "enable" : "disable") + "\n";
		
		return idStr + nameStr + disStr + stsStr;
	}
}
