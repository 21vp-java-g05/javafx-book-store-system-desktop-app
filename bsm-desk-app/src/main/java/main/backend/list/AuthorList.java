package main.backend.list;

import java.util.ArrayList;

import main.backend.objects.Author;

public class AuthorList {
	private ArrayList<Author> authors;
	
	public AuthorList() { authors = new ArrayList<>(); }
	public AuthorList(AuthorList other) { authors = new ArrayList<>(other.authors); }

	public void addAuthor(Author author) { authors.add(author); }

	public Author getAuthorByID(int id) {
		for (Author author : authors)
			if (author.getId() == id) return author;
		return null;
	}
	
	@Override
	public String toString() {
		String str = "There are " + authors.size() + " authors in the list.\n\n";

		for (Author author : authors)
			str += author.toString() + "\n";
		
		return str;
	}
}
