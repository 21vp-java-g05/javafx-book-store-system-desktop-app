package main.backend.objects;

import java.io.FileInputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

public class BookList {
	private ArrayList<Book> books;
	
	public BookList() {
		books = new ArrayList<>();
	}
	
	public boolean loadBooksFromFile(String filename, PublisherList pl, AuthorList al) {
		try (Scanner scanner = new Scanner(new FileInputStream(filename), StandardCharsets.UTF_8)) {
			scanner.nextLine();
			while (scanner.hasNextLine()) {
				String[] parts = scanner.nextLine().split("\t");
				books.add(new Book(parts[0], parts[1], parts[2], pl.getPublisherByID(parts[3]), parts[4], Integer.parseInt(parts[5]), al.getAuthorByID(parts[6])));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public Publisher getPublisherById(PublisherList pl, String id) {
		return pl.getPublisherByID(id);
	}
	
	public Author getAuthorById(AuthorList al, String id) {
		return al.getAuthorByID(id);
	}
	
	public void writeBooksToFile(String filename) {
		try (PrintWriter writer = new PrintWriter(filename)) {
			writer.println("No.\tID\tISBN\tTitle\tPublisher\tLanguage\tNumber of pages\tAuthor");
			int i = 1;
			for (Book book : books)
				writer.println(i++ + "\t" + book.getStrToFile());
		} catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("Books are written to file output.txt");
	}

	@Override
	public String toString() {
		String str = "There are " + books.size() + " books in the list.\n\n";

		for (Book book : books)
			str += book.toString() + "\n";
		
		return str;
	}
	
}
