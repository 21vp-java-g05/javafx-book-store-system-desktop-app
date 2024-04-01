package main.frontend.backend.lists;

import main.frontend.backend.objects.Category;
import main.frontend.backend.utils.DBconnect;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CategoryList {
	private ArrayList<Category> categories;
	
	public CategoryList() { categories = new ArrayList<Category>(); }
	public CategoryList(CategoryList other) { categories = new ArrayList<>(other.categories); }

	public void add(Category category) { categories.add(category); }
	public void clear() { categories.clear(); }
	public Category getCategoryByID(int id) {
		for (Category category : categories)
			if (category.getId() == id) return category;
		return null;
	}

	public boolean loadCategories_fromDatabase(String name) {
		String condition = name == null ? null : ("name LIKE '%" + name + "%'");
		DBconnect db = new DBconnect();
		categories = new ArrayList<Category>();
		
		try (ResultSet rs = db.view("CATEGORY", condition);) {
			while (rs.next())
				categories.add(new Category(rs.getInt("id"), rs.getString("name"), rs.getString("description"), rs.getBoolean("status")));
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally { db.close(); }

		return true;
	}

	public String getName() {
		String names = categories.get(0).getName();
		for (int i = 1; i < categories.size(); i++)
			names += ", " + categories.get(i).getName();
		return names;
	}
	@Override
	public String toString() {
		String str = "There are " + categories.size() + " categories in the list.\n\n";

		for (Category category : categories)
			str += category.toString() + "\n";
		
		return str.toString();
	}
}
