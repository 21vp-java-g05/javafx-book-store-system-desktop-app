/*
 * Họ và tên: Nguyễn Trần Châu Minh
 * MSSV		: 21126030
 * Lớp		: 21VP
*/

// PLEASE DO NOT CHANGE THE CONTENT OF THE CLASS 

public class MainProgram {
	
	public static void main(String[] args) {
		AuthorList authors = new AuthorList();
		authors.loadAuthorsFromFile("author_data.txt");
		System.out.println("========Author list:===========");
		System.out.println(authors);
		
		PublisherList publishers = new PublisherList();
		publishers.loadPublisherFromFile("publisher_data.txt");
		System.out.println("========Publisher list:========");
		System.out.println(publishers);
		
		BookList books = new BookList();
		books.loadBooksFromFile("book_data.txt", publishers, authors);
		System.out.println("========Book list:=============");
		System.out.println(books);
		books.writeBooksToFile("output.txt");
	}
}
