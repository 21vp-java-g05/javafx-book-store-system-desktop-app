package main.frontend.controller;

public class orderBooksData {

    private final String bookName;
    private final Integer orderID;
    private final Integer bookID;
    private final Integer quantity;
    private final Double price;

    public orderBooksData(String bookName, Integer orderID, Integer bookID, Integer quantity, Double price) {
        this.bookName = bookName;
        this.orderID = orderID;
        this.bookID = bookID;
        this.quantity = quantity;
        this.price = price;
    }

    public Integer getOrderID() {
        return orderID;
    }

    public Integer getBookID() {
        return bookID;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Double getPrice() {
        return price;
    }
    public String getBookName() {
        return bookName;
    }
}
