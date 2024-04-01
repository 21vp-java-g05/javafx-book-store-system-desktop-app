package main.frontend.backend.lists;

import main.frontend.backend.objects.Author;
import main.frontend.backend.utils.DBconnect;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AuthorList {
	private ArrayList<Author> authors;
	
	public AuthorList() { authors = new ArrayList<>(); }
	public AuthorList(AuthorList other) { authors = new ArrayList<>(other.authors); }
	
	public void add(Author author) { authors.add(author); }
	public void clear() { authors.clear(); }
	public Author getAuthorByID(int id) {
		for (Author author : authors)
			if (author.getId() == id) return author;
		return null;
	}

	public boolean loadAuthors_fromDatabase(String name) {
		String condition = name == null ? null : ("name LIKE '%" + name + "%'");
		DBconnect db = new DBconnect();
		authors = new ArrayList<Author>();
		
		try (ResultSet rs = db.view("AUTHOR", condition);) {
			while (rs.next())
				authors.add(new Author(rs.getInt("id"), rs.getString("name"), rs.getString("description"), rs.getBoolean("status")));
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally { db.close(); }
		
		return true;
	}
	
	@Override
	public String toString() {
		String str = "There are " + authors.size() + " authors in the list.\n\n";

		for (Author author : authors)
			str += author.toString() + "\n";
		
		return str;
	}
}
