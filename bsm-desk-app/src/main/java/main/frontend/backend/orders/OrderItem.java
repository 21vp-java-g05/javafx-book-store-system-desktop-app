package main.frontend.backend.orders;

import main.frontend.backend.users.Employee;
import main.frontend.backend.orders.Customer;

import java.time.LocalDate;

public class OrderItem {
    private int orderId;
    private LocalDate orderDate;
    private double salePrice;
    private Employee employee;
    private Customer customer;

    public OrderItem(int orderId, LocalDate orderDate, double salePrice, Employee employee, Customer customer) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.salePrice = salePrice;
        this.employee = employee;
        this.customer = customer;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
