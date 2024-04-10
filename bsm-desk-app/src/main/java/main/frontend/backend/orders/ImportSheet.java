package main.frontend.backend.orders;

import main.frontend.backend.users.Employee;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ImportSheet {
	private int id;
	private LocalDateTime importTime;
	private Employee employee;
	private float totalCost;
	private ArrayList<ImportItem> importItems;

	public ImportSheet() {
		importItems = new ArrayList<>();
	}

	public ImportSheet(int id, LocalDateTime importTime, Employee employee, float totalCost, ArrayList<ImportItem> importItems) {
		this.id = id;
		this.importTime = importTime;
		this.employee = employee;
		this.totalCost = totalCost;
		this.importItems = importItems;
	}

	public ImportSheet(ImportSheet other) {
		this(other.id, other.importTime, other.employee, other.totalCost, other.importItems);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getImportTime() {
		return importTime;
	}

	public void setImportTime(LocalDateTime importTime) {
		this.importTime = importTime;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public float getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(float totalCost) {
		this.totalCost = totalCost;
	}

	public ArrayList<ImportItem> getImportItems() {
		return importItems;
	}

	public void setImportItems(ArrayList<ImportItem> importItems) {
		this.importItems = importItems;
	}

	public void addItem(ImportItem item) {
		importItems.add(item);
	}

	public void removeItem(ImportItem item) {
		importItems.remove(item);
	}
}
