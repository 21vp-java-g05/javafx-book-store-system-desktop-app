package main.frontend.backend.lists;

import main.frontend.backend.objects.Category;
import main.frontend.backend.utils.DBconnect;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CategoryList {
	private ArrayList<Category> categories;

	public CategoryList() {
		categories = new ArrayList<>();
	}

	public CategoryList(CategoryList other) {
		categories = new ArrayList<>(other.categories);
	}

	public void add(Category category) {
		categories.add(category);
	}

	public void clear() {
		categories.clear();
	}

	public Category getCategoryByID(int id) {
		for (Category category : categories)
			if (category.getId() == id) return category;
		return null;
	}
	public Category getCategory_byName(String name) {
		for (Category category : categories)
			if (category.getName().compareTo(name) == 0) return category;
		return null;
	}

	public ArrayList<Category> loadCategories_fromDatabase(String name) {
		String condition = (name == null || name.isEmpty()) ? "" : "WHERE Name = '" + name + "'";
		DBconnect db = new DBconnect();
		try (
			 Statement st = db.getConnection().createStatement();
			 ResultSet rs = st.executeQuery("SELECT * FROM CATEGORY " + condition)) {
			while (rs.next()) {
				Category category = new Category(rs.getInt("id"), rs.getString("name"), rs.getBoolean("status"));
				categories.add(category);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.close();
		}
		return categories;
	}

	public boolean addCategory(Category category) {
		DBconnect db = new DBconnect();
		try {
			// Check if the category ID already exists
			String checkCategoryIdQuery = "SELECT id FROM CATEGORY WHERE id = ?";
			try (PreparedStatement checkIdStatement = db.getConnection().prepareStatement(checkCategoryIdQuery)) {
				checkIdStatement.setInt(1, category.getId());
				ResultSet idResultSet = checkIdStatement.executeQuery();
				if (idResultSet.next()) {
					// Category ID already exists, return false
					return false;
				}
			}

			// Insert the new category
			String insertCategoryQuery = "INSERT INTO CATEGORY (id, name, status) VALUES (?, ?, ?)";
			try (PreparedStatement insertStatement = db.getConnection().prepareStatement(insertCategoryQuery)) {
				insertStatement.setInt(1, category.getId());
				insertStatement.setString(2, category.getName());
				insertStatement.setBoolean(3, category.isEnabled());
				int rowsAffected = insertStatement.executeUpdate();
				return rowsAffected > 0;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			return false;
		} finally {
			db.close();
		}
	}

	public boolean updateCategory(Category update) {
		DBconnect db = new DBconnect();
		try (
			 PreparedStatement statement = db.getConnection().prepareStatement("UPDATE CATEGORY SET name = ?, status = ? WHERE id = ?")) {
			statement.setString(1, update.getName());
			statement.setBoolean(2, update.isEnabled());
			statement.setInt(3, update.getId());
			int rowsAffected = statement.executeUpdate();

			if (rowsAffected > 0 && !update.isEnabled()) {
				// If the category is disabled, update the status of all books with this category ID to disabled
				updateBooksStatusByCategory(update.getId());
			}

			return rowsAffected > 0;
		} catch (SQLException ex) {
			ex.printStackTrace();
			return false;
		} finally {
			db.close();
		}
	}

	private void updateBooksStatusByCategory(int categoryId) {
		DBconnect db = new DBconnect();
		try (
			 PreparedStatement statement = db.getConnection().prepareStatement("UPDATE BOOK SET status = ? WHERE id IN (SELECT book_id FROM CATEGORY_BOOK WHERE category_id = ?)")) {
			statement.setBoolean(1, false);
			statement.setInt(2, categoryId);
			statement.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			db.close();
		}
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		for (Category category : categories)
			str.append(category.toString()).append("\n");

		return str.toString();
	}
}
