package main.backend.list;

import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

import main.backend.objects.Author;

public class AuthorList {
	private ArrayList<Author> authors;
	
	public AuthorList() {
		authors = new ArrayList<>();
	}
	public AuthorList(AuthorList other) {
		authors = new ArrayList<>(other.authors);
	}

	public void addAuthor(Author author) {
		authors.add(author);
	}
	
	public boolean loadAuthorsFromFile(String filename) {
		try (Scanner scanner = new Scanner(new FileInputStream(filename), StandardCharsets.UTF_8)) {
			scanner.nextLine();
			while (scanner.hasNextLine()) {
				String[] parts = scanner.nextLine().split("\t");
				authors.add(new Author(parts[0], parts[1], parts[2]));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public Author getAuthorByID(String id) {
		for (Author author : authors)
			if (author.getId().equals(id))
				return author;
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
