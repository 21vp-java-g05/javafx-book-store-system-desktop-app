/*
 * Họ và tên: Nguyễn Trần Châu Minh
 * MSSV		: 21126030
 * Lớp		: 21VP
*/

import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

public class PublisherList {
	//Declare a list of publishers
	private ArrayList <Publisher> publishers;
	
	public PublisherList() {
		publishers = new ArrayList<>();
	}
	
	/*
	 * This function reads publisher information  from a file and put in publisher list.
	 * Returns true if the function successes, false otherwise 
	 */
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
	
	/*
	 * This function searches publisher in an publisher list by id and returns the publisher found.
	 */
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
