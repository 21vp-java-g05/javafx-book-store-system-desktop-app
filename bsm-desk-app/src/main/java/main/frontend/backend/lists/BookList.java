package main.frontend.backend.lists;

import main.frontend.backend.objects.Author;
import main.frontend.backend.objects.Book;
import main.frontend.backend.objects.Category;
import main.frontend.backend.objects.Publisher;
import main.frontend.backend.utils.DBconnect;
import main.frontend.controller.bookData;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Objects;

public class BookList {
	private final ArrayList<Book> books;
	
	public BookList() { books = new ArrayList<>(); }
	public BookList(BookList other) { books = new ArrayList<>(other.books); }

	public void clear() { books.clear(); }

	public ArrayList<Book> loadBooks_fromDatabase(String name) {
		String condition = name == null ? "" : ("WHERE name = '" + name + "'");
		ArrayList<Book> books = new ArrayList<>();
		try (
				DBconnect db = new DBconnect();
				Statement st = db.getConnection().createStatement();
				ResultSet rs = st.executeQuery("SELECT * FROM BOOK " + condition)
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
								"WHERE CATEGORY_BOOK.book_id = " + id)
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
						ResultSet rs2 = st2.executeQuery("SELECT * FROM PUBLISHER WHERE id = " + publisherID)
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
						ResultSet rs3 = st3.executeQuery("SELECT * FROM AUTHOR WHERE id = " + authorID)
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
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
		return books;
	}

