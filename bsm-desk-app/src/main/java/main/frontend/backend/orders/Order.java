package main.frontend.backend.orders;

import main.frontend.backend.lists.BookList;
import main.frontend.backend.users.Employee;
import main.frontend.backend.utils.DBconnect;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Order {
	private int id;
	private Date OrderTime;
	private Employee employee;
	private float SalesPrice;
	private BookList books;
	private ArrayList<Integer> quantity;
	private ArrayList<Float> ImportPrice;
	private Customer customer;

	public Order() {}
	public Order(int id, Date OrderTime, Employee employee, float SalesPrice, BookList books, ArrayList<Integer> quantity, ArrayList<Float> ImportPrice, Customer customer) {
		this.id = id;
		this.OrderTime = OrderTime;
		this.employee = employee;
		this.SalesPrice = SalesPrice;
		this.books = books;
		this.quantity = quantity;
		this.ImportPrice = ImportPrice;
		this.customer = customer;
	}
	public Order(Order other) {
		this(other.id, other.OrderTime, other.employee, other.SalesPrice, other.books, other.quantity, other.ImportPrice, other.customer);
	}

	public static void addOrder(int employeeID, int currentCustomer, float totalP, List<Map<String, Object>> orderData) {
		try (DBconnect db = new DBconnect();
		PreparedStatement insertOrderStatement = db.getConnection().prepareStatement(
				"INSERT INTO orders (order_time, employee, customer, sale_price) VALUES (?, ?, ?, ?)",
				Statement.RETURN_GENERATED_KEYS
		);) {
			// Insert into orders table
			System.out.println("Customer: "+ currentCustomer);;
			insertOrderStatement.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
			insertOrderStatement.setInt(2, employeeID); // Replace with actual employee ID
			insertOrderStatement.setInt(3, currentCustomer); // Replace with actual customer ID
			insertOrderStatement.setFloat(4, totalP);

			insertOrderStatement.executeUpdate();

			ResultSet generatedKeys = insertOrderStatement.getGeneratedKeys();
			generatedKeys.next();
			int orderId = generatedKeys.getInt(1);

			Map<String, Map<String, Object>> cleanedData = new HashMap<>();

			// Iterate through orderData to group rows by book_id and import_price
			for (Map<String, Object> bookData : orderData) {
				int bookId = (int) bookData.get("book_id");
				float importPrice = (float) bookData.get("import_price");
				String key = bookId + ":" + importPrice;

				// Update or initialize the grouped data
				if (cleanedData.containsKey(key)) {
					Map<String, Object> existingData = cleanedData.get(key);
					int existingBought = (int) existingData.get("bought");
					int currentBought = (int) bookData.get("bought");
					existingData.put("bought", existingBought + currentBought);
				} else {
					cleanedData.put(key, new HashMap<>(bookData)); // Use a copy of bookData
				}
			}

			// Convert the cleanedData map back to a list
			orderData = new ArrayList<>(cleanedData.values());

			// Insert into orders_book table
			try (PreparedStatement insertOrderBookStatement = db.getConnection().prepareStatement("INSERT INTO orders_book (orders_id, book_id, quantity, price) VALUES (?, ?, ?, ?)")) {
				for (Map<String, Object> bookData : orderData) {
					insertOrderBookStatement.setInt(1, orderId);
					insertOrderBookStatement.setInt(2, (int) bookData.get("book_id"));
					insertOrderBookStatement.setInt(3, (int) bookData.get("bought"));
					insertOrderBookStatement.setFloat(4, (float) bookData.get("import_price"));
					insertOrderBookStatement.executeUpdate();
				}
			}
		} catch (SQLException e) {
			// Handle database errors
			// ...
		}
	}

	public int getId() { return id; }
	public Date getOrderTime() { return OrderTime; }
	public Employee getEmployee() { return employee; }
	public float getSalesPrice() { return SalesPrice; }
	public BookList getBooks() { return books; }
	public ArrayList<Integer> getQuantity() { return quantity; }
	public ArrayList<Float> getImportPrice() { return ImportPrice; }
	public Customer getCustomer() { return customer; }

	public void changeInfo(int id, Date OrderTime, Employee employee, float SalesPrice, BookList books, ArrayList<Integer> quantity, ArrayList<Float> ImportPrice, Customer customer) {
		this.id = id;
		this.OrderTime = OrderTime;
		this.employee = employee;
		this.SalesPrice = SalesPrice;
		this.books = books;
		this.quantity = quantity;
		this.ImportPrice = ImportPrice;
		this.customer = customer;
	}
}
