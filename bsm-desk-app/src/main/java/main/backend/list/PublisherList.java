package main.backend.list;

import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

import main.backend.objects.Publisher;

public class PublisherList {
	private ArrayList<Publisher> publishers;
	
	public PublisherList() {
		publishers = new ArrayList<>();
	}
	public PublisherList(PublisherList other) {
		publishers = new ArrayList<>(other.publishers);
	}

	public void addPublisher(Publisher publisher) {
		publishers.add(publisher);
	}
	
	public boolean loadPublisherFromFile(String filename) {
		try (Scanner scanner = new Scanner(new FileInputStream(filename), StandardCharsets.UTF_8)) {
			scanner.nextLine();
			while (scanner.hasNextLine()) {
				String[] parts = scanner.nextLine().split("\t");
				publishers.add(new Publisher(parts[0], parts[1], parts[2]));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public Publisher getPublisherByID(String id) {
		for (Publisher publisher : publishers)
			if (publisher.getId().equals(id))
				return publisher;
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
