package main.frontend.backend.lists;

import main.frontend.backend.orders.Customer;
import main.frontend.backend.utils.DBconnect;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Objects;

public class CustomerList {
    private ArrayList<Customer> customers;

    public CustomerList() {
        customers = new ArrayList<>();
    }

    public CustomerList(CustomerList other) {
        customers = new ArrayList<>(other.customers);
    }

    public static ArrayList<String> getAllPurchaseCustomerId() {
        ArrayList<String> listData = new ArrayList<>();
        try (
                DBconnect db = new DBconnect();
                Statement st = db.getConnection().createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM CUSTOMER")
        ) {
            while (rs.next()) {
                String id = rs.getString("id");
                String customerName = rs.getString("fullname");
                listData.add(id + ": " +customerName);
            }
            return listData;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void add(Customer customer) {
        customers.add(customer);
    }

    public void clear() {
        customers.clear();
    }

    public Customer getCustomerByID(int id) {
        for (Customer customer : customers) {
            if (customer.getId() == id) {
                return customer;
            }
        }
        return null;
    }

    public ArrayList<Customer> loadCustomersFromDatabase() { // Adjusted method name for consistency
        try (DBconnect db = new DBconnect();
             Statement st = db.getConnection().createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM CUSTOMER")) {
            while (rs.next()) {
                Customer customer = new Customer(
                        rs.getInt("id"),
                        rs.getString("mail"),
                        rs.getString("fullName"),
                        rs.getBoolean("gender") ? "Male": "Female",
                        rs.getBoolean("status")
                ); // Assuming Customer fields based on previous code
                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    public boolean addCustomer(Customer customer) {
        try (DBconnect db = new DBconnect()) {
            // Check if the customer ID already exists
            String checkCustomerIdQuery = "SELECT id FROM CUSTOMER WHERE id = ?";
            try (PreparedStatement checkIdStatement = db.getConnection().prepareStatement(checkCustomerIdQuery)) {
                checkIdStatement.setInt(1, customer.getId());
                ResultSet idResultSet = checkIdStatement.executeQuery();
                if (idResultSet.next()) {
                    return false;
                }
            }

            // Insert the new customer
            String insertCustomerQuery = "INSERT INTO CUSTOMER (id, fullName, mail, gender, status) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement insertStatement = db.getConnection().prepareStatement(insertCustomerQuery)) {
                insertStatement.setInt(1, customer.getId());
                insertStatement.setString(2, customer.getFullName());
                insertStatement.setString(3, customer.getEmail());
                insertStatement.setBoolean(4, Objects.equals(customer.getGender(), "Male"));
                insertStatement.setBoolean(5, customer.isEnabled());
                int rowsAffected = insertStatement.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean updateCustomer(Customer updatedCustomer) {
        try (DBconnect db = new DBconnect();
             PreparedStatement statement = db.getConnection().prepareStatement("UPDATE CUSTOMER SET fullName = ?, mail = ?, gender = ?, status = ? WHERE id = ?")) {
            statement.setString(1, updatedCustomer.getFullName());
            statement.setString(2, updatedCustomer.getEmail());
            statement.setBoolean(3, Objects.equals(updatedCustomer.getGender(), "Male"));
            statement.setBoolean(4, updatedCustomer.isEnabled());
            statement.setInt(5, updatedCustomer.getId());
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }


}
