package main.backend.objects;

public class Book {
	private String id, isbn, title, language;
	private Publisher publisher;
	private int numberOfPages;
	private Author author;
	private boolean enabled;
	
	public Book(String id, String isbn, String title, Publisher publisher, String language, int numberOfPages, Author author) {
		this.id = id;
		this.isbn = isbn;
		this.title = title;
		this.publisher = new Publisher(publisher);
		this.language = language;
		this.numberOfPages = numberOfPages;
		this.author = new Author(author);
		enabled = true;
	}
	public Book(String id, String isbn, String title, Publisher publisher, String language, int numberOfPages, Author author, boolean enabled) {
		this.id = id;
		this.isbn = isbn;
		this.title = title;
		this.publisher = new Publisher(publisher);
		this.language = language;
		this.numberOfPages = numberOfPages;
		this.author = new Author(author);
		this.enabled = enabled;
	}
	public Book(Book book) {
		id = book.id;
		isbn = book.isbn;
		title = book.title;
		publisher = new Publisher(book.publisher);
		language = book.language;
		numberOfPages = book.numberOfPages;
		author = new Author(book.author);
		enabled = book.enabled;
	}

	public String getId() { return id; }
	public String getIsbn() { return isbn; }
	public String getTitle() { return title; }
	public Publisher getPublisher() { return publisher; }
	public String getLanguage() { return language; }
	public int getNumberOfPages() { return numberOfPages; }
	public Author geAuthor() { return author; }
	public boolean isEnabled() { return enabled; }
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
	public void setEnable(boolean enabled) { this.enabled = enabled; }

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