	public boolean addBook(bookData book) {
		try (DBconnect db = new DBconnect();
			 Statement st = db.getConnection().createStatement();
			 ResultSet rs = st.executeQuery("SELECT ID FROM BOOK WHERE BOOK.id = " + book.getBookId())) {

			if (rs.next()) {
				return false; // Book ID already exists
			}

			int authorId, publisherId;

			boolean bookStatusEnabled = true; // Assume book status is enabled by default

			// Retrieve author ID or insert if not exists
			String checkAuthor = "SELECT ID, status FROM AUTHOR WHERE AUTHOR.name = ?";
			try (PreparedStatement st1 = db.getConnection().prepareStatement(checkAuthor)) {
				st1.setString(1, book.getAuthor());
				ResultSet rs1 = st1.executeQuery();
				if (rs1.next()) {
					authorId = rs1.getInt("id");
					boolean authorStatus = rs1.getBoolean("status");
					if (!authorStatus) {
						bookStatusEnabled = false; // Set book status as disabled if author is disabled
					}
				} else {
					// Insert new author
					String addAuthorSql = "INSERT INTO AUTHOR(name) VALUES(?)";
					try (PreparedStatement prepareAddAuthor = db.getConnection().prepareStatement(addAuthorSql, Statement.RETURN_GENERATED_KEYS)) {
						prepareAddAuthor.setString(1, book.getAuthor());
						prepareAddAuthor.executeUpdate();

						try (ResultSet generatedKeys = prepareAddAuthor.getGeneratedKeys()) {
							if (generatedKeys.next()) {
								authorId = generatedKeys.getInt(1); // Get the generated ID
							} else {
								return false;
							}
						}
					}
				}
			}

			// Retrieve publisher ID or insert if not exists
			String checkPublisher = "SELECT ID, status FROM PUBLISHER WHERE PUBLISHER.name = ?";
			try (PreparedStatement st2 = db.getConnection().prepareStatement(checkPublisher)) {
				st2.setString(1, book.getPublisher());
				ResultSet rs2 = st2.executeQuery();
				if (rs2.next()) {
					publisherId = rs2.getInt("id");
					boolean publisherStatus = rs2.getBoolean("status");
					if (!publisherStatus) {
						bookStatusEnabled = false; // Set book status as disabled if publisher is disabled
					}
				} else {
					// Insert new publisher
					String addPublisherSql = "INSERT INTO PUBLISHER(name) VALUES(?)";
					try (PreparedStatement prepareAddPublisher = db.getConnection().prepareStatement(addPublisherSql, Statement.RETURN_GENERATED_KEYS)) {
						prepareAddPublisher.setString(1, book.getPublisher());
						prepareAddPublisher.executeUpdate();

						try (ResultSet generatedKeys = prepareAddPublisher.getGeneratedKeys()) {
							if (generatedKeys.next()) {
								publisherId = generatedKeys.getInt(1); // Get the generated ID
							} else {
								return false;
							}
						}
					}
				}
			}

			// Retrieve or insert categories and their IDs
			String categories = book.getGenre();
			String[] categoryNames = categories.split(", ");
			ArrayList<Integer> categoriesIds = new ArrayList<>();

			for (String categoryName : categoryNames) {
				String checkCategory = "SELECT id FROM CATEGORY WHERE name = ?";
				try (PreparedStatement checkCategoryStatement = db.getConnection().prepareStatement(checkCategory)) {
					checkCategoryStatement.setString(1, categoryName);
					ResultSet rs3 = checkCategoryStatement.executeQuery();
					if (rs3.next()) {
						int categoryId = rs3.getInt("id");
						categoriesIds.add(categoryId);
					} else {
						String addCategorySql = "INSERT INTO CATEGORY (name) VALUES (?)";
						try (PreparedStatement addCategoryStatement = db.getConnection().prepareStatement(addCategorySql, Statement.RETURN_GENERATED_KEYS)) {
							addCategoryStatement.setString(1, categoryName);
							int affectedRows = addCategoryStatement.executeUpdate();
							if (affectedRows == 0) {
								return false;
							}
							try (ResultSet generatedKeys = addCategoryStatement.getGeneratedKeys()) {
								if (generatedKeys.next()) {
									int categoryId = generatedKeys.getInt(1); // Get the generated ID
									categoriesIds.add(categoryId);
								} else {
									return false;
								}
							}
						}
					}
				}
			}

			// Insert category-book associations
			String addCategoryBook = "INSERT INTO CATEGORY_BOOK (category_id, book_id) VALUES (?, ?)";
			try (PreparedStatement prepareStatement = db.getConnection().prepareStatement(addCategoryBook)) {
				for (Integer categoryId : categoriesIds) {
					prepareStatement.setInt(1, categoryId);
					prepareStatement.setInt(2, book.getBookId());
					prepareStatement.executeUpdate();
				}
			}

			// Insert the book
			String sql = "INSERT INTO BOOK (id, title, publisher, author, status) VALUES(?,?,?,?,?)";
			try (PreparedStatement prepare = db.getConnection().prepareStatement(sql)) {
				prepare.setInt(1, book.getBookId());
				prepare.setString(2, book.getTitle());
				prepare.setInt(3, publisherId);
				prepare.setInt(4, authorId);
				if(bookStatusEnabled)
					prepare.setBoolean(5, Objects.equals(book.getStatus(), "Enabled"));
				else
					prepare.setBoolean(5, false);
				prepare.executeUpdate();
			}

		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
		return true;
	}


	@Override
	public String toString() {
		StringBuilder str = new StringBuilder("There are " + books.size() + " books in the list.\n\n");

		for (Book book : books)
			str.append(book.toString()).append("\n");
		
		return str.toString();
	}

	public boolean updateBook(bookData book) {
		try (DBconnect db = new DBconnect();
			 Statement st = db.getConnection().createStatement();
			 ResultSet rs = st.executeQuery("SELECT ID FROM BOOK WHERE BOOK.id = " + book.getBookId())) {

			if (!rs.next()) {
				return false; // Book ID not found
			}

			int authorId, publisherId;
			boolean bookStatusEnabled = true;

			String checkAuthor = "SELECT ID, status FROM AUTHOR WHERE AUTHOR.name = ?";
			try (PreparedStatement st1 = db.getConnection().prepareStatement(checkAuthor)) {
				st1.setString(1, book.getAuthor());
				ResultSet rs1 = st1.executeQuery();
				if (rs1.next()) {
					authorId = rs1.getInt("id");
					boolean authorStatus = rs1.getBoolean("status");
					if (!authorStatus) {
						bookStatusEnabled = false; // Set book status as disabled if author is disabled
					}
				} else {
					// Insert new author
					String addAuthorSql = "INSERT INTO AUTHOR(name) VALUES(?)";
					try (PreparedStatement prepareAddAuthor = db.getConnection().prepareStatement(addAuthorSql, Statement.RETURN_GENERATED_KEYS)) {
						prepareAddAuthor.setString(1, book.getAuthor());
						prepareAddAuthor.executeUpdate();

						try (ResultSet generatedKeys = prepareAddAuthor.getGeneratedKeys()) {
							if (generatedKeys.next()) {
								authorId = generatedKeys.getInt(1); // Get the generated ID
							} else {
								return false;
							}
						}
					}
				}
			}

			String checkPublisher = "SELECT ID, status FROM PUBLISHER WHERE PUBLISHER.name = ?";
			try (PreparedStatement st2 = db.getConnection().prepareStatement(checkPublisher)) {
				st2.setString(1, book.getPublisher());
				ResultSet rs2 = st2.executeQuery();
				if (rs2.next()) {
					publisherId = rs2.getInt("id");
					boolean publisherStatus = rs2.getBoolean("status");
					if (!publisherStatus) {
						bookStatusEnabled = false; // Set book status as disabled if publisher is disabled
					}
				} else {
					// Handle adding new publisher
					String addPublisherSql = "INSERT INTO PUBLISHER(name) VALUES(?)";
					try (PreparedStatement prepareAddPublisher = db.getConnection().prepareStatement(addPublisherSql, Statement.RETURN_GENERATED_KEYS)) {
						prepareAddPublisher.setString(1, book.getPublisher());
						prepareAddPublisher.executeUpdate();

						try (ResultSet generatedKeys = prepareAddPublisher.getGeneratedKeys()) {
							if (generatedKeys.next()) {
								publisherId = generatedKeys.getInt(1); // Get the generated ID
							} else {
								return false;
							}
						}
					}
				}
			}

			// Clear existing relations in the categories_book table for the given book ID
			String clearCategoriesSql = "DELETE FROM CATEGORY_BOOK WHERE book_id = ?";
			try (PreparedStatement clearCategoriesStatement = db.getConnection().prepareStatement(clearCategoriesSql)) {
				clearCategoriesStatement.setInt(1, book.getBookId());
				clearCategoriesStatement.executeUpdate();
			}

			// Add new relations for the updated categories
			String[] categoryNames = book.getGenre().split(", ");
			for (String categoryName : categoryNames) {
				// Check if the category already exists in the categories table
				String checkCategorySql = "SELECT id FROM CATEGORY WHERE name = ?";
				try (PreparedStatement checkCategoryStatement = db.getConnection().prepareStatement(checkCategorySql)) {
					checkCategoryStatement.setString(1, categoryName);
					ResultSet rsCategory = checkCategoryStatement.executeQuery();
					if (rsCategory.next()) {
						int categoryId = rsCategory.getInt("id");
						// Add a new relation to the categories_book table
						String addCategoryBookSql = "INSERT INTO CATEGORY_BOOK (category_id, book_id) VALUES (?, ?)";
						try (PreparedStatement addCategoryBookStatement = db.getConnection().prepareStatement(addCategoryBookSql)) {
							addCategoryBookStatement.setInt(1, categoryId);
							addCategoryBookStatement.setInt(2, book.getBookId());
							addCategoryBookStatement.executeUpdate();
						}
					} else {
						// Add the new category to the categories table
						String addCategorySql = "INSERT INTO CATEGORY (name) VALUES (?)";
						try (PreparedStatement addCategoryStatement = db.getConnection().prepareStatement(addCategorySql, Statement.RETURN_GENERATED_KEYS)) {
							addCategoryStatement.setString(1, categoryName);
							addCategoryStatement.executeUpdate();

							// Retrieve the generated category ID
							try (ResultSet generatedKeys = addCategoryStatement.getGeneratedKeys()) {
								if (generatedKeys.next()) {
									int categoryId = generatedKeys.getInt(1);
									// Add a new relation to the categories_book table
									String addCategoryBookSql = "INSERT INTO CATEGORY_BOOK (category_id, book_id) VALUES (?, ?)";
									try (PreparedStatement addCategoryBookStatement = db.getConnection().prepareStatement(addCategoryBookSql)) {
										addCategoryBookStatement.setInt(1, categoryId);
										addCategoryBookStatement.setInt(2, book.getBookId());
										addCategoryBookStatement.executeUpdate();
									}
								} else {
									return false;
								}
							}
						}
					}
				}
			}


			// Update other details of the book
			String sql = "UPDATE BOOK SET title = ?, publisher = ?, author = ?, status = ? WHERE id = ?";
			try (PreparedStatement prepare = db.getConnection().prepareStatement(sql)) {
				prepare.setString(1, book.getTitle());
				prepare.setInt(2, publisherId);
				prepare.setInt(3, authorId);
				if (bookStatusEnabled)
					prepare.setBoolean(4, Objects.equals(book.getStatus(), "Enabled"));
				else
					prepare.setBoolean(4, false);
				prepare.setInt(5, book.getBookId());
				prepare.executeUpdate();
			}

		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
		return true;
	}
}
