package main.backend.lists;

import java.sql.ResultSet;
import java.util.ArrayList;

import main.backend.objects.Book;
import main.backend.utils.DBconnect;

public class BookList {
	private ArrayList<Book> books;
	
	public BookList() { books = new ArrayList<>(); }
	public BookList(BookList other) { books = new ArrayList<>(other.books); }

	public void add(Book book) { books.add(book); }
	public void clear() { books.clear(); }
	public Book getBookById(int id) {
		for (Book book : books)
			if (book.getId() == id) return book;
		return null;
	}

	public boolean loadBooks_fromDatabase(String name, PublisherList pl, AuthorList au, CategoryList ca) {
		String condition = name == null ? null : ("WHERE Name = " + name);
		try (
			DBconnect db = new DBconnect();
			ResultSet rs = db.view("AUTHOR", condition);
		) {
			books.clear();
			while (rs.next()) {
				int id = rs.getInt(0);
				String title = rs.getString(1);
				String isbn = rs.getString(2);
				String language = rs.getString(3);
				int numberOfPages = rs.getInt(4);
				int publisherID = rs.getInt(5);
				int authorID = rs.getInt(6);
				boolean enabled = rs.getBoolean(8);

				CategoryList categories = new CategoryList();
				String condition1 = "WHERE BookID = " + String.valueOf(id);
				try (ResultSet rs1 = db.view("CATEGORY_BOOK", condition1);) {
					while (rs1.next())
						categories.add(ca.getCategoryByID(rs1.getInt(0)));
				} catch (Exception e) {
					System.err.println("Categories");
					e.printStackTrace();
					return false;
				}

				Book book = new Book(id, title, isbn, language, numberOfPages, pl.getPublisherByID(publisherID), au.getAuthorByID(authorID), categories, enabled);
				books.add(book);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	@Override
	public String toString() {
		String str = "There are " + books.size() + " books in the list.\n\n";

		for (Book book : books)
			str += book.toString() + "\n";
		
		return str;
	}
	
}
