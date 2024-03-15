package main.backend.orders;

import java.util.ArrayList;
import java.util.Date;

import main.backend.objects.BookList;
import main.backend.users.Employee;

public class Order {
	private String id;
	private Date OrderTime;
	private Employee employee;
	private float SalesPrice;
	private BookList books;
	private ArrayList<Integer> quantity;
	private ArrayList<Float> ImportPrice;
	private CustomerList customers;
}
