package main.frontend.backend.objects;

import main.frontend.backend.lists.CategoryList;

public class Book {
	private int id, numberOfPages;
	private String isbn, title, language;
	private Publisher publisher;
	private Author author;
	private CategoryList categories;
	private boolean enabled;
	
	public Book() {}
	public Book(int id, String title, String isbn, String language, int numberOfPages, Publisher publisher, Author author, CategoryList categories, boolean enabled) {
		this.id = id;
		this.isbn = isbn;
		this.title = title;
		this.language = language;
		this.numberOfPages = numberOfPages;
		this.publisher = publisher;
		this.author = author;
		this.categories = categories;
		this.enabled = enabled;
	}
	public Book(int id, String title, String isbn, String language, int numberOfPages, Publisher publisher, Author author, CategoryList categories) {
		this(id, isbn, title, language, numberOfPages, publisher, author, categories, true);
	}
	public Book(Book other) {
		this(other.id, other.isbn, other.title, other.language, other.numberOfPages, other.publisher, other.author, other.categories, other.enabled);
	}

    public Book(int i) {
		this.id = i;
    }

    public int getId() { return id; }
	public String getIsbn() { return isbn; }
	public String getTitle() { return title; }
	public String getLanguage() { return language; }
	public int getNumberOfPages() { return numberOfPages; }
	public Publisher getPublisher() { return publisher; }
	public Author getAuthor() { return author; }
	public CategoryList getCategories() { return categories; }
	public boolean isEnabled() { return enabled; }
	
	public void changeInfo(int id, String title, String isbn, String language, int numberOfPages, Publisher publisher, Author author, CategoryList categories, boolean enabled) {
		this.id = id;
		this.isbn = isbn;
		this.title = title;
		this.language = language;
		this.numberOfPages = numberOfPages;
		this.publisher = publisher;
		this.author = author;
		this.categories = categories;
		this.enabled = enabled;
	}

	@Override
	public String toString() {
		String idStr = "\tID: " + String.valueOf(id) + "\n";
		String isbnStr = "\tISBN: " + isbn + "\n";
		String titleStr = "\tTitle: " + title + "\n";
		String langStr = "\tLanguage: " + language + "\n";
		String numString = "\tNumber of pages: " + numberOfPages + "\n";
		String publisherStr = "\tPublisher: " + publisher.getPublisherName() + "\n";
		String authorStr = "\tAuthor: " + author.getAuthorName() + "\n";
		String categoriesStr = "\tCategories: " + categories.toString();
		String stsStr = "\tStatus: " + (enabled ? "enable" : "disable") + "\n";
		
		return idStr + isbnStr + titleStr + langStr + numString + publisherStr + authorStr + categoriesStr + stsStr;
	}


}
