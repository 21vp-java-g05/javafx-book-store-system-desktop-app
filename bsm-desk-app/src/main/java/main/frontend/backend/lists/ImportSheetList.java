package main.frontend.backend.lists;

import main.frontend.backend.orders.ImportBook;
import main.frontend.backend.orders.ImportItem;
import main.frontend.backend.orders.ImportSheet;
import main.frontend.backend.users.Employee;
import main.frontend.backend.utils.DBconnect;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ImportSheetList {
    private List<ImportSheet> importSheets;

    public ImportSheetList() {
        importSheets = new ArrayList<>();
    }

    public List<ImportSheet> loadImportSheetsFromDatabase() {
        List<ImportSheet> importSheets = new ArrayList<>();
        DBconnect db = new DBconnect();
        try (
             Statement statement = db.getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM imports JOIN account ON imports.employee = account.id")) {
            while (resultSet.next()) {
                // Retrieve data from the database
                int id = resultSet.getInt("id");
                Timestamp importTime = resultSet.getTimestamp("import_time");
                String employeeName = resultSet.getString("fullname");
                float totalCost = resultSet.getFloat("total_cost");

                // Create ImportSheet object and add it to the list
                ImportSheet importSheet = new ImportSheet();
                importSheet.setId(id);
                importSheet.setImportTime(importTime.toLocalDateTime()); // Convert Timestamp to LocalDateTime
                importSheet.setEmployee(new Employee(employeeName));
                importSheet.setTotalCost(totalCost);

                // Add the import sheet to the list
                importSheets.add(importSheet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
        return importSheets;
    }

    public void addImportSheet(ImportSheet importSheet) throws SQLException {
        float totalCost = calculateTotalCost(importSheet);

        // Add import sheet data to the database
        int importId = addImportSheetToDatabase(importSheet, totalCost);

        // Add each item in importSheet to the IMPORTS_BOOK table
        for (ImportItem item : importSheet.getImportItems()) {
            this.addImportItemToDatabase(importId, item);
        }

    }

    public boolean bookExistsInDatabase(int bookId) throws SQLException {
        DBconnect db = new DBconnect();
        try (Statement statement = db.getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM BOOK WHERE id = " + bookId)) {
            if (resultSet.next()) {
                return true;
            }
        } finally {
            db.close();
        }
        return false;
    }

    private float calculateTotalCost(ImportSheet importSheet) {
        float totalCost = 0;
        for (ImportItem item : importSheet.getImportItems()) {
            totalCost += item.getImportPrice() * item.getQuantity();
        }
        return totalCost;
    }

    public int addImportSheetToDatabase(ImportSheet importSheet, float totalCost) throws SQLException {
        int importId = -1; // Initialize importId to -1
        DBconnect db = new DBconnect();
        // Step 1: Connect to the database
        try {
            // Step 2: Insert data into the IMPORTS table
            String insertImportQuery = "INSERT INTO public.imports (import_time, employee, total_cost) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = db.getConnection().prepareStatement(insertImportQuery, PreparedStatement.RETURN_GENERATED_KEYS)) {
                // Set parameters for the prepared statement
                LocalDateTime currentDateTime = LocalDateTime.now();
                preparedStatement.setTimestamp(1, Timestamp.valueOf(currentDateTime));
                preparedStatement.setInt(2, 1);
                preparedStatement.setFloat(3, totalCost);

                // Execute the query
                int rowsAffected = preparedStatement.executeUpdate();

                // Check if the insertion was successful
                if (rowsAffected > 0) {
                    // Retrieve the auto-generated key (import_id)
                    try (var generatedKeys = preparedStatement.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            importId = generatedKeys.getInt(1);
                        }
                    }
                }
            }
        } finally {
            db.close();
        }
        // Return the importId
        return importId;
    }

    private void addImportItemToDatabase(int importId, ImportItem item) throws SQLException {
        String query = "INSERT INTO public.imports_book (imports_id, book_id, quantity, import_price, remaining) VALUES (?, ?, ?, ?, ?)";
        DBconnect db = new DBconnect();
        try (
             PreparedStatement preparedStatement = db.getConnection().prepareStatement(query);
             ) {
                preparedStatement.setInt(1, importId);
                preparedStatement.setInt(2, item.getBook().getId());
                preparedStatement.setInt(3, item.getQuantity());
                preparedStatement.setFloat(4, item.getImportPrice());
                preparedStatement.setInt(5, item.getQuantity());
                preparedStatement.executeUpdate();
        } finally {
            db.close();
        }
    }

    public List<ImportSheet> getImportSheets() {
        return importSheets;
    }

    public void setImportSheets(List<ImportSheet> importSheets) {
        this.importSheets = importSheets;
    }

    public List<ImportBook> loadImportBooksFromDatabase() {
        List<ImportBook> importBooks = new ArrayList<>();
        DBconnect db = new DBconnect();
        try (
             Statement statement = db.getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM imports_book JOIN book on imports_book.book_id = book.id")) {
            while (resultSet.next()) {
                // Retrieve data from the database
                int importID = resultSet.getInt("imports_id");
                int bookID = resultSet.getInt("book_id");
                String title = resultSet.getString("title");
                int quantity = resultSet.getInt("quantity");
                float price = resultSet.getFloat("import_price");
                int remaining = resultSet.getInt("remaining");

                // Create ImportBook object and add it to the list
                ImportBook importBook = new ImportBook(importID, bookID, title, quantity, (double) price, remaining);

                // Add the import sheet to the list
                importBooks.add(importBook);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
        return importBooks;
    }
}
