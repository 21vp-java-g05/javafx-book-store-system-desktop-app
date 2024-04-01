package main.frontend.backend.utils;

import java.sql.*;

public class DBconnect {
	private static final String DRIVER = "org.postgresql.Driver";
	private static final String URL = "jdbc:postgresql:Book_store";
	private static final String USER = "postgres";
	private static final String PASSWORD = "03102003Minh";

	private static Connection connection;

	// Constructor for initializing the connection
	public DBconnect() {
		try {
			Class.forName(DRIVER);
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Connected");
		} catch (ClassNotFoundException e) {
			System.err.println("Class not found");
		} catch (SQLException e) {
			System.err.println("Connection fail");
		}
	}

	// Method to execute a query and retrieve a ResultSet
	public ResultSet view(String object, String condition) {
		String query = "SELECT * FROM " + object;
		if (condition != null) query += " WHERE " + condition;
		ResultSet rs = null;
		
		try {
			Statement st = connection.createStatement();
			rs = st.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rs;
	}
	public boolean add(String object, String value) {
		String query = "INSERT INTO " + object + " VALUES (" + value + ")";
		
		try {
			Statement st = connection.createStatement();
			st.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	public boolean update(String object, String value, String condition) {
		String query = "UPDATE " + object + " SET " + value + " WHERE " + condition;
		
		try {
			Statement st = connection.createStatement();
			st.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}
	public boolean changeStatus(String object, String condition, boolean status) {
		String query = "UPDATE " + object + " SET Enabled = ?" + " WHERE " + condition;
		
		try {
			PreparedStatement st = connection.prepareStatement(query);
			st.setBoolean(0, status);
			st.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	public Connection getConnection() {
		return connection;
	}
	public void close() {
		try {
			connection.close();
		} catch (SQLException e) {
			System.err.println("Error closing connection");
		}
	}
}
