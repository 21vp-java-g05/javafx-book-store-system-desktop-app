/*
 * Họ và tên: Nguyễn Trần Châu Minh
 * MSSV		: 21126030
 * Lớp		: 21VP
*/

public class Publisher {
	private String id;
	private String publisher_name;
	private String publisher_description;
	
	public Publisher(String id, String name, String description) {
		this.id = id;
		publisher_name = name;
		publisher_description = description;
	}
	public Publisher(Publisher publisher) {
		id = publisher.id;
		publisher_name = publisher.publisher_name;
		publisher_description = publisher.publisher_description;
	}

	public String getId() { return id; }
	public String getPublisherName() { return publisher_name; }
	public String getPublisherDescription() { return publisher_description; }

	public void setId(String id) { this.id = id; }
	public void setPublisherName(String publisher_name) { this.publisher_name = publisher_name; }
	public void setPublisherDescription(String publisher_description) { this.publisher_description = publisher_description; }

	@Override
	public String toString() {
		String idStr = "\tID: " + id + "\n";
		String nameStr = "\tPublisher name: " + publisher_name + "\n";
		String disStr = "\tPublisher description: " + publisher_description + "\n";
		
		return idStr + nameStr + disStr;
	}
}
