package main.frontend.backend.lists;

import main.frontend.backend.orders.Customer;

import java.util.ArrayList;

import main.frontend.backend.orders.OrderItem;
import main.frontend.backend.users.Employee;
import main.frontend.backend.utils.DBconnect;

import java.sql.*;
import java.time.LocalDate;

public class OrderList {
    public ArrayList<OrderItem> loadOrdersFromDatabase() {
        ArrayList<OrderItem> orders = new ArrayList<>();
        DBconnect db = new DBconnect();
        try (

                Statement st = db.getConnection().createStatement();
                ResultSet rs = st.executeQuery("SELECT o.id AS order_id, o.order_time, o.sale_price," +
                        "a.fullname AS employee_name, c.fullname AS customer_name " +
                        "FROM Orders o " +
                        "JOIN Account a ON o.employee = a.id " +
                        "JOIN Customer c ON o.customer = c.id")
        ) {
            while (rs.next()) {
                int orderId = rs.getInt("order_id");
                LocalDate orderDate = rs.getDate("order_time").toLocalDate();
                double totalAmount = rs.getDouble("sale_price");
                String employeeName = rs.getString("employee_name");
                String customerName = rs.getString("customer_name");

                // Create instances of Employee and Customer using the retrieved names
                Employee employee = new Employee();
                employee.setFullname(employeeName);

                Customer customer = new Customer();
                customer.setFullName(customerName);

                OrderItem order = new OrderItem(orderId, orderDate, totalAmount, employee, customer);
                order.setEmployee(employee); // Set the employee of the order
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
