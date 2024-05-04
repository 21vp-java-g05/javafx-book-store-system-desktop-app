package main.frontend.backend.lists;

import javafx.scene.control.Alert;
import main.frontend.backend.objects.Author;
import main.frontend.backend.objects.Book;
import main.frontend.backend.objects.Category;
import main.frontend.backend.objects.Publisher;
import main.frontend.backend.utils.DBconnect;
import main.frontend.controller.bookData;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.HashMap;

public class BookList {
	private final ArrayList<Book> books;
	
	public BookList() { books = new ArrayList<>(); }
	public BookList(BookList other) { books = new ArrayList<>(other.books); }

	public Book getBook_byId(int id) {
		for (Book book : books)
			if (book.getId() == id) return book;
		return null;
	}
    public static Book getPurchaseBookInfo(int i) {
		DBconnect db = new DBconnect();
		try (
				
				Statement st = db.getConnection().createStatement();
				ResultSet rs = st.executeQuery("SELECT * FROM BOOK WHERE id = " + i)
		) {
			if(rs.next()){
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
                return new Book(i, title, isbn, language, numberOfPages, pl.getPublisherByID(publisherID), al.getAuthorByID(authorID), cl, enabled);
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			db.close();
		}
        return null;
    }

	public static ArrayList<String> getAllPurchaseBookId() {
		ArrayList<String> listData = new ArrayList<>();
		DBconnect db = new DBconnect();
		try (
				Statement st = db.getConnection().createStatement();
				ResultSet rs = st.executeQuery("SELECT * FROM BOOK")
		) {
			while (rs.next()) {
				listData.add(rs.getString("id"));
			}
			return listData;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			db.close();
		}
	}

        public void clear() { books.clear(); }

	public ArrayList<Book> loadBooks_fromDatabase() {
		ArrayList<Book> books = new ArrayList<>();
		DBconnect db = new DBconnect();
		try (

				Statement st = db.getConnection().createStatement();
				ResultSet rs = st.executeQuery("SELECT * FROM BOOK")
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
		} finally {
			db.close();
		}
		return books;
	}

	public boolean addBook(bookData book) {
		LocalDate today = LocalDate.now();
		Date currentDate = Date.valueOf(today);
		DBconnect db = new DBconnect();
		try (
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
			String sql = "INSERT INTO BOOK (id, title, publisher, author, status, date) VALUES(?,?,?,?,?,?)";
			try (PreparedStatement prepare = db.getConnection().prepareStatement(sql)) {
				prepare.setInt(1, book.getBookId());
				prepare.setString(2, book.getTitle());
				prepare.setInt(3, publisherId);
				prepare.setInt(4, authorId);
				if(bookStatusEnabled)
					prepare.setBoolean(5, Objects.equals(book.getStatus(), "Enabled"));
				else
					prepare.setBoolean(5, false);
				prepare.setDate(6, currentDate);
				prepare.executeUpdate();
			}

		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		} finally {
			db.close();
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
		DBconnect db = new DBconnect();
		try (
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
		} finally {
			db.close();
		}
		return true;
	}
	public ArrayList<Book> loadNewBooks_fromDatabase() {
		ArrayList<Book> books = new ArrayList<>();
		DBconnect db = new DBconnect();
		try (
				Statement st = db.getConnection().createStatement();
				ResultSet rs = st.executeQuery("SELECT * FROM BOOK WHERE date_part('month', date) = date_part('month', current_date)")
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

    public ArrayList<Book> loadOosBooks_fromDatabase(Object o) {
		ArrayList<Book> books = new ArrayList<>();
		DBconnect db = new DBconnect();
		try (
				Statement st = db.getConnection().createStatement();
				ResultSet rs = st.executeQuery("SELECT * FROM BOOK b\n" +
						"LEFT JOIN imports_book ib ON b.id = ib.book_id\n" +
						"WHERE ib.remaining IS NULL OR ib.remaining = 0\n" +
						"GROUP BY b.id, ib.imports_id, ib.book_id\n" +  // Add ib.imports_id to GROUP BY
						"HAVING SUM(ib.remaining) = 0;");
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
		} finally {
			db.close();
		}
		return books;
    }
	private static Map<String, Object> resultSetToMap(ResultSet resultSet, int bought) throws SQLException {
		ResultSetMetaData metaData = resultSet.getMetaData();
		int columnCount = metaData.getColumnCount();
		Map<String, Object> rowMap = new HashMap<>();

		for (int i = 1; i <= columnCount; i++) {
			String columnName = metaData.getColumnLabel(i);
			Object columnValue = resultSet.getObject(i);
			rowMap.put(columnName, columnValue);
		}
		rowMap.put("bought",bought);
		return rowMap;
	}
	public static List<Map<String, Object>> isBookAvailable(int id, int quantity) {
		String sql = "SELECT * FROM imports_book WHERE book_id = ?";
		Alert alert;
		alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Error message");
		alert.setHeaderText(null);
		DBconnect db = new DBconnect();
		try(
			PreparedStatement statement = db.getConnection().prepareStatement(sql)) {
			statement.setInt(1, id);

			ResultSet resultSet = statement.executeQuery();

			int remainingSum = 0;
			List<Map<String, Object>> bookData = new ArrayList<>();
			while (resultSet.next() && remainingSum < quantity) {
				int remaining = resultSet.getInt("remaining");
				Double price = resultSet.getDouble("import_price");
				int import_id = resultSet.getInt("imports_id");
				if(quantity >= remainingSum + remaining){
					remainingSum += remaining;
					bookData.add(resultSetToMap(resultSet, remaining));
				}
				else {
					bookData.add(resultSetToMap(resultSet, quantity - remainingSum));
					remainingSum += (quantity - remainingSum);
				}
			}

			if (remainingSum >= quantity) {
				int updatedQuantity = 0;
				try (PreparedStatement updateStatement = db.getConnection().prepareStatement("UPDATE imports_book SET remaining = ? WHERE book_id = ? AND imports_id = ?")) {
					for (Map<String, Object> bookD : bookData) {
						int remaining = (int) bookD.get("remaining");
						int bought = (int) bookD.get("bought");
						int quantityToUpdate = remaining - bought;
						updateStatement.setInt(1, quantityToUpdate);
						updateStatement.setInt(2, id);
						updateStatement.setInt(3, (int) bookD.get("imports_id")); // Set import_id
						updateStatement.executeUpdate();
						updatedQuantity += quantityToUpdate;
						if (updatedQuantity >= quantity) {
							break;
						}
					}
				} catch (SQLException e) {
					// Handle update errors
				}
			} else {
				alert.setContentText("The quantity of this book is insufficient");
				alert.showAndWait();
				return null;
			}
			return bookData;
		} catch (SQLException e) {
			// Handle any database errors
			e.printStackTrace();
			alert.setContentText("An error occurred while checking book availability");
			alert.showAndWait();
			return null;
		} finally {
			db.close();
		}
	}

}
