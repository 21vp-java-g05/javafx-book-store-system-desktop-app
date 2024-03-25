package main.backend.objects;

import java.sql.*;

public class Author {
	private int id;
	private String name, description;
	private boolean enabled;
	
	// DB config
	// Change this accordingly
	private static final String DB_URL = "jdbc:mysql://localhost:3306/mydb";
    private static final String DB_USER = "username";
    private static final String DB_PASSWORD = "password";


	public Author(int id, String name, String description, boolean enabled) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.enabled = enabled;
	}
	public Author(int id, String name, String description) { this(id, name, description, true); }
	public Author(Author author) { this(author.id, author.name, author.description); }

	public int getId() { return id; }
	public String getAuthorName() { return name; }
	public String getAuthorDescription() { return description; }
	public boolean isEnabled() { return enabled; }

	public void changeInfo(int id, String name, String description, boolean enabled) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.enabled = enabled;
	}
	
	public static Author getAuthorById(int id) {
        Author author = null;
        try (Connection cn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = cn.prepareStatement("SELECT * FROM authors WHERE id = ?")) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                author = new Author(rs.getInt("id"), rs.getString("name"), rs.getString("description"), rs.getBoolean("enabled"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return author;
    }

	public static boolean createAuthor(Author author) {
        try (Connection cn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = cn.prepareStatement("INSERT INTO authors (name, description, enabled) VALUES (?, ?, ?)")) {
            stmt.setString(1, author.name);
            stmt.setString(2, author.description);
            stmt.setBoolean(3, author.enabled);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

	public static boolean updateAuthor(Author author) {
        try (Connection cn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement st = cn.prepareStatement("UPDATE authors SET name = ?, description = ?, enabled = ? WHERE id = ?")) {
            st.setString(1, author.name);
            st.setString(2, author.description);
            st.setBoolean(3, author.enabled);
            st.setInt(4, author.id);
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

	public static boolean deleteAuthor(int id) {
        try (Connection cn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement st = cn.prepareStatement("DELETE FROM authors WHERE id = ?")) {
            st.setInt(1, id);
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

	@Override
	public String toString() {
		String idStr = "\tID: " + String.valueOf(id) + "\n";
		String nameStr = "\tAuthor name: " + name + "\n";
		String disStr = "\tAuthor description: " + description + "\n";
		String stsStr = "\tStatus: " + (enabled ? "enable" : "disable") + "\n";
		
		return idStr + nameStr + disStr + stsStr;
	}
}
