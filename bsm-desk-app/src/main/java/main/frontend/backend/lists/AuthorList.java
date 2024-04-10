package main.frontend.backend.lists;

import main.frontend.backend.objects.Author;
import main.frontend.backend.utils.DBconnect;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class AuthorList {
	private ArrayList<Author> authors;

	public AuthorList() {
		authors = new ArrayList<>();
	}

	public AuthorList(AuthorList other) {
		authors = new ArrayList<>(other.authors);
	}

	public void add(Author author) {
		authors.add(author);
	}

	public void clear() {
		authors.clear();
	}

	public Author getAuthorByID(int id) {
		for (Author author : authors)
			if (author.getId() == id) return author;
		return null;
	}

	public ArrayList<Author> loadAuthors_fromDatabase(String name) {
		String condition = (name == null || name.isEmpty()) ? "" : "WHERE Name = '" + name + "'";
		try (DBconnect db = new DBconnect();
			 Statement st = db.getConnection().createStatement();
			 ResultSet rs = st.executeQuery("SELECT * FROM AUTHOR " + condition)) {
			while (rs.next()) {
				Author author = new Author(rs.getInt("id"), rs.getString("name"), rs.getBoolean("status"));
				authors.add(author);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return authors;
	}


	public boolean addAuthor(Author author) {
		try (DBconnect db = new DBconnect()) {
			// Check if the author ID already exists
			String checkAuthorIdQuery = "SELECT id FROM AUTHOR WHERE id = ?";
			try (PreparedStatement checkIdStatement = db.getConnection().prepareStatement(checkAuthorIdQuery)) {
				checkIdStatement.setInt(1, author.getId());
				ResultSet idResultSet = checkIdStatement.executeQuery();
				if (idResultSet.next()) {
					// Author ID already exists, return false
					return false;
				}
			}

			// Insert the new author
			String insertAuthorQuery = "INSERT INTO AUTHOR (id, name, status) VALUES (?, ?, ?)";
			try (PreparedStatement insertStatement = db.getConnection().prepareStatement(insertAuthorQuery)) {
				insertStatement.setInt(1, author.getId());
				insertStatement.setString(2, author.getName());
				insertStatement.setBoolean(3, author.isEnabled());
				int rowsAffected = insertStatement.executeUpdate();
				return rowsAffected > 0;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			return false;
		}
	}

	public boolean updateAuthor(Author update) {
		try (DBconnect db = new DBconnect();
			 PreparedStatement statement = db.getConnection().prepareStatement("UPDATE AUTHOR SET name = ?, status = ? WHERE id = ?")) {
			statement.setString(1, update.getName());
			statement.setBoolean(2, update.isEnabled());
			statement.setInt(3, update.getId());
			int rowsAffected = statement.executeUpdate();

			if (rowsAffected > 0 && !update.isEnabled()) {
				// If the author is disabled, update the status of all books with this author ID to disabled
				updateBooksStatusByAuthor(update.getId());
			}

			return rowsAffected > 0;
		} catch (SQLException ex) {
			ex.printStackTrace();
			return false;
		}
	}

	private void updateBooksStatusByAuthor(int authorId) {
		try (DBconnect db = new DBconnect();
			 PreparedStatement statement = db.getConnection().prepareStatement("UPDATE BOOK SET status = ? WHERE author = ?")) {
			statement.setBoolean(1, false);
			statement.setInt(2, authorId);
			statement.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

}
