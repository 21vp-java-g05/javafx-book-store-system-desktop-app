package main.backend.lists;

import java.sql.ResultSet;
import java.util.ArrayList;

import main.backend.objects.Publisher;
import main.backend.utils.DBconnect;

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

	public boolean loadPublishers_fromDatabase(String name) {
		String condition = name == null ? null : ("WHERE Name = " + name);
		try (
			DBconnect db = new DBconnect();
			ResultSet rs = db.view("AUTHOR", condition);
		) {
			publishers.clear();
			while (rs.next())
				publishers.add(new Publisher(rs.getInt(0), rs.getString(1), rs.getString(2)));
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	@Override
	public String toString() {
		String str = "There are " + publishers.size() + " publishers in the list.\n\n";

		for (Publisher publisher : publishers)
			str += publisher.toString() + "\n";
		
		return str;
	}
}
