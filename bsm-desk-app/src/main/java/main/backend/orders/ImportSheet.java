package main.backend.orders;

import java.util.ArrayList;
import java.util.Date;

import main.backend.list.BookList;
import main.backend.users.Employee;

public class ImportSheet {
	private String id;
	private Date ImportTime;
	private Employee employee;
	private float TotalCost;
	private BookList books;
	private ArrayList<Integer> quantity;
	private ArrayList<Float> ImportPrice;

	public ImportSheet(String id, Date ImportTime, Employee employee, float TotalCost, BookList books, ArrayList<Integer> quantity, ArrayList<Float> ImportPrice) {
		this.id = id;
		this.ImportTime = ImportTime;
		this.employee = employee;
		this.TotalCost = TotalCost;
		this.books = books;
		this.quantity = quantity;
		this.ImportPrice = ImportPrice;
	}
	public ImportSheet(ImportSheet other) {
		this(other.id, other.ImportTime, other.employee, other.TotalCost, other.books, other.quantity, other.ImportPrice);
	}

	public String getId() { return id; }
	public Date getImportTime() { return ImportTime; }
	public Employee getEmployee() { return employee; }
	public float getTotalCost() { return TotalCost; }
	public BookList getBookList() { return books; }
	public ArrayList<Integer> getQuantity() { return quantity; }
	public ArrayList<Float> getImportPrice() { return ImportPrice; }
}
