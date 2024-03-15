/*
 * Họ và tên: Nguyễn Trần Châu Minh
 * MSSV		: 21126030
 * Lớp		: 21VP
*/

public class Book {
	private String id;
	private String isbn;
	private String title;
	private Publisher publisher;
	private String language;
	private int numberOfPages;
	private Author author;
	
	public Book(String id, String isbn, String title, Publisher publisher, String language, int numberOfPages, Author author) {
		this.id = id;
		this.isbn = isbn;
		this.title = title;
		this.publisher = new Publisher(publisher);
		this.language = language;
		this.numberOfPages = numberOfPages;
		this.author = new Author(author);
	}
	public Book(Book book) {
		id = book.id;
		isbn = book.isbn;
		title = book.title;
		publisher = new Publisher(book.publisher);
		language = book.language;
		numberOfPages = book.numberOfPages;
		author = new Author(book.author);
	}

	public String getId() { return id; }
	public String getIsbn() { return isbn; }
	public String getTitle() { return title; }
	public Publisher getPublisher() { return publisher; }
	public String getLanguage() { return language; }
	public int getNumberOfPages() { return numberOfPages; }
	public Author geAuthor() { return author; }
	public String getStrToFile() {
		return id + "\t" + isbn + "\t" + title + "\t" + publisher.getPublisherName() + "\t" + language + "\t" + String.valueOf(numberOfPages) + "\t" + author.getAuthorName();
	}
	
	public void setId(String id) { this.id = id; }
	public void setIsbn(String isbn) { this.isbn = isbn; }
	public void setTitle(String title) { this.title = title; }
	public void setPublisher(Publisher publisher) { this.publisher = new Publisher(publisher); }
	public void setLanguage(String language) { this.language = language; }
	public void setNumberOfPages(int numberOfPages) { this.numberOfPages = numberOfPages; }
	public void setAuthor(Author author) { this.author = new Author(author); }

	@Override
	public String toString() {
		String idStr = "\tID: " + id + "\n";
		String isbnStr = "\tISBN: " + isbn + "\n";
		String titleStr = "\tTitle: " + title + "\n";
		String publisherStr = "\tPublisher: " + publisher.getPublisherName() + "\n";
		String langStr = "\tLanguage: " + language + "\n";
		String numString = "\tNumber of pages: " + numberOfPages + "\n";
		String authorStr = "\tAuthor: " + author.getAuthorName() + "\n";
		
		return idStr + isbnStr + titleStr + publisherStr + langStr + numString + authorStr;
	}
}
