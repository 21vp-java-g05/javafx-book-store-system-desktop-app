/*
 * Họ và tên: Nguyễn Trần Châu Minh
 * MSSV		: 21126030
 * Lớp		: 21VP
*/

import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

public class AuthorList {
	//Declare an ArrayList of authors
	private ArrayList<Author> authors;
	
	public AuthorList() {
		authors = new ArrayList<>();
	}
	
	/*
	 * This function reads authors information  from a file and put in author list.
	 * Returns true if the function successes, false otherwise 
	 */
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

	/*
	 * This function searches author in an author list by id and returns the author found.
	 */
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
