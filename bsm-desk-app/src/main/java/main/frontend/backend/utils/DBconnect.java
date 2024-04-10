package main.frontend.backend.utils;

import java.sql.*;

public class DBconnect implements AutoCloseable {
	private static final String DRIVER = "org.postgresql.Driver";
//	private static final String URL = "jdbc:postgresql:database";
	private static final String URL = "jdbc:postgresql://localhost:5432/";
	private static final String USER = "postgres";
	private static final String PASSWORD = "123456";

	private static Connection connection;

	// Constructor for initializing the connection
	public DBconnect() {
		try {
			Class.forName(DRIVER);
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Method to execute a query and retrieve a ResultSet
	public ResultSet view(String object, String condition) {
		try (Statement st = connection.createStatement()) {
			ResultSet rs;
			String query = "SELECT * FROM " + object;
			if (condition != null)
				query += " WHERE " + condition;
			System.out.println(query);
			rs = st.executeQuery(query);
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean add(String object, String value) {
		try (Statement st = connection.createStatement();) {
			String query = "INSERT INTO " + object + " VALUES (" + value + ")";
			st.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public boolean update(String object, String value, String condition) {
		try (Statement st = connection.createStatement();) {
			String query = "UPDATE " + object + " SET " + value + " WHERE " + condition;
			st.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public boolean changeStatus(String object, String condition, boolean status) {
		String query = "UPDATE " + object + " SET Enabled = ?" + " WHERE " + condition;
		try (PreparedStatement st = connection.prepareStatement(query);) {
			st.setBoolean(0, status);
			st.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public void close() throws SQLException {
		if (connection != null) {
			connection.close();
		}
	}

	public Connection getConnection() {
		return connection;
	}
}
