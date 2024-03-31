package main.frontend.backend.lists;

import main.frontend.backend.objects.Author;
import main.frontend.backend.objects.Book;
import main.frontend.backend.objects.Category;
import main.frontend.backend.objects.Publisher;
import main.frontend.backend.utils.DBconnect;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

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

	public ArrayList<Book> loadBooks_fromDatabase(String name) {
		String condition = name == null ? "" : ("WHERE name = '" + name + "'");
		ArrayList<Book> books = new ArrayList<>();
		try (
				DBconnect db = new DBconnect();
				Statement st = db.getConnection().createStatement();
				ResultSet rs = st.executeQuery("SELECT * FROM BOOK " + condition);
		) {
			while (rs.next()) {
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String isbn = rs.getString("isbn");
				String language = rs.getString("language");
				int numberOfPages = rs.getInt("number_of_pages");
				int publisherID = rs.getInt("publisher");
				int authorID = rs.getInt("author");
				boolean enabled = rs.getBoolean("status");

				CategoryList cl = new CategoryList();
				try (
						Statement st1 = db.getConnection().createStatement();
						ResultSet rs1 = st1.executeQuery("SELECT * FROM CATEGORY " +
								"JOIN CATEGORY_BOOK ON CATEGORY.id = CATEGORY_BOOK.category_id " +
								"WHERE CATEGORY_BOOK.book_id = " + id);
				) {
					while (rs1.next()) {
						Category c = new Category();
						c.setId(rs1.getInt("category_id"));
						c.setName(rs1.getString("name"));
						c.setDescription(rs1.getString("description"));
						c.setEnabled(rs1.getBoolean("status"));
						cl.add(c);
					}
				}

				PublisherList pl = new PublisherList();
				try (
						Statement st2 = db.getConnection().createStatement();
						ResultSet rs2 = st2.executeQuery("SELECT * FROM PUBLISHER WHERE id = " + publisherID);
				) {
					while (rs2.next()) {
						Publisher p = new Publisher();
						p.setId(rs2.getInt("id"));
						p.setName(rs2.getString("name"));
						p.setDescription(rs2.getString("description"));
						p.setEnabled(rs2.getBoolean("status"));
						pl.add(p);
					}
				}

				AuthorList al = new AuthorList();
				try (
						Statement st3 = db.getConnection().createStatement();
						ResultSet rs3 = st3.executeQuery("SELECT * FROM AUTHOR WHERE id = " + authorID);
				) {
					while (rs3.next()) {
						Author a = new Author();
						a.setId(rs3.getInt("id"));
						a.setName(rs3.getString("name"));
						a.setDescription(rs3.getString("description"));
						a.setEnabled(rs3.getBoolean("status"));
						al.add(a);
					}
				}

				Book book = new Book(id, title, isbn, language, numberOfPages, pl.getPublisherByID(publisherID), al.getAuthorByID(authorID), cl, enabled);
				books.add(book);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return books;
	}




	@Override
	public String toString() {
		String str = "There are " + books.size() + " books in the list.\n\n";

		for (Book book : books)
			str += book.toString() + "\n";
		
		return str;
	}
	
}
