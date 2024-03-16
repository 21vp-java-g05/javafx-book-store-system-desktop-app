package main.backend.list;

import java.util.ArrayList;

import main.backend.objects.Category;

public class CategoryList {
	private ArrayList<Category> categories;
	
	public CategoryList() {
		categories = new ArrayList<Category>();
	}
	public CategoryList(CategoryList other) {
		categories = new ArrayList<>(other.categories);
	}

	public void addCategory(Category category) {
		categories.add(category);
	}
	
	public Category getCategoryByID(String id) {
		for (Category category : categories)
			if (category.getId().equals(id))
				return category;
		return null;
	}

	@Override
	public String toString() {
		String str = "There are " + categories.size() + " publishers in the list.\n\n";

		for (Category category : categories)
			str += category.toString() + "\n";
		
		return str;
	}
}
