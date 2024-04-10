package main.frontend.controller;

import java.time.LocalDate;

public class orderData {

    public orderData(int orderId, LocalDate orderDate, String employeeFullname, String customerFullName, double salePrice) {
        this.orderID = orderId;
        this.date = orderDate;
        this.employeeName = employeeFullname;
        this.customerName = customerFullName;
        this.total = salePrice;
    }

    public Integer getOrderID() {
        return orderID;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public Double getTotal() {
        return total;
    }
    private final Integer orderID;

    public LocalDate getDate() {
        return date;
    }

    private final LocalDate date;
    private final String employeeName;
    private final String customerName;
    private final Double total;

    public orderData(Integer orderID, LocalDate date, String employeeName, String customerName, Double total) {
        this.orderID = orderID;
        this.date = date;
        this.employeeName = employeeName;
        this.customerName = customerName;
        this.total = total;
    }

}
