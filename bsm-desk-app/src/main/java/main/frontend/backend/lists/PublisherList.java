package main.frontend.backend.lists;

import main.frontend.backend.objects.Publisher;
import main.frontend.backend.utils.DBconnect;
import main.frontend.controller.publisherData;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Objects;

public class PublisherList {
	private ArrayList<Publisher> publishers;
	
	public PublisherList() { publishers = new ArrayList<>(); }
	public PublisherList(PublisherList other) { publishers = new ArrayList<>(other.publishers); }

	public void add(Publisher publisher) { publishers.add(publisher); }
	public void clear() { publishers.clear(); }
	public Publisher getPublisherByID(int id) {
		for (Publisher publisher : publishers)
			if (publisher.getId() == id) return publisher;
		return null;
	}

	public ArrayList<Publisher> loadPublishers_fromDatabase(String name) {
		String condition = (name == null || name.isEmpty()) ? "" : "WHERE Name = '" + name + "'";
		ArrayList<Publisher> publishers = new ArrayList<>();
		try (DBconnect db = new DBconnect();
			 Statement st = db.getConnection().createStatement();
			 ResultSet rs = st.executeQuery("SELECT * FROM PUBLISHER " + condition)) {
			while (rs.next()) {
				Publisher publisher = new Publisher(rs.getInt("id"), rs.getString("name"), rs.getString("description"), rs.getBoolean("status"));
				publishers.add(publisher);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return publishers;
	}

	public boolean addPublisher(publisherData publisher) {
		try (DBconnect db = new DBconnect()) {
			// Check if the publisher ID already exists
			String checkPublisherIdQuery = "SELECT id FROM PUBLISHER WHERE id = ?";
			try (PreparedStatement checkIdStatement = db.getConnection().prepareStatement(checkPublisherIdQuery)) {
				checkIdStatement.setInt(1, publisher.getPublisherId());
				ResultSet idResultSet = checkIdStatement.executeQuery();
				if (idResultSet.next()) {
					// Publisher ID already exists, return false
					return false;
				}
			}

			// Insert the new publisher
			String insertPublisherQuery = "INSERT INTO PUBLISHER (id, name, status) VALUES (?, ?, ?)";
			try (PreparedStatement insertStatement = db.getConnection().prepareStatement(insertPublisherQuery)) {
				insertStatement.setInt(1, publisher.getPublisherId());
				insertStatement.setString(2, publisher.getName());
				insertStatement.setBoolean(3, Objects.equals(publisher.getStatus(), "Enabled"));
				int rowsAffected = insertStatement.executeUpdate();
				return rowsAffected > 0;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			return false;
		}
	}


	@Override
	public String toString() {
		StringBuilder str = new StringBuilder("There are " + publishers.size() + " publishers in the list.\n\n");

		for (Publisher publisher : publishers)
			str.append(publisher.toString()).append("\n");
		
		return str.toString();
	}

	public boolean updatePublisher(publisherData update) {
		try (DBconnect db = new DBconnect();
			 PreparedStatement statement = db.getConnection().prepareStatement("UPDATE PUBLISHER SET name = ?, status = ? WHERE id = ?")) {
			statement.setString(1, update.getName());
			statement.setBoolean(2, Objects.equals(update.getStatus(), "Enabled"));
			statement.setInt(3, update.getPublisherId());
			int rowsAffected = statement.executeUpdate();
			if (rowsAffected > 0 && !Objects.equals(update.getStatus(), "Enabled")) {
				// Check if the status is set to disabled, then update status of all books with the same publisher id
				updateBooksStatusByPublisher(update.getPublisherId());
				return true;
			} else {
				return false;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			return false;
		}
    }
	public void updateBooksStatusByPublisher(int publisherId) {
		try (DBconnect db = new DBconnect();
			 PreparedStatement statement = db.getConnection().prepareStatement("UPDATE BOOK SET status = ? WHERE publisher = ?")) {
			statement.setBoolean(1, false);
			statement.setInt(2, publisherId);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
}
