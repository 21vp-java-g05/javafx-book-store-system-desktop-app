package main.backend.lists;

import java.sql.ResultSet;
import java.util.ArrayList;

import main.backend.objects.Category;
import main.backend.utils.DBconnect;

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
		String str = "There are " + categories.size() + " publishers in the list.\n\n";

		for (Category category : categories)
			str += category.toString() + "\n";
		
		return str;
	}
}
