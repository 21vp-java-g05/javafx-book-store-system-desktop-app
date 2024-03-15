package main.backend.orders;

import java.util.ArrayList;
import java.util.Date;

import main.backend.objects.BookList;
import main.backend.users.Employee;

public class ImportSheet {
	private String id;
	private Date ImportTime;
	private Employee employee;
	private float TotalCost;
	private BookList books;
	private ArrayList<Integer> quantity;
	private ArrayList<Float> ImportPrice;
}
