package main.backend.list;

import java.util.ArrayList;

import main.backend.objects.Publisher;

public class PublisherList {
	private ArrayList<Publisher> publishers;
	
	public PublisherList() { publishers = new ArrayList<>(); }
	public PublisherList(PublisherList other) { publishers = new ArrayList<>(other.publishers); }

	public void addPublisher(Publisher publisher) { publishers.add(publisher); }
	
	public Publisher getPublisherByID(int id) {
		for (Publisher publisher : publishers)
			if (publisher.getId() == id) return publisher;
		return null;
	}

	@Override
	public String toString() {
		String str = "There are " + publishers.size() + " publishers in the list.\n\n";

		for (Publisher publisher : publishers)
			str += publisher.toString() + "\n";
		
		return str;
	}
}
