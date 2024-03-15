package main.backend.objects;

public class Author {
	private String id, name, description;
	private boolean enabled;
	
	public Author(String id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
		enabled = true;
	}
	public Author(String id, String name, String description, boolean enabled) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.enabled = enabled;
	}
	public Author(Author author) {
		id = author.id;
		name = author.name;
		description = author.description;
		enabled = author.enabled;
	}

	public String getId() { return id; }
	public String getAuthorName() { return name; }
	public String getAuthorDescription() { return description; }
	public boolean isEnabled() { return enabled; }

	public void setId(String id) { this.id = id; }
	public void setAuthorName(String name) { this.name = name; }
	public void setAuthorDescription(String description) { this.description = description; }
	public void setEnable(boolean enabled) { this.enabled = enabled; }
	
	@Override
	public String toString() {
		String idStr = "\tID: " + id + "\n";
		String nameStr = "\tAuthor name: " + name + "\n";
		String disStr = "\tAuthor description: " + description + "\n";
		
		return idStr + nameStr + disStr;
	}
}
