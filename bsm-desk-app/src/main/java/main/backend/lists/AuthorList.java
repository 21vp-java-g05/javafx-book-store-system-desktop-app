package main.backend.lists;

import java.sql.ResultSet;
import java.util.ArrayList;

import main.backend.objects.Author;
import main.backend.utils.DBconnect;

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
		String condition = name == null ? null : ("WHERE Name = " + name);
		try (
			DBconnect db = new DBconnect();
			ResultSet rs = db.view("AUTHOR", condition);
		) {
			authors.clear();
			while (rs.next())
				authors.add(new Author(rs.getInt(0), rs.getString(1), rs.getString(2)));
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

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
