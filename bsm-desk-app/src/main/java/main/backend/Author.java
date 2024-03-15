/*
 * Họ và tên: Nguyễn Trần Châu Minh
 * MSSV		: 21126030
 * Lớp		: 21VP
*/

public class Author {
	private String id;
	private String author_name;
	private String author_description;
	
	public Author(String id, String name, String description) {
		this.id = id;
		author_name = name;
		author_description = description;
	}
	public Author(Author author) {
		id = author.id;
		author_name = author.author_name;
		author_description = author.author_description;
	}

	public String getId() { return id; }
	public String getAuthorName() { return author_name; }
	public String getAuthorDescription() { return author_description; }

	public void setId(String id) { this.id = id; }
	public void setAuthorName(String author_name) { this.author_name = author_name; }
	public void setAuthorDescription(String author_description) { this.author_description = author_description; }
	
	@Override
	public String toString() {
		String idStr = "\tID: " + id + "\n";
		String nameStr = "\tAuthor name: " + author_name + "\n";
		String disStr = "\tAuthor description: " + author_description + "\n";
		
		return idStr + nameStr + disStr;
	}
}
