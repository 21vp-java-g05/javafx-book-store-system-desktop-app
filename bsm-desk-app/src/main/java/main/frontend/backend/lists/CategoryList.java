package main.frontend.backend.lists;

import main.frontend.backend.objects.Category;
import main.frontend.backend.utils.DBconnect;

import java.sql.ResultSet;
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
		String condition = name == null ? null : ("WHERE Name = " + name);
		try (
			DBconnect db = new DBconnect();
			ResultSet rs = db.view("AUTHOR", condition);
		) {
			categories.clear();
			while (rs.next())
				categories.add(new Category(rs.getInt(0), rs.getString(1), rs.getString(2)));
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();

		for (Category category : categories)
			str.append(category.toString()).append("\n");
		
		return str.toString();
	}
}
