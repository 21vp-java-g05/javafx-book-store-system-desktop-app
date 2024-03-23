package main.backend.list;

import java.util.ArrayList;

import main.backend.objects.Author;
import main.backend.objects.Book;
import main.backend.objects.Publisher;

public class BookList {
	private ArrayList<Book> books;
	
	public BookList() { books = new ArrayList<>(); }
	public BookList(BookList other) { books = new ArrayList<>(other.books); }

	public void addBook(Book book) { books.add(book); }
	
	public Publisher getPublisherById(PublisherList pl, int id) { return pl.getPublisherByID(id); }
	public Author getAuthorById(AuthorList al, int id) { return al.getAuthorByID(id); }
	public Book getBookById(int id) {
		for (Book book : books)
			if (book.getId() == id) return book;
		return null;
	}

	@Override
	public String toString() {
		String str = "There are " + books.size() + " books in the list.\n\n";

		for (Book book : books)
			str += book.toString() + "\n";
		
		return str;
	}
	
}
