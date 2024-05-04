package main.frontend.backend.lists;

import java.util.ArrayList;

import main.frontend.backend.orders.OrderBook;
import main.frontend.backend.utils.DBconnect;

import java.sql.*;

public class OrderBooksList {
    public ArrayList<OrderBook> loadOrderBooksFromDatabase() {
        ArrayList<OrderBook> orders = new ArrayList<>();
        DBconnect db = new DBconnect();
        try (
                Statement st = db.getConnection().createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM orders_book JOIN book ON orders_book.book_id = book.id")
        ) {
            while (rs.next()) {
                int orderId = rs.getInt("orders_id");
                int bookId = rs.getInt("book_id");
                String bookName = rs.getString("title");
                int quantity = rs.getInt("quantity");
                double price = rs.getDouble("price");
                OrderBook order = new OrderBook(orderId, bookId, quantity, price, bookName);
                orders.add(order);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            db.close();
        }
        return orders;
    }

}
