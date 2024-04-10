package main.frontend.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.frontend.BookstoreManagementApplication;
import main.frontend.backend.lists.*;
import main.frontend.backend.objects.Author;
import main.frontend.backend.objects.Book;
import main.frontend.backend.objects.Category;
import main.frontend.backend.objects.Publisher;
import main.frontend.backend.orders.*;

import static main.frontend.backend.lists.BookList.getPurchaseBookInfo;
import static main.frontend.backend.lists.BookList.isBookAvailable;

public class dashboardController implements Initializable{

    public Customer getCurrentCustomer() {
        return currentCustomer;
    }
    public void setCurrentCustomer(Customer currentCustomer) {
        this.currentCustomer = currentCustomer;
    }
    public void clearCurrentCustomer() {
        this.currentCustomer = null;
    }

    private Customer currentCustomer = new Customer();

    @FXML
    private AnchorPane main_form;

    @FXML
    private Button close;

    @FXML
    private Button minimize;

    @FXML
    private Label username;

    @FXML
    private Button dashboard_btn;

    @FXML
    private Button availableBooks_btn;

    @FXML
    private Button purchase_btn;

    @FXML
    private Button publisher_btn;

    @FXML
    private Button author_btn;

    @FXML
    private Button category_btn;

    @FXML
    private Button newbook_btn;

    @FXML
    private Button oos_btn;

    @FXML
    private Button import_btn;

    @FXML
    private Button import_books_btn;

    @FXML
    private Button customer_btn;

    @FXML
    private Button order_btn;

    @FXML
    private Button order_books_btn;


    @FXML
    private Button logout;

    @FXML
    private AnchorPane dashboard_form;

    @FXML
    private Label dashboard_AB;

    @FXML
    private Label dashboard_TI;

    @FXML
    private Label dashboard_TC;

    @FXML
    private AreaChart<?, ?> dashboard_incomeChart;

    @FXML
    private BarChart<?, ?> dashboard_customerChart;

    @FXML
    private AnchorPane availableBooks_form;

    @FXML
    private TextField availableBooks_bookID;

    @FXML
    private TextField availableBooks_bookTitle;

    @FXML
    private TextField availableBooks_author;

    @FXML
    private TextField availableBooks_publisher;

    @FXML
    private TextField availableBooks_genre;
    @FXML
    private TextField availableBooks_status;

    @FXML
    private TextField availableBooks_search;

    @FXML
    private TableView<bookData> availableBooks_tableView;

    @FXML
    private TableColumn<bookData, String> availableBooks_col_bookID;

    @FXML
    private TableColumn<bookData, String> availableBooks_col_bookTitle;

    @FXML
    private TableColumn<bookData, String> availableBooks_col_author;

    @FXML
    private TableColumn<bookData, String> availableBooks_col_genre;

    @FXML
    private TableColumn<bookData, String> availableBooks_col_publisher;

    @FXML
    private TableColumn<bookData, String> availableBooks_col_status;

    @FXML
    private AnchorPane purchase_form;

    @FXML
    private ComboBox<String> purchase_bookID;

    @FXML
    private ComboBox<String> purchase_customerID;

    @FXML
    private Label purchase_total;

    @FXML
    private Button purchase_addBtn;

    @FXML
    private Label purchase_info_customerName;

    @FXML
    private Label purchase_info_bookID;

    @FXML
    private Label purchase_info_bookTitle;

    @FXML
    private Label purchase_info_author;

    @FXML
    private Label purchase_info_genre;

    @FXML
    private Label purchase_info_publisher;

    @FXML
    private Button purchase_payBtn;

    @FXML
    private TableView<purchaseData> purchase_tableView;

    @FXML
    private Spinner<Integer> purchase_quantity;

    @FXML
    private TableColumn<purchaseData, String> purchase_col_bookID;

    @FXML
    private TableColumn<purchaseData, String> purchase_col_bookTitle;

    @FXML
    private TableColumn<purchaseData, String> purchase_col_author;

    @FXML
    private TableColumn<purchaseData, String> purchase_col_genre;

    @FXML
    private TableColumn<purchaseData, String> purchase_col_quantity;

    @FXML
    private TableColumn<purchaseData, String> purchase_col_price;

    @FXML
    private AnchorPane publisher_form;

    @FXML
    private TextField publisher_search;

    @FXML
    private TableView<publisherData> publisher_tableView;

    @FXML
    private TextField publisher_publisherID;

    @FXML
    private TextField publisher_publisherName;
    @FXML
    private TextField publisher_publisherStatus;

    @FXML
    private TableColumn<publisherData, String> publisher_col_publisherID;

    @FXML
    private TableColumn<publisherData, String> publisher_col_publisherName;
    @FXML
    private TableColumn<publisherData, String> publisher_col_publisherStatus;

    @FXML
    private AnchorPane author_form;

    @FXML
    private TextField author_authorID;

    @FXML
    private TextField author_authorName;

    @FXML
    private TextField author_authorStatus;

    @FXML
    private TextField author_search;

    @FXML
    private TableColumn<authorData, String> author_col_authorID;

    @FXML
    private TableColumn<authorData, String> author_col_authorName;
    @FXML
    private TableColumn<authorData, String> author_col_authorStatus;

    @FXML
    private TableView<authorData> author_tableView;

    @FXML
    private AnchorPane category_form;

    @FXML
    private TextField category_search;

    @FXML
    private TableView<categoryData> category_tableView;

    @FXML
    private TextField category_categoryID;

    @FXML
    private TextField category_categoryName;

    @FXML
    private TextField category_categoryStatus;

    @FXML
    private TableColumn<categoryData, String> category_col_categoryID;

    @FXML
    private TableColumn<categoryData, String> category_col_categoryName;

    @FXML
    private TableColumn<categoryData, String> category_col_categoryStatus;

    @FXML
    private AnchorPane newbook_form;

    @FXML
    private TextField newbook_bookID;

    @FXML
    private TextField newbook_title;

    @FXML
    private TextField newbook_author;

    @FXML
    private TextField newbook_publisher;

    @FXML
    private TextField newbook_genre;

    @FXML
    private TextField newbook_status;

    @FXML
    private TextField newbook_search;

    @FXML
    private TableView<bookData> newbook_tableView;

    @FXML
    private TableColumn<bookData, String> newbook_col_bookID;

    @FXML
    private TableColumn<bookData, String> newbook_col_title;

    @FXML
    private TableColumn<bookData, String> newbook_col_author;

    @FXML
    private TableColumn<bookData, String> newbook_col_publisher;

    @FXML
    private TableColumn<bookData, String> newbook_col_genre;

    @FXML
    private TableColumn<bookData, String> newbook_col_status;

    @FXML
    private AnchorPane oos_form;

    @FXML
    private TextField oos_bookID;

    @FXML
    private TextField oos_title;

    @FXML
    private TextField oos_author;

    @FXML
    private TextField oos_publisher;

    @FXML
    private TextField oos_genre;

    @FXML
    private TextField oos_status;

    @FXML
    private TextField oos_search;

    @FXML
    private TableView<bookData> oos_tableView;

    @FXML
    private TableColumn<bookData, String> oos_col_bookID;

    @FXML
    private TableColumn<bookData, String> oos_col_title;

    @FXML
    private TableColumn<bookData, String> oos_col_author;

    @FXML
    private TableColumn<bookData, String> oos_col_publisher;

    @FXML
    private TableColumn<bookData, String> oos_col_genre;

    @FXML
    private TableColumn<bookData, String> oos_col_status;


    @FXML
    private AnchorPane import_form;

    @FXML
    private TableView<importSheetData> import_tableView;

    @FXML
    private TextField import_search;

    @FXML
    private TableColumn<importSheetData, String> import_col_importID;

    @FXML
    private TableColumn<importSheetData, String> import_col_datetime;

    @FXML
    private TableColumn<importSheetData, String> import_col_employee;

    @FXML
    private TableColumn<importSheetData, String> import_col_totalCost;

    @FXML
    private TextField import_file;

    @FXML
    private Button import_selectFile;

    @FXML
    private AnchorPane import_books_form;

    @FXML
    private TextField import_books_importID;

    @FXML
    private TextField import_books_bookID;

    @FXML
    private TextField import_books_title;

    @FXML
    private TextField import_books_quantity;

    @FXML
    private TextField import_books_price;

    @FXML
    private TextField import_books_remaining;

    @FXML
    private TextField import_books_search;

    @FXML
    private TableView<importBookData> import_books_tableView;

    @FXML
    private TableColumn<importBookData, String> import_books_col_importID;

    @FXML
    private TableColumn<importBookData, String> import_books_col_bookID;

    @FXML
    private TableColumn<importBookData, String> import_books_col_title;

    @FXML
    private TableColumn<importBookData, String> import_books_col_quantity;

    @FXML
    private TableColumn<importBookData, String> import_books_col_price;

    @FXML
    private TableColumn<importBookData, String> import_books_col_remaining;

    @FXML
    private AnchorPane customer_form;

    @FXML
    private TextField customer_id;

    @FXML
    private TextField customer_fullName;

    @FXML
    private TextField customer_mail;

    @FXML
    private TextField customer_gender;

    @FXML
    private TextField customer_status;

    @FXML
    private TextField customer_search;

    @FXML
    private TableView<customerData> customer_tableView;

    @FXML
    private TableColumn<customerData, Integer> customer_col_id;

    @FXML
    private TableColumn<customerData, String> customer_col_fullName;

    @FXML
    private TableColumn<customerData, String> customer_col_mail;

    @FXML
    private TableColumn<customerData, String> customer_col_gender;

    @FXML
    private TableColumn<customerData, String> customer_col_status;

    @FXML
    private AnchorPane order_form;

    @FXML
    public TextField order_search;

    @FXML
    private TextField order_orderID;

    @FXML
    private TextField order_time;

    @FXML
    private TextField order_employee;

    @FXML
    private TextField order_customer;

    @FXML
    private TextField order_total;

    @FXML
    private TableView<orderData> order_tableView;

    @FXML
    private TableColumn<orderData, String> order_col_orderID;

    @FXML
    private TableColumn<orderData, String> order_col_time;

    @FXML
    private TableColumn<orderData, String> order_col_employee;

    @FXML
    private TableColumn<orderData, String> order_col_customer;

    @FXML
    private TableColumn<orderData, String> order_col_total;

    @FXML
    public TextField order_books_search;

    @FXML
    private AnchorPane order_books_form;

    @FXML
    private TextField order_books_orderID;

    @FXML
    private TextField order_books_bookID;

    @FXML
    private TextField order_books_name;

    @FXML
    private TextField order_books_quantity;

    @FXML
    private TextField order_books_price;

    @FXML
    private TableView<orderBooksData> order_books_tableView;

    @FXML
    private TableColumn<orderBooksData, String> order_books_col_orderID;

    @FXML
    private TableColumn<orderBooksData, String> order_books_col_bookID;

    @FXML
    private TableColumn<orderBooksData, String> order_books_col_quantity;

    @FXML
    private TableColumn<orderBooksData, String> order_books_col_price;

    @FXML
    private TableColumn<orderBooksData, String> order_books_col_name;

    private PublisherList fePublisherList;
    private AuthorList feAuthorList;
    private CategoryList feCategoryList;

    public void dashboardAB(){

        String sql = "SELECT COUNT(id) FROM book";

//        connect = database.connectDb();
//        int countAB = 0;
//        try{
//            prepare = connect.prepareStatement(sql);
//            result = prepare.executeQuery();
//
//            if(result.next()){
//                countAB = result.getInt("COUNT(id)");
//            }
//
//            dashboard_AB.setText(String.valueOf(countAB));
//
//        }catch(Exception e){e.printStackTrace();}
    }

    public void dashboardTI(){

        String sql = "SELECT SUM(total) FROM customer_info";

//        connect = database.connectDb();
//        double sumTotal = 0;
//        try{
//            prepare = connect.prepareStatement(sql);
//            result = prepare.executeQuery();
//
//            if(result.next()){
//                sumTotal = result.getDouble("SUM(total)");
//            }
//
//            dashboard_TI.setText("$" + String.valueOf(sumTotal));
//
//        }catch(Exception e){e.printStackTrace();}
    }

    public void dashboardTC(){
        String sql = "SELECT COUNT(id) FROM customer_info";

//        connect = database.connectDb();
//        int countTC = 0;
//        try{
//            prepare = connect.prepareStatement(sql);
//            result = prepare.executeQuery();
//
//            if(result.next()){
//                countTC = result.getInt("COUNT(id)");
//            }
//
//            dashboard_TC.setText(String.valueOf(countTC));
//
//        }catch(Exception e){e.printStackTrace();}

    }

    public void dashboardIncomeChart(){

        dashboard_incomeChart.getData().clear();

        String sql = "SELECT date, SUM(total) FROM customer_info GROUP BY date ORDER BY TIMESTAMP(date) ASC LIMIT 6";

//        connect = database.connectDb();
//
//        try{
//            XYChart.Series chart = new XYChart.Series();
//
//            prepare = connect.prepareStatement(sql);
//            result = prepare.executeQuery();
//
//            while(result.next()){
//                chart.getData().add(new XYChart.Data(result.getString(1), result.getInt(2)));
//            }
//
//            dashboard_incomeChart.getData().add(chart);
//
//        }catch(Exception e){e.printStackTrace();}

    }


    public void dashboardCustomerChart(){

        dashboard_customerChart.getData().clear();

        String sql = "SELECT date, COUNT(id) FROM customer_info GROUP BY date ORDER BY TIMESTAMP(date) ASC LIMIT 4";

//        connect = database.connectDb();
//
//        try{
//            XYChart.Series chart = new XYChart.Series();
//
//            prepare = connect.prepareStatement(sql);
//            result = prepare.executeQuery();
//
//            while(result.next()){
//                chart.getData().add(new XYChart.Data(result.getString(1), result.getInt(2)));
//            }
//
//            dashboard_customerChart.getData().add(chart);
//
//        }catch(Exception e){e.printStackTrace();}

    }

    public void availableBooksAdd(){
        try{
            Alert alert;

            if(availableBooks_bookID.getText().isEmpty()
                    || availableBooks_bookTitle.getText().isEmpty()
                    || availableBooks_author.getText().isEmpty()
                    || availableBooks_publisher.getText().isEmpty()
                    || availableBooks_genre.getText().isEmpty()
                    || availableBooks_status.getText().isEmpty()
                    ){
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            }else{
                BookList feBookList = new BookList();
                // CHECK IF BOOK ID IS ALREADY EXIST
                bookData add = new bookData(Integer.parseInt(availableBooks_bookID.getText()), availableBooks_bookTitle.getText(), availableBooks_author.getText(), availableBooks_publisher.getText(), availableBooks_genre.getText(), availableBooks_status.getText());
                boolean isAddedSuccessfully = feBookList.addBook(add);
                if(!isAddedSuccessfully) {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Book ID: " + availableBooks_bookID.getText() + " was already exist!");
                    alert.showAndWait();
                } else {
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Added!");
                    alert.showAndWait();
                    availableBooksShowListData();
                    availableBooksClear();
                }
            }
        }catch(Exception e){e.printStackTrace();}

    }

    public void availableBooksUpdate() {
        try {
            Alert alert;

            if (availableBooks_bookID.getText().isEmpty()
                    || availableBooks_bookTitle.getText().isEmpty()
                    || availableBooks_author.getText().isEmpty()
                    || availableBooks_publisher.getText().isEmpty()
                    || availableBooks_genre.getText().isEmpty()
                    || availableBooks_status.getText().isEmpty()
                    ) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                BookList feBookList = new BookList();
                bookData update = new bookData(Integer.parseInt(availableBooks_bookID.getText()), availableBooks_bookTitle.getText(), availableBooks_author.getText(), availableBooks_publisher.getText(), availableBooks_genre.getText(), availableBooks_status.getText());
                boolean isUpdatedSuccessfully = feBookList.updateBook(update);
                if (!isUpdatedSuccessfully) {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Book ID: " + availableBooks_bookID.getText() + " was not found!");
                    alert.showAndWait();
                } else {
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Updated!");
                    alert.showAndWait();
                    availableBooksShowListData();
                    availableBooksClear();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void availableBooksClear(){
        availableBooks_bookID.setText("");
        availableBooks_bookTitle.setText("");
        availableBooks_author.setText("");
        availableBooks_publisher.setText("");
        availableBooks_genre.setText("");
        availableBooks_status.setText("");
    }

    public ObservableList<bookData> availableBooksListData(){
        BookList feBookList = new BookList();
        ObservableList<bookData> listData = FXCollections.observableArrayList();
        ArrayList<Book> books = feBookList.loadBooks_fromDatabase();
        for (Book book : books) {
            bookData data;
            if (book.isEnabled()) data = new bookData(
                    book.getId(),
                    book.getTitle(),
                    book.getAuthor().getAuthorName(),
                    book.getPublisher().getPublisherName(),
                    book.getCategories().toString(),
                    "Enabled"
            );
            else data = new bookData(
                    book.getId(),
                    book.getTitle(),
                    book.getAuthor().getAuthorName(),
                    book.getPublisher().getPublisherName(),
                    book.getCategories().toString(),
                    "Disabled"
            );
            listData.add(data);
        }
        return listData;
    }

    private ObservableList<bookData> availableBooksList;
    public void availableBooksShowListData(){
        availableBooksList = availableBooksListData();
        availableBooks_col_bookID.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        availableBooks_col_bookTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        availableBooks_col_author.setCellValueFactory(new PropertyValueFactory<>("author"));
        availableBooks_col_publisher.setCellValueFactory(new PropertyValueFactory<>("publisher"));
        availableBooks_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));
        availableBooks_col_genre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        availableBooks_tableView.setItems(availableBooksList);
    }

    public void availableBooksSelect(){
        bookData bookD = availableBooks_tableView.getSelectionModel().getSelectedItem();
        int num = availableBooks_tableView.getSelectionModel().getSelectedIndex();

        if((num - 1) < -1){ return; }

        availableBooks_bookID.setText(String.valueOf(bookD.getBookId()));
        availableBooks_bookTitle.setText(bookD.getTitle());
        availableBooks_author.setText(bookD.getAuthor());
        availableBooks_genre.setText(bookD.getGenre().replace("\n", ", "));
        availableBooks_publisher.setText(bookD.getPublisher());
        availableBooks_status.setText(bookD.getStatus());
    }

    public void availableBooksSearch() {
        // Create a filtered list based on the availableBooksList
        FilteredList<bookData> filteredList = new FilteredList<>(availableBooksList, e -> true);

        // Add a listener to the text property of the search TextField
        availableBooks_search.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            // Set the predicate for filtering based on the search text
            filteredList.setPredicate(bookData -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true; // Show all items if the search text is empty
                }

                String searchKey = newValue.toLowerCase();
                return bookData.getBookId().toString().contains(searchKey) ||
                        bookData.getTitle().toLowerCase().contains(searchKey) ||
                        bookData.getAuthor().toLowerCase().contains(searchKey) ||
                        bookData.getPublisher().toLowerCase().contains(searchKey) ||
                        bookData.getGenre().toLowerCase().contains(searchKey);
            });
            // Create a sorted list based on the filtered list
            SortedList<bookData> sortedList = new SortedList<>(filteredList);

            // Bind the comparator of the sorted list to the comparator of the TableView
            sortedList.comparatorProperty().bind(availableBooks_tableView.comparatorProperty());

            // Set the items of the TableView to the sorted list
            availableBooks_tableView.setItems(sortedList);
        });
    }

    public ObservableList<bookData> newbookListData(){
        BookList feBookList = new BookList();
        ObservableList<bookData> listData = FXCollections.observableArrayList();
        ArrayList<Book> books = feBookList.loadNewBooks_fromDatabase();
        for (Book book : books) {
            bookData data;
            if (book.isEnabled()) data = new bookData(
                    book.getId(),
                    book.getTitle(),
                    book.getAuthor().getAuthorName(),
                    book.getPublisher().getPublisherName(),
                    book.getCategories().toString(),
                    "Enabled"
            );
            else data = new bookData(
                    book.getId(),
                    book.getTitle(),
                    book.getAuthor().getAuthorName(),
                    book.getPublisher().getPublisherName(),
                    book.getCategories().toString(),
                    "Disabled"
            );
            listData.add(data);
        }
        return listData;
    }

    private ObservableList<bookData> newbookList;
    public void newbookShowListData(){
        newbookList = newbookListData();
        newbook_col_bookID.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        newbook_col_title.setCellValueFactory(new PropertyValueFactory<>("title"));
        newbook_col_author.setCellValueFactory(new PropertyValueFactory<>("author"));
        newbook_col_publisher.setCellValueFactory(new PropertyValueFactory<>("publisher"));
        newbook_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));
        newbook_col_genre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        newbook_tableView.setItems(newbookList);
    }

    public void newbookSelect(){
        bookData bookD = newbook_tableView.getSelectionModel().getSelectedItem();
        int num = newbook_tableView.getSelectionModel().getSelectedIndex();

        if((num - 1) < -1){ return; }

        newbook_bookID.setText(String.valueOf(bookD.getBookId()));
        newbook_title.setText(bookD.getTitle());
        newbook_author.setText(bookD.getAuthor());
        newbook_genre.setText(bookD.getGenre().replace("\n", ", "));
        newbook_publisher.setText(bookD.getPublisher());
        newbook_status.setText(bookD.getStatus());
    }

    public void newbookSearch() {
        // Create a filtered list based on the newbookList
        FilteredList<bookData> filteredList = new FilteredList<>(newbookList, e -> true);

        // Add a listener to the text property of the search TextField
        newbook_search.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            // Set the predicate for filtering based on the search text
            filteredList.setPredicate(bookData -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true; // Show all items if the search text is empty
                }

                String searchKey = newValue.toLowerCase();
                return bookData.getBookId().toString().contains(searchKey) ||
                        bookData.getTitle().toLowerCase().contains(searchKey) ||
                        bookData.getAuthor().toLowerCase().contains(searchKey) ||
                        bookData.getPublisher().toLowerCase().contains(searchKey) ||
                        bookData.getGenre().toLowerCase().contains(searchKey);
            });
            // Create a sorted list based on the filtered list
            SortedList<bookData> sortedList = new SortedList<>(filteredList);

            // Bind the comparator of the sorted list to the comparator of the TableView
            sortedList.comparatorProperty().bind(newbook_tableView.comparatorProperty());

            // Set the items of the TableView to the sorted list
            newbook_tableView.setItems(sortedList);
        });
    }

    public ObservableList<bookData> oosListData() {  // Renamed newbookListData
        BookList feBookList = new BookList();
        ObservableList<bookData> listData = FXCollections.observableArrayList();
        ArrayList<Book> books = feBookList.loadOosBooks_fromDatabase(null);  // Assuming a similar method exists

        for (Book book : books) {
            bookData data;
            if (book.isEnabled()) {
                data = new bookData(
                        book.getId(),
                        book.getTitle(),
                        book.getAuthor().getAuthorName(),
                        book.getPublisher().getPublisherName(),
                        book.getCategories().toString(),
                        "In Stock"  // Assuming "Enabled" signifies in-stock books
                );
            } else {
                data = new bookData(
                        book.getId(),
                        book.getTitle(),
                        book.getAuthor().getAuthorName(),
                        book.getPublisher().getPublisherName(),
                        book.getCategories().toString(),
                        "Out Of Stock"  // Set status to "Out Of Stock"
                );
            }
            listData.add(data);
        }
        return listData;
    }


    private ObservableList<bookData> oosList;

    public void oosShowListData() {
        oosList = oosListData();
        oos_col_bookID.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        oos_col_title.setCellValueFactory(new PropertyValueFactory<>("title"));
        oos_col_author.setCellValueFactory(new PropertyValueFactory<>("author"));
        oos_col_publisher.setCellValueFactory(new PropertyValueFactory<>("publisher"));
        oos_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));
        oos_col_genre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        oos_tableView.setItems(oosList);
    }

    public void oosSelect() {
        bookData bookD = oos_tableView.getSelectionModel().getSelectedItem();
        int num = oos_tableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        oos_bookID.setText(String.valueOf(bookD.getBookId()));
        oos_title.setText(bookD.getTitle());
        oos_author.setText(bookD.getAuthor());
        oos_genre.setText(bookD.getGenre().replace("\n", ", "));
        oos_publisher.setText(bookD.getPublisher());
        oos_status.setText(bookD.getStatus());
    }

    public void oosSearch() {
        // Create a filtered list based on the oosList
        FilteredList<bookData> filteredList = new FilteredList<>(oosList, e -> true);

        // Add a listener to the text property of the search TextField
        oos_search.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            // Set the predicate for filtering based on the search text
            filteredList.setPredicate(bookData -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true; // Show all items if the search text is empty
                }

                String searchKey = newValue.toLowerCase();
                return bookData.getBookId().toString().contains(searchKey) ||
                        bookData.getTitle().toLowerCase().contains(searchKey) ||
                        bookData.getAuthor().toLowerCase().contains(searchKey) ||
                        bookData.getPublisher().toLowerCase().contains(searchKey) ||
                        bookData.getGenre().toLowerCase().contains(searchKey);
            });

            // Create a sorted list based on the filtered list
            SortedList<bookData> sortedList = new SortedList<>(filteredList);

            // Bind the comparator of the sorted list to the comparator of the TableView
            sortedList.comparatorProperty().bind(oos_tableView.comparatorProperty());

            // Set the items of the TableView to the sorted list
            oos_tableView.setItems(sortedList);
        });
    }


    public ObservableList<importSheetData> importSheetListData() {
        ImportSheetList importSheetList = new ImportSheetList();
        ObservableList<importSheetData> listData = FXCollections.observableArrayList();
        List<ImportSheet> importSheets = importSheetList.loadImportSheetsFromDatabase(); // Adjust the method signature according to your implementation

        for (ImportSheet importSheet : importSheets) {
            importSheetData data = new importSheetData(
                    importSheet.getId(),
                    importSheet.getImportTime(),
                    importSheet.getEmployee().getFullname(),
                    importSheet.getTotalCost()
            );
            listData.add(data);
        }
        return listData;
    }

    private ObservableList<importSheetData> importSheetList;

    public void importSheetShowListData() {
        importSheetList = importSheetListData();
        // Assuming you have appropriate TableColumn objects defined
        import_col_importID.setCellValueFactory(new PropertyValueFactory<>("importId"));
        import_col_datetime.setCellValueFactory(new PropertyValueFactory<>("importTime"));
        import_col_employee.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEmployeeName()));
        import_col_totalCost.setCellValueFactory(new PropertyValueFactory<>("totalCost"));
        import_tableView.setItems(importSheetList);
    }

    public void importSheetSearch() {
        FilteredList<importSheetData> filteredList = new FilteredList<>(importSheetList, e -> true);

        import_search.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            filteredList.setPredicate(importSheetData -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String searchKey = newValue.toLowerCase();
                return String.valueOf(importSheetData.getImportId()).contains(searchKey)
                        || importSheetData.getImportTime().toString().toLowerCase().contains(searchKey)
                        || String.valueOf(importSheetData.getEmployeeName()).contains(searchKey)
                        || String.valueOf(importSheetData.getTotalCost()).contains(searchKey);
            });
            SortedList<importSheetData> sortedList = new SortedList<>(filteredList);
            sortedList.comparatorProperty().bind(import_tableView.comparatorProperty());
            import_tableView.setItems(sortedList);
        });
    }

    //New
    public ObservableList<importBookData> importBooksListData() {
        ImportSheetList importSheetList = new ImportSheetList();
        ObservableList<importBookData> listData = FXCollections.observableArrayList();
        List<ImportBook> importBooks = importSheetList.loadImportBooksFromDatabase(); // Adjust the method signature according to your implementation

        for (ImportBook importBook : importBooks) {
            importBookData data = new importBookData(
                    importBook.getImportID(),
                    importBook.getBookID(),
                    importBook.getTitle(),
                    importBook.getQuantity(),
                    importBook.getPrice(),
                    importBook.getRemaining()
            );
            listData.add(data);
        }
        return listData;
    }

    private ObservableList<importBookData> importBooksList;

    public void importBooksShowListData() {
        importBooksList = importBooksListData();
        // Assuming you have appropriate TableColumn objects defined
        import_books_col_importID.setCellValueFactory(new PropertyValueFactory<>("importID"));
        import_books_col_bookID.setCellValueFactory(new PropertyValueFactory<>("bookID"));
        import_books_col_title.setCellValueFactory(new PropertyValueFactory<>("title"));
        import_books_col_quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        import_books_col_price.setCellValueFactory(new PropertyValueFactory<>("price"));
        import_books_col_remaining.setCellValueFactory(new PropertyValueFactory<>("remaining"));

        import_books_tableView.setItems(importBooksList);
    }

    public void importBooksSearch() {
        FilteredList<importBookData> filteredList = new FilteredList<>(importBooksList, e -> true); // Use importBooksList as the source

        import_books_search.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            filteredList.setPredicate(importBookData -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String searchKey = newValue.toLowerCase();
                return String.valueOf(importBookData.getImportID()).contains(searchKey)
                        || String.valueOf(importBookData.getBookID()).contains(searchKey)
                        || importBookData.getTitle().toLowerCase().contains(searchKey) // Consider case-insensitive search
                        || String.valueOf(importBookData.getQuantity()).contains(searchKey)
                        || String.valueOf(importBookData.getPrice()).contains(searchKey)
                        || String.valueOf(importBookData.getRemaining()).contains(searchKey);
            });
            SortedList<importBookData> sortedList = new SortedList<>(filteredList);
            sortedList.comparatorProperty().bind(import_books_tableView.comparatorProperty()); // Use import_books_tableView
            import_books_tableView.setItems(sortedList);
        });
    }

    public void importBooksSelect() {
        importBookData importBook = import_books_tableView.getSelectionModel().getSelectedItem();
        int num = import_books_tableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        // Assuming you have appropriate TextField objects defined
        import_books_importID.setText(String.valueOf(importBook.getImportID()));
        import_books_bookID.setText(String.valueOf(importBook.getBookID()));
        import_books_title.setText(importBook.getTitle());
        import_books_quantity.setText(String.valueOf(importBook.getQuantity()));
        import_books_price.setText(String.valueOf(importBook.getPrice()));
        import_books_remaining.setText(String.valueOf(importBook.getRemaining()));
        // Additional fields if needed, e.g., import_importID.setText(String.valueOf(importBook.getImportID()));
    }

    public float getTotalP() {
        return totalP;
    }

    public void setTotalP(float totalP) {
        this.totalP = totalP;
    }

    private float totalP = 0;
    private List<Map<String, Object>> orderData;

    public void setOrderData(List<Map<String, Object>> orderData) {
        this.orderData = orderData;
    }

    public void purchaseAdd(){
        try{
            Alert alert;

            if(     purchase_bookID.getSelectionModel().getSelectedItem() == null
                    || purchase_quantity.getValue() <= 0
            ){
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error message");
                alert.setHeaderText(null);
                alert.setContentText("Please choose book first");
                alert.showAndWait();
            } else {
                List<Map<String, Object>> additionalOrderData = isBookAvailable(Integer.parseInt(purchase_bookID.getSelectionModel().getSelectedItem()), purchase_quantity.getValue());
                if (additionalOrderData == null){
                    return;
                } else {
                    if (orderData == null) {
                        orderData = new ArrayList<>();
                    }
                    orderData.addAll(additionalOrderData);
                    for (Map<String, Object> data : additionalOrderData) {
                        int quantityToAdd = (int) data.get("bought");
                        float bookPrice = (float) data.get("import_price");  // Assuming price is stored as a double
                        totalP += (float) (quantityToAdd * bookPrice * 1.1);
                    }
                    purchaseList.clear();
                    for (Map<String, Object> data : orderData) {
                        float bookPrice = (float) data.get("import_price");  // Assuming price is stored as a double
                        Book book = getPurchaseBookInfo((int) data.get("book_id"));
                        int quantityToAdd = (int) data.get("bought");
                        purchaseData purchase = new purchaseData((int) data.get("book_id"), book.getTitle(), book.getAuthor().getAuthorName(), book.getCategories().toString(),quantityToAdd, bookPrice);
                        purchaseList.add(purchase);
                    }
                    purchase_col_bookID.setCellValueFactory(new PropertyValueFactory<>("bookId"));
                    purchase_col_bookTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
                    purchase_col_author.setCellValueFactory(new PropertyValueFactory<>("author"));
                    purchase_col_genre.setCellValueFactory(new PropertyValueFactory<>("genre"));
                    purchase_col_quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
                    purchase_col_price.setCellValueFactory(new PropertyValueFactory<>("price"));
                    purchase_tableView.setItems(purchaseList);
//
                    purchaseDisplayTotal(); // Should now display the calculated totalP
                }
            }
            purchase_bookID.getSelectionModel().clearSelection();
        }catch(Exception e){e.printStackTrace();}
    }

    public void purchasePay(){
        if(purchase_customerID.getSelectionModel().getSelectedItem() != null && !purchase_customerID.getSelectionModel().getSelectedItem().isEmpty()) {
            String customerString = purchase_customerID.getSelectionModel().getSelectedItem();
            String[] parts = customerString.split(": ");
            float total = (float) (totalP * 0.95);
            Order.addOrder(1, Integer.parseInt(parts[0]), total, orderData);
        } else{
            Order.addOrder(1, -1, getTotalP(), orderData);
        }

        setOrderData(null);
        setTotalP(0);
        purchaseList.clear();
        purchaseShowPurchaseListData(null);
        clearCurrentCustomer();
        purchase_customerID.getSelectionModel().clearSelection();
        purchase_bookID.getSelectionModel().clearSelection();
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information Message");
        alert.setHeaderText(null);
        alert.setContentText("Order paid successfully!");
        alert.showAndWait();
    }

    public void purchaseDisplayTotal(){
        float displayTotal = totalP;  // Use the calculated total price

        if(purchase_customerID.getSelectionModel().getSelectedItem() != null && !purchase_customerID.getSelectionModel().getSelectedItem().isEmpty()) {
            displayTotal *= 0.95F;  // Apply 5% discount
        }

        purchase_total.setText("$" + displayTotal);

    }

    public void purchaseBookId(){
        ObservableList<String> listData = FXCollections.observableArrayList();
        ArrayList<String> bookIDs = BookList.getAllPurchaseBookId();
        listData.addAll(bookIDs);
        purchase_bookID.setItems(listData);
        purchaseBookInfo();
    }
    public void purchaseCustomerId(){
        ObservableList<String> listData = FXCollections.observableArrayList();
        ArrayList<String> customerIDs = CustomerList.getAllPurchaseCustomerId();
        if (customerIDs == null || customerIDs.isEmpty()) {
            System.out.println("No customer");
        } else {
            listData.addAll(customerIDs);
        }
        purchase_customerID.setItems(listData);
    }

    public void purchaseCustomerIdSelect(){
        if(purchase_customerID.getSelectionModel().getSelectedItem() != null && !purchase_customerID.getSelectionModel().getSelectedItem().isEmpty()) {
            String customerString = purchase_customerID.getSelectionModel().getSelectedItem();
            String[] parts = customerString.split(": ");
            if(getCurrentCustomer() == null){
                setCurrentCustomer(new Customer());
            }
            getCurrentCustomer().setId(Integer.parseInt(parts[0]));
            getCurrentCustomer().setFullName(parts[1]);
        }
    }

    public void purchaseBookInfo(){
        if(purchase_bookID.getSelectionModel().getSelectedItem() != null) {
            Book book = BookList.getPurchaseBookInfo(Integer.parseInt(purchase_bookID.getSelectionModel().getSelectedItem()));
            assert book != null;
            purchase_info_bookID.setText(String.valueOf(book.getId()));
            purchase_info_bookTitle.setText(book.getTitle());
            purchase_info_author.setText(book.getAuthor().getAuthorName());
            purchase_info_genre.setText(book.getCategories().toString());
            purchase_info_publisher.setText(book.getPublisher().getPublisherName());
        }
    }

    private ObservableList<purchaseData> purchaseList = FXCollections.observableArrayList();;
    public ObservableList<purchaseData> purchaseListData(){
        int remainingQuantity = purchase_quantity.getValue();
        for (Map<String, Object> bookData : orderData) {
            int bookRemaining = (int) bookData.get("remaining");
            float bookPrice = (float) bookData.get("import_price");  // Assuming price is stored as a double
            Book book = getPurchaseBookInfo((int) bookData.get("book_id"));
            int quantityToAdd = Math.min(bookRemaining, remainingQuantity);
            purchaseData purchase = new purchaseData((int) bookData.get("book_id"), book.getTitle(), book.getAuthor().getAuthorName(), book.getCategories().toString(),quantityToAdd, bookPrice);
            purchaseList.add(purchase);
            remainingQuantity -= quantityToAdd;
            if (remainingQuantity == 0) {
                break;
            }
        }
        return purchaseList;
    }

    public void purchaseShowPurchaseListData(List<Map<String, Object>> orderData){
        int remainingQuantity = purchase_quantity.getValue();
        if(orderData != null){
            for (Map<String, Object> bookData : orderData) {
                int quantityToAdd = (int) bookData.get("bought");
                float bookPrice = (float) bookData.get("import_price");  // Assuming price is stored as a double
                Book book = getPurchaseBookInfo((int) bookData.get("book_id"));
                purchaseData purchase = new purchaseData((int) bookData.get("book_id"), book.getTitle(), book.getAuthor().getAuthorName(), book.getCategories().toString(),quantityToAdd, bookPrice);
                purchaseList.add(purchase);
                remainingQuantity -= quantityToAdd;
                if (remainingQuantity == 0) {
                    break;
                }
            }
            purchase_col_bookID.setCellValueFactory(new PropertyValueFactory<>("bookId"));
            purchase_col_bookTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
            purchase_col_author.setCellValueFactory(new PropertyValueFactory<>("author"));
            purchase_col_genre.setCellValueFactory(new PropertyValueFactory<>("genre"));
            purchase_col_quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
            purchase_col_price.setCellValueFactory(new PropertyValueFactory<>("price"));
            purchase_tableView.setItems(purchaseList);
        }
    }

    private SpinnerValueFactory<Integer> spinner;

    public void purchaseDisplayQTY(){
        spinner = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10, 0);
        purchase_quantity.setValueFactory(spinner);
    }
    private int qty;
    public void purchaseQty(){
        qty = purchase_quantity.getValue();
    }

    private int customerId;

    public void publisherAdd() {
        try {
            Alert alert;

            // Check if publisher data fields are empty
            if (publisher_publisherID.getText().isEmpty() || publisher_publisherName.getText().isEmpty() || publisher_publisherStatus.getText().isEmpty()) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all fields");
                alert.showAndWait();
            } else {
                // Create a publisherData object
                publisherData newPublisher = new publisherData(Integer.parseInt(publisher_publisherID.getText()), publisher_publisherName.getText(), publisher_publisherStatus.getText());
                // Add the publisher to the database
                PublisherList publisherList = new PublisherList();
                boolean isAddedSuccessfully = publisherList.addPublisher(newPublisher);
                if (!isAddedSuccessfully) {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Failed to add publisher!");
                    alert.showAndWait();
                } else {
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Publisher added successfully!");
                    alert.showAndWait();
                    // Refresh the publisher list
                    publisherShowListData();

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void publisherUpdate(){
        try {
            Alert alert;

            if (publisher_publisherID.getText().isEmpty()
                    || publisher_publisherName.getText().isEmpty()
                    || publisher_publisherStatus.getText().isEmpty()
            ) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                PublisherList fePublisherList = new PublisherList();
                publisherData update = new publisherData(Integer.parseInt(publisher_publisherID.getText()), publisher_publisherName.getText(), publisher_publisherStatus.getText());
                boolean isUpdatedSuccessfully = fePublisherList.updatePublisher(update);
                if (!isUpdatedSuccessfully) {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Book ID: " + availableBooks_bookID.getText() + " was not found!");
                    alert.showAndWait();
                } else {
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Updated!");
                    alert.showAndWait();
                    publisherShowListData();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void publisherSearch() {
        // Create a filtered list based on the publisherList
        FilteredList<publisherData> filteredList = new FilteredList<>(publisherList, e -> true);

        // Add a listener to the text property of the search TextField
        publisher_search.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            // Set the predicate for filtering based on the search text
            filteredList.setPredicate(publisherData -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true; // Show all items if the search text is empty
                }

                String searchKey = newValue.toLowerCase();
                return publisherData.getPublisherId().toString().contains(searchKey) ||
                        publisherData.getName().toLowerCase().contains(searchKey);
            });

            // Create a sorted list based on the filtered list
            SortedList<publisherData> sortedList = new SortedList<>(filteredList);

            // Bind the comparator of the sorted list to the comparator of the TableView
            sortedList.comparatorProperty().bind(publisher_tableView.comparatorProperty());

            // Set the items of the TableView to the sorted list
            publisher_tableView.setItems(sortedList);
        });
    }

    public ObservableList<publisherData> publisherListData() {
        PublisherList publisherList = new PublisherList();
        ObservableList<publisherData> listData = FXCollections.observableArrayList();
        ArrayList<Publisher> publishers = publisherList.loadPublishers_fromDatabase(null);
        for (Publisher publisher : publishers) {
            publisherData data;
            if (publisher.isEnabled()) data = new publisherData(
                    publisher.getId(),
                    publisher.getName(),
                    "Enabled"
            );
            else data = new publisherData(
                    publisher.getId(),
                    publisher.getName(),
                    "Disabled"
            );

            listData.add(data);
        }
        return listData;
    }
    private ObservableList<publisherData> publisherList;
    public void publisherShowListData(){
        publisherList = publisherListData();
        publisher_col_publisherID.setCellValueFactory(new PropertyValueFactory<>("publisherId"));
        publisher_col_publisherName.setCellValueFactory(new PropertyValueFactory<>("name"));
        publisher_col_publisherStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        publisher_tableView.setItems(publisherList);
    }

    public void publisherSelect(){
        publisherData pubD = publisher_tableView.getSelectionModel().getSelectedItem();
        int num = publisher_tableView.getSelectionModel().getSelectedIndex();

        if((num - 1) < -1){
            return;
        }

        publisher_publisherID.setText(String.valueOf(pubD.getPublisherId()));
        publisher_publisherName.setText(pubD.getName());
        publisher_publisherStatus.setText(pubD.getStatus());
    }

    public void authorAdd() {
        try {
            Alert alert;

            // Check if author data fields are empty
            if (author_authorID.getText().isEmpty() || author_authorName.getText().isEmpty() || author_authorStatus.getText().isEmpty()) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all fields");
                alert.showAndWait();
            } else {
                // Create an authorData object
                Author newAuthor = new Author(Integer.parseInt(author_authorID.getText()), author_authorName.getText(), Objects.equals(author_authorStatus.getText(), "Enabled"));
                // Add the author to the database
                AuthorList authorList = new AuthorList();
                boolean isAddedSuccessfully = authorList.addAuthor(newAuthor);
                if (!isAddedSuccessfully) {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Failed to add author!");
                    alert.showAndWait();
                } else {
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Author added successfully!");
                    alert.showAndWait();
                    // Refresh the author list
                    authorShowListData();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void authorUpdate(){
        try {
            Alert alert;

            if (author_authorID.getText().isEmpty()
                    || author_authorName.getText().isEmpty()
                    || author_authorStatus.getText().isEmpty()
            ) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                AuthorList feAuthorList = new AuthorList();
                Author update = new Author(Integer.parseInt(author_authorID.getText()), author_authorName.getText(), author_authorStatus.getText() == "Enabled");
                boolean isUpdatedSuccessfully = feAuthorList.updateAuthor(update);
                if (!isUpdatedSuccessfully) {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Author ID: " + author_authorID.getText() + " was not found!");
                    alert.showAndWait();
                } else {
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Updated!");
                    alert.showAndWait();
                    authorShowListData();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void authorSearch() {
        // Create a filtered list based on the authorList
        FilteredList<authorData> filteredList = new FilteredList<>(authorList, e -> true);

        // Add a listener to the text property of the search TextField
        author_search.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            // Set the predicate for filtering based on the search text
            filteredList.setPredicate(authorData -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true; // Show all items if the search text is empty
                }

                String searchKey = newValue.toLowerCase();
                return authorData.getAuthorId().toString().contains(searchKey) ||
                        authorData.getName().toLowerCase().contains(searchKey);
            });

            // Create a sorted list based on the filtered list
            SortedList<authorData> sortedList = new SortedList<>(filteredList);

            // Bind the comparator of the sorted list to the comparator of the TableView
            sortedList.comparatorProperty().bind(author_tableView.comparatorProperty());

            // Set the items of the TableView to the sorted list
            author_tableView.setItems(sortedList);
        });
    }

    public ObservableList<authorData> authorListData() {
        AuthorList authorList = new AuthorList();
        ObservableList<authorData> listData = FXCollections.observableArrayList();
        ArrayList<Author> authors = authorList.loadAuthors_fromDatabase(null);
        for (Author author : authors) {
            authorData data;
            if (author.isEnabled()) data = new authorData(
                    author.getId(),
                    author.getName(),
                    "Enabled"
            );
            else data = new authorData(
                    author.getId(),
                    author.getName(),
                    "Disabled"
            );

            listData.add(data);
        }
        return listData;
    }
    private ObservableList<authorData> authorList;
    public void authorShowListData(){
        authorList = authorListData();
        author_col_authorID.setCellValueFactory(new PropertyValueFactory<>("authorId"));
        author_col_authorName.setCellValueFactory(new PropertyValueFactory<>("name"));
        author_col_authorStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        author_tableView.setItems(authorList);
    }

    public void authorSelect(){
        authorData autD = author_tableView.getSelectionModel().getSelectedItem();
        int num = author_tableView.getSelectionModel().getSelectedIndex();

        if((num - 1) < -1){
            return;
        }

        author_authorID.setText(String.valueOf(autD.getAuthorId()));
        author_authorName.setText(autD.getName());
        author_authorStatus.setText(autD.getStatus());
    }

    public void categoryAdd() {
        try {
            Alert alert;

            // Check if category data fields are empty
            if (category_categoryID.getText().isEmpty() || category_categoryName.getText().isEmpty() || category_categoryStatus.getText().isEmpty()) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all fields");
                alert.showAndWait();
            } else {
                // Create a categoryData object
                Category newCategory = new Category(Integer.parseInt(category_categoryID.getText()), category_categoryName.getText(), Objects.equals(category_categoryStatus.getText(), "Enabled"));
                // Add the category to the database
                CategoryList categoryList = new CategoryList();
                boolean isAddedSuccessfully = categoryList.addCategory(newCategory);
                if (!isAddedSuccessfully) {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Failed to add category!");
                    alert.showAndWait();
                } else {
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Category added successfully!");
                    alert.showAndWait();
                    // Refresh the category list
                    categoryShowListData();

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void categoryUpdate(){
        try {
            Alert alert;

            if (category_categoryID.getText().isEmpty()
                    || category_categoryName.getText().isEmpty()
                    || category_categoryStatus.getText().isEmpty()
            ) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                CategoryList feCategoryList = new CategoryList();
                Category update = new Category(Integer.parseInt(category_categoryID.getText()), category_categoryName.getText(), Objects.equals(category_categoryStatus.getText(), "Enabled"));
                boolean isUpdatedSuccessfully = feCategoryList.updateCategory(update);
                if (!isUpdatedSuccessfully) {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Category ID: " + availableBooks_bookID.getText() + " was not found!");
                    alert.showAndWait();
                } else {
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Updated!");
                    alert.showAndWait();
                    categoryShowListData();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void categorySearch() {
        // Create a filtered list based on the categoryList
        FilteredList<categoryData> filteredList = new FilteredList<>(categoryList, e -> true);

        // Add a listener to the text property of the search TextField
        category_search.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            // Set the predicate for filtering based on the search text
            filteredList.setPredicate(categoryData -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true; // Show all items if the search text is empty
                }

                String searchKey = newValue.toLowerCase();
                return categoryData.getCategoryId().toString().contains(searchKey) ||
                        categoryData.getName().toLowerCase().contains(searchKey);
            });

            // Create a sorted list based on the filtered list
            SortedList<categoryData> sortedList = new SortedList<>(filteredList);

            // Bind the comparator of the sorted list to the comparator of the TableView
            sortedList.comparatorProperty().bind(category_tableView.comparatorProperty());

            // Set the items of the TableView to the sorted list
            category_tableView.setItems(sortedList);
        });
    }

    public ObservableList<categoryData> categoryListData() {
        CategoryList categoryList = new CategoryList();
        ObservableList<categoryData> listData = FXCollections.observableArrayList();
        ArrayList<Category> categories = categoryList.loadCategories_fromDatabase(null);
        for (Category category : categories) {
            categoryData data;
            if (category.isEnabled()) data = new categoryData(
                    category.getId(),
                    category.getName(),
                    "Enabled"
            );
            else data = new categoryData(
                    category.getId(),
                    category.getName(),
                    "Disabled"
            );

            listData.add(data);
        }
        return listData;
    }
    private ObservableList<categoryData> categoryList;
    public void categoryShowListData(){
        categoryList = categoryListData();
        category_col_categoryID.setCellValueFactory(new PropertyValueFactory<>("categoryId"));
        category_col_categoryName.setCellValueFactory(new PropertyValueFactory<>("name"));
        category_col_categoryStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        category_tableView.setItems(categoryList);
    }

    public void categorySelect(){
        categoryData catD = category_tableView.getSelectionModel().getSelectedItem();
        int num = category_tableView.getSelectionModel().getSelectedIndex();

        if((num - 1) < -1){
            return;
        }

        category_categoryID.setText(String.valueOf(catD.getCategoryId()));
        category_categoryName.setText(catD.getName());
        category_categoryStatus.setText(catD.getStatus());
    }


    public void displayUsername(){
//        String user = getData.username;
//        user = user.substring(0, 1).toUpperCase() + user.substring(1);
//        username.setText(user);
        username.setText("Tan");
    }

    public void switchForm(ActionEvent event){
        purchase_customerID.getSelectionModel().clearSelection();
        if(event.getSource() == dashboard_btn){
            dashboard_form.setVisible(true);
            availableBooks_form.setVisible(false);
            purchase_form.setVisible(false);
            publisher_form.setVisible(false);
            author_form.setVisible(false);
            category_form.setVisible(false);
            newbook_form.setVisible(false);
            import_form.setVisible(false);
            oos_form.setVisible(false);
            import_books_form.setVisible(false);
            customer_form.setVisible(false);
            order_form.setVisible(false);
            order_books_form.setVisible(false);

            dashboard_btn.setStyle("-fx-background-color: linear-gradient(to top right, #9AD0C2, #E2F4C5);");
            availableBooks_btn.setStyle("-fx-background-color: transparent");
            purchase_btn.setStyle("-fx-background-color: transparent");
            publisher_btn.setStyle("-fx-background-color: transparent");
            author_btn.setStyle("-fx-background-color: transparent");
            category_btn.setStyle("-fx-background-color: transparent");
            newbook_btn.setStyle("-fx-background-color: transparent");
            import_btn.setStyle("-fx-background-color: transparent");
            oos_btn.setStyle("-fx-background-color: transparent");
            import_books_btn.setStyle("-fx-background-color: transparent");
            customer_btn.setStyle("-fx-background-color: transparent");
            order_btn.setStyle("-fx-background-color: transparent");
            order_books_btn.setStyle("-fx-background-color: transparent");

            dashboardAB();
            dashboardTI();
            dashboardTC();
            dashboardIncomeChart();
            dashboardCustomerChart();

        }else if(event.getSource() == availableBooks_btn){
            dashboard_form.setVisible(false);
            availableBooks_form.setVisible(true);
            purchase_form.setVisible(false);
            publisher_form.setVisible(false);
            author_form.setVisible(false);
            category_form.setVisible(false);
            newbook_form.setVisible(false);
            import_form.setVisible(false);
            oos_form.setVisible(false);
            import_books_form.setVisible(false);
            customer_form.setVisible(false);
            order_form.setVisible(false);
            order_books_form.setVisible(false);

            availableBooks_btn.setStyle("-fx-background-color: linear-gradient(to top right, #9AD0C2, #E2F4C5);");
            dashboard_btn.setStyle("-fx-background-color: transparent");
            purchase_btn.setStyle("-fx-background-color: transparent");
            publisher_btn.setStyle("-fx-background-color: transparent");
            author_btn.setStyle("-fx-background-color: transparent");
            category_btn.setStyle("-fx-background-color: transparent");
            newbook_btn.setStyle("-fx-background-color: transparent");
            import_btn.setStyle("-fx-background-color: transparent");
            oos_btn.setStyle("-fx-background-color: transparent");
            import_books_btn.setStyle("-fx-background-color: transparent");
            customer_btn.setStyle("-fx-background-color: transparent");
            order_btn.setStyle("-fx-background-color: transparent");
            order_books_btn.setStyle("-fx-background-color: transparent");

            availableBooksShowListData();
            availableBooksSearch();

        }else if(event.getSource() == purchase_btn){
            dashboard_form.setVisible(false);
            availableBooks_form.setVisible(false);
            purchase_form.setVisible(true);
            publisher_form.setVisible(false);
            author_form.setVisible(false);
            category_form.setVisible(false);
            newbook_form.setVisible(false);
            import_form.setVisible(false);
            oos_form.setVisible(false);
            import_books_form.setVisible(false);
            customer_form.setVisible(false);
            order_form.setVisible(false);
            order_books_form.setVisible(false);

            category_btn.setStyle("-fx-background-color: transparent");
            newbook_btn.setStyle("-fx-background-color: transparent");
            purchase_btn.setStyle("-fx-background-color: linear-gradient(to top right, #9AD0C2, #E2F4C5);");
            availableBooks_btn.setStyle("-fx-background-color: transparent");
            dashboard_btn.setStyle("-fx-background-color: transparent");
            publisher_btn.setStyle("-fx-background-color: transparent");
            author_btn.setStyle("-fx-background-color: transparent");
            import_btn.setStyle("-fx-background-color: transparent");
            oos_btn.setStyle("-fx-background-color: transparent");
            import_books_btn.setStyle("-fx-background-color: transparent");
            customer_btn.setStyle("-fx-background-color: transparent");
            order_btn.setStyle("-fx-background-color: transparent");
            order_books_btn.setStyle("-fx-background-color: transparent");

            purchaseCustomerId();
            purchaseBookId();

        } else if (event.getSource() == publisher_btn) {
            dashboard_form.setVisible(false);
            availableBooks_form.setVisible(false);
            publisher_form.setVisible(true);
            purchase_form.setVisible(false);
            author_form.setVisible(false);
            category_form.setVisible(false);
            newbook_form.setVisible(false);
            import_form.setVisible(false);
            oos_form.setVisible(false);
            import_books_form.setVisible(false);
            customer_form.setVisible(false);
            order_form.setVisible(false);
            order_books_form.setVisible(false);

            publisher_btn.setStyle("-fx-background-color: linear-gradient(to top right, #9AD0C2, #E2F4C5);");
            availableBooks_btn.setStyle("-fx-background-color: transparent");
            dashboard_btn.setStyle("-fx-background-color: transparent");
            purchase_btn.setStyle("-fx-background-color: transparent");
            author_btn.setStyle("-fx-background-color: transparent");
            category_btn.setStyle("-fx-background-color: transparent");
            newbook_btn.setStyle("-fx-background-color: transparent");
            import_btn.setStyle("-fx-background-color: transparent");
            oos_btn.setStyle("-fx-background-color: transparent");
            import_books_btn.setStyle("-fx-background-color: transparent");
            customer_btn.setStyle("-fx-background-color: transparent");
            order_btn.setStyle("-fx-background-color: transparent");
            order_books_btn.setStyle("-fx-background-color: transparent");

            publisherShowListData();

        } else if (event.getSource() == author_btn) {
            dashboard_form.setVisible(false);
            availableBooks_form.setVisible(false);
            publisher_form.setVisible(false);
            purchase_form.setVisible(false);
            author_form.setVisible(true);
            category_form.setVisible(false);
            newbook_form.setVisible(false);
            import_form.setVisible(false);
            oos_form.setVisible(false);
            import_books_form.setVisible(false);
            customer_form.setVisible(false);
            order_form.setVisible(false);
            order_books_form.setVisible(false);

            author_btn.setStyle("-fx-background-color: linear-gradient(to top right, #9AD0C2, #E2F4C5);");
            availableBooks_btn.setStyle("-fx-background-color: transparent");
            dashboard_btn.setStyle("-fx-background-color: transparent");
            purchase_btn.setStyle("-fx-background-color: transparent");
            publisher_btn.setStyle("-fx-background-color: transparent");
            category_btn.setStyle("-fx-background-color: transparent");
            newbook_btn.setStyle("-fx-background-color: transparent");
            import_btn.setStyle("-fx-background-color: transparent");
            oos_btn.setStyle("-fx-background-color: transparent");
            import_books_btn.setStyle("-fx-background-color: transparent");
            customer_btn.setStyle("-fx-background-color: transparent");
            order_btn.setStyle("-fx-background-color: transparent");
            order_books_btn.setStyle("-fx-background-color: transparent");

            authorShowListData();
        } else if (event.getSource() == category_btn) {
            dashboard_form.setVisible(false);
            availableBooks_form.setVisible(false);
            publisher_form.setVisible(false);
            purchase_form.setVisible(false);
            author_form.setVisible(false);
            category_form.setVisible(true);
            newbook_form.setVisible(false);
            import_form.setVisible(false);
            oos_form.setVisible(false);
            import_books_form.setVisible(false);
            customer_form.setVisible(false);
            order_form.setVisible(false);
            order_books_form.setVisible(false);

            category_btn.setStyle("-fx-background-color: linear-gradient(to top right, #9AD0C2, #E2F4C5);");
            availableBooks_btn.setStyle("-fx-background-color: transparent");
            dashboard_btn.setStyle("-fx-background-color: transparent");
            purchase_btn.setStyle("-fx-background-color: transparent");
            publisher_btn.setStyle("-fx-background-color: transparent");
            author_btn.setStyle("-fx-background-color: transparent");
            newbook_btn.setStyle("-fx-background-color: transparent");
            import_btn.setStyle("-fx-background-color: transparent");
            oos_btn.setStyle("-fx-background-color: transparent");
            import_books_btn.setStyle("-fx-background-color: transparent");
            customer_btn.setStyle("-fx-background-color: transparent");
            order_btn.setStyle("-fx-background-color: transparent");
            order_books_btn.setStyle("-fx-background-color: transparent");

            categoryShowListData();
        } else if (event.getSource() == newbook_btn) {
            dashboard_form.setVisible(false);
            availableBooks_form.setVisible(false);
            publisher_form.setVisible(false);
            purchase_form.setVisible(false);
            author_form.setVisible(false);
            category_form.setVisible(false);
            newbook_form.setVisible(true);
            import_form.setVisible(false);
            oos_form.setVisible(false);
            import_books_form.setVisible(false);
            customer_form.setVisible(false);
            order_form.setVisible(false);
            order_books_form.setVisible(false);

            newbook_btn.setStyle("-fx-background-color: linear-gradient(to top right, #9AD0C2, #E2F4C5);");
            availableBooks_btn.setStyle("-fx-background-color: transparent");
            dashboard_btn.setStyle("-fx-background-color: transparent");
            purchase_btn.setStyle("-fx-background-color: transparent");
            publisher_btn.setStyle("-fx-background-color: transparent");
            author_btn.setStyle("-fx-background-color: transparent");
            category_btn.setStyle("-fx-background-color: transparent");
            import_btn.setStyle("-fx-background-color: transparent");
            oos_btn.setStyle("-fx-background-color: transparent");
            import_books_btn.setStyle("-fx-background-color: transparent");
            customer_btn.setStyle("-fx-background-color: transparent");
            order_btn.setStyle("-fx-background-color: transparent");
            order_books_btn.setStyle("-fx-background-color: transparent");

            newbookShowListData();
        } else if (event.getSource() == oos_btn) {
            dashboard_form.setVisible(false);
            availableBooks_form.setVisible(false);
            publisher_form.setVisible(false);
            purchase_form.setVisible(false);
            author_form.setVisible(false);
            category_form.setVisible(false);
            newbook_form.setVisible(false);
            oos_form.setVisible(true);
            import_form.setVisible(false);
            import_books_form.setVisible(false);
            customer_form.setVisible(false);
            order_form.setVisible(false);
            order_books_form.setVisible(false);

            oos_btn.setStyle("-fx-background-color: linear-gradient(to top right, #9AD0C2, #E2F4C5);");
            availableBooks_btn.setStyle("-fx-background-color: transparent");
            dashboard_btn.setStyle("-fx-background-color: transparent");
            purchase_btn.setStyle("-fx-background-color: transparent");
            publisher_btn.setStyle("-fx-background-color: transparent");
            author_btn.setStyle("-fx-background-color: transparent");
            category_btn.setStyle("-fx-background-color: transparent");
            newbook_btn.setStyle("-fx-background-color: transparent");
            import_btn.setStyle("-fx-background-color: transparent");
            import_books_btn.setStyle("-fx-background-color: transparent");
            customer_btn.setStyle("-fx-background-color: transparent");
            order_btn.setStyle("-fx-background-color: transparent");
            order_books_btn.setStyle("-fx-background-color: transparent");

            oosShowListData();
        } else if (event.getSource() == import_btn) {
            dashboard_form.setVisible(false);
            availableBooks_form.setVisible(false);
            publisher_form.setVisible(false);
            purchase_form.setVisible(false);
            author_form.setVisible(false);
            category_form.setVisible(false);
            newbook_form.setVisible(false);
            oos_form.setVisible(false);
            import_form.setVisible(true);
            import_books_form.setVisible(false);
            customer_form.setVisible(false);
            order_form.setVisible(false);
            order_books_form.setVisible(false);

            import_btn.setStyle("-fx-background-color: linear-gradient(to top right, #9AD0C2, #E2F4C5);");
            availableBooks_btn.setStyle("-fx-background-color: transparent");
            dashboard_btn.setStyle("-fx-background-color: transparent");
            purchase_btn.setStyle("-fx-background-color: transparent");
            publisher_btn.setStyle("-fx-background-color: transparent");
            author_btn.setStyle("-fx-background-color: transparent");
            category_btn.setStyle("-fx-background-color: transparent");
            newbook_btn.setStyle("-fx-background-color: transparent");
            oos_btn.setStyle("-fx-background-color: transparent");
            import_books_btn.setStyle("-fx-background-color: transparent");
            customer_btn.setStyle("-fx-background-color: transparent");
            order_btn.setStyle("-fx-background-color: transparent");
            order_books_btn.setStyle("-fx-background-color: transparent");

            importSheetShowListData();
        } else if (event.getSource() == import_books_btn) {
            dashboard_form.setVisible(false);
            availableBooks_form.setVisible(false);
            publisher_form.setVisible(false);
            purchase_form.setVisible(false);
            author_form.setVisible(false);
            category_form.setVisible(false);
            newbook_form.setVisible(false);
            import_form.setVisible(false);
            oos_form.setVisible(false);
            import_books_form.setVisible(true);
            customer_form.setVisible(false);
            order_form.setVisible(false);
            order_books_form.setVisible(false);

            import_books_btn.setStyle("-fx-background-color: linear-gradient(to top right, #9AD0C2, #E2F4C5);");
            availableBooks_btn.setStyle("-fx-background-color: transparent");
            dashboard_btn.setStyle("-fx-background-color: transparent");
            purchase_btn.setStyle("-fx-background-color: transparent");
            publisher_btn.setStyle("-fx-background-color: transparent");
            author_btn.setStyle("-fx-background-color: transparent");
            category_btn.setStyle("-fx-background-color: transparent");
            newbook_btn.setStyle("-fx-background-color: transparent");
            oos_btn.setStyle("-fx-background-color: transparent");
            import_btn.setStyle("-fx-background-color: transparent");
            customer_btn.setStyle("-fx-background-color: transparent");
            order_btn.setStyle("-fx-background-color: transparent");
            order_books_btn.setStyle("-fx-background-color: transparent");

            importBooksShowListData();
        } else if (event.getSource() == customer_btn) {
            dashboard_form.setVisible(false);
            availableBooks_form.setVisible(false);
            publisher_form.setVisible(false);
            purchase_form.setVisible(false);
            author_form.setVisible(false);
            category_form.setVisible(false);
            newbook_form.setVisible(false);
            import_form.setVisible(false);
            oos_form.setVisible(false);
            import_books_form.setVisible(false);
            customer_form.setVisible(true);
            order_form.setVisible(false);
            order_books_form.setVisible(false);

            customer_btn.setStyle("-fx-background-color: linear-gradient(to top right, #9AD0C2, #E2F4C5);");
            availableBooks_btn.setStyle("-fx-background-color: transparent");
            dashboard_btn.setStyle("-fx-background-color: transparent");
            purchase_btn.setStyle("-fx-background-color: transparent");
            publisher_btn.setStyle("-fx-background-color: transparent");
            author_btn.setStyle("-fx-background-color: transparent");
            category_btn.setStyle("-fx-background-color: transparent");
            newbook_btn.setStyle("-fx-background-color: transparent");
            import_btn.setStyle("-fx-background-color: transparent");
            oos_btn.setStyle("-fx-background-color: transparent");
            import_books_btn.setStyle("-fx-background-color: transparent");
            order_btn.setStyle("-fx-background-color: transparent");
            order_books_btn.setStyle("-fx-background-color: transparent");

            customerShowListData();
        } else if (event.getSource() == order_btn) {
            dashboard_form.setVisible(false);
            availableBooks_form.setVisible(false);
            publisher_form.setVisible(false);
            purchase_form.setVisible(false);
            author_form.setVisible(false);
            category_form.setVisible(false);
            newbook_form.setVisible(false);
            import_form.setVisible(false);
            oos_form.setVisible(false);
            import_books_form.setVisible(false);
            customer_form.setVisible(false);
            order_form.setVisible(true);
            order_books_form.setVisible(false);

            order_btn.setStyle("-fx-background-color: linear-gradient(to top right, #9AD0C2, #E2F4C5);");
            availableBooks_btn.setStyle("-fx-background-color: transparent");
            dashboard_btn.setStyle("-fx-background-color: transparent");
            purchase_btn.setStyle("-fx-background-color: transparent");
            publisher_btn.setStyle("-fx-background-color: transparent");
            author_btn.setStyle("-fx-background-color: transparent");
            category_btn.setStyle("-fx-background-color: transparent");
            newbook_btn.setStyle("-fx-background-color: transparent");
            import_btn.setStyle("-fx-background-color: transparent");
            oos_btn.setStyle("-fx-background-color: transparent");
            import_books_btn.setStyle("-fx-background-color: transparent");
            customer_btn.setStyle("-fx-background-color: transparent");
            order_books_btn.setStyle("-fx-background-color: transparent");

            orderShowListData();
        } else if (event.getSource() == order_books_btn) {
            dashboard_form.setVisible(false);
            availableBooks_form.setVisible(false);
            publisher_form.setVisible(false);
            purchase_form.setVisible(false);
            author_form.setVisible(false);
            category_form.setVisible(false);
            newbook_form.setVisible(false);
            import_form.setVisible(false);
            oos_form.setVisible(false);
            import_books_form.setVisible(false);
            customer_form.setVisible(false);
            order_form.setVisible(false);
            order_books_form.setVisible(true);

            order_books_btn.setStyle("-fx-background-color: linear-gradient(to top right, #9AD0C2, #E2F4C5);");
            availableBooks_btn.setStyle("-fx-background-color: transparent");
            dashboard_btn.setStyle("-fx-background-color: transparent");
            purchase_btn.setStyle("-fx-background-color: transparent");
            publisher_btn.setStyle("-fx-background-color: transparent");
            author_btn.setStyle("-fx-background-color: transparent");
            category_btn.setStyle("-fx-background-color: transparent");
            newbook_btn.setStyle("-fx-background-color: transparent");
            import_btn.setStyle("-fx-background-color: transparent");
            oos_btn.setStyle("-fx-background-color: transparent");
            import_books_btn.setStyle("-fx-background-color: transparent");
            customer_btn.setStyle("-fx-background-color: transparent");
            order_btn.setStyle("-fx-background-color: transparent");

            orderBooksShowListData();
        }
    }

    private double x = 0;
    private double y = 0;
    public void logout(){
        try{
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to logout?");
            Optional<ButtonType> option = alert.showAndWait();

            if(option.get().equals(ButtonType.OK)){

                // HIDE YOUR DASHBOARD
                logout.getScene().getWindow().hide();
                // LINK YOUR LOGIN FORM
                Parent root = FXMLLoader.load(Objects.requireNonNull(BookstoreManagementApplication.class.getResource("/main/frontend/fxml/FXMLDocument.fxml")));
                Stage stage = new Stage();
                Scene scene = new Scene(root);

                root.setOnMousePressed((MouseEvent event) ->{
                    x = event.getSceneX();
                    y = event.getSceneY();
                });

                root.setOnMouseDragged((MouseEvent event) ->{
                    stage.setX(event.getScreenX() - x);
                    stage.setY(event.getScreenY() - y);

                    stage.setOpacity(.8);
                });

                root.setOnMouseReleased((MouseEvent event) ->{
                    stage.setOpacity(1);
                });

                stage.initStyle(StageStyle.TRANSPARENT);

                stage.setScene(scene);
                stage.show();
            }

        }catch(Exception e){e.printStackTrace();}
    }

    public void close(){
        System.exit(0);
    }

    public void minimize(){
        Stage stage = (Stage)main_form.getScene().getWindow();
        stage.setIconified(true);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        displayUsername();

        dashboardAB();
        dashboardTI();
        dashboardTC();
        dashboardIncomeChart();
        dashboardCustomerChart();

        // TO SHOW THE DATA ON TABLEVIEW (AVAILABLE BOOKS)
        availableBooksShowListData();
        purchaseDisplayQTY();
        purchaseDisplayTotal();
        publisherShowListData();
        authorShowListData();
        categoryShowListData();
        newbookShowListData();
        oosShowListData();
        importSheetShowListData();
        importBooksShowListData();
        customerShowListData();
        purchaseCustomerId();
        purchaseBookId();
        orderShowListData();
        orderBooksShowListData();
    }

    public File selectedImportFile;
    public void selectImportFile(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();

        // Set the title of the dialog
        fileChooser.setTitle("Select Import File");

        // Show the dialog and wait for the user to select a file
        selectedImportFile = fileChooser.showOpenDialog(import_selectFile.getScene().getWindow());

        // Check if a file was selected
        if (selectedImportFile != null) {
            // Set the selected file's name to the import_file TextField
            import_file.setText(selectedImportFile.getName());
        }
    }

    public void addImport(ActionEvent actionEvent) {
        if (selectedImportFile != null) {
            try (Scanner scanner = new Scanner(new FileInputStream(selectedImportFile))) {
                boolean skipFirstLine = true; // Flag to skip the first line
                ImportSheetList importSheetList = new ImportSheetList();
                ImportSheet importSheet = new ImportSheet();
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    if (skipFirstLine) {
                        skipFirstLine = false;
                        continue;
                    }
                    String[] fields = line.split("\t");
                    if (fields.length >= 3) {
                        String bookId = fields[0];
                        int quantity = Integer.parseInt(fields[1]);
                        float price = Float.parseFloat(fields[2]);

                        if (importSheetList.bookExistsInDatabase(Integer.parseInt(bookId))) {
                            ImportItem item = new ImportItem(new Book(Integer.parseInt(bookId)), quantity, price);
                            importSheet.getImportItems().add(item);
                        } else {
                            System.out.println("Book with ID " + bookId + " does not exist in the database. Skipping...");
                        }
                    }
                }
                importSheetList.addImportSheet(importSheet);
                importSheetShowListData();
            } catch (IOException e) {
                e.printStackTrace(); // Handle or log the exception as needed
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            // No file selected, handle this case accordingly
            System.out.println("No file selected.");
        }
    }

    public void customerClear() {
        customer_id.setText("");
        customer_fullName.setText("");
        customer_mail.setText("");
        customer_gender.setText("");
        customer_status.setText("");
    }

    public ObservableList<customerData> customerListData() {
        CustomerList customerList = new CustomerList(); // Assuming CustomerList class exists
        ObservableList<customerData> listData = FXCollections.observableArrayList();
        ArrayList<Customer> customers = customerList.loadCustomersFromDatabase(); // Assuming loadCustomersFromDatabase exists
        for (Customer customer : customers) {
            customerData data = new customerData(
                    customer.getId(),
                    customer.getFullName(),
                    customer.getEmail(),
                    customer.getGender(),
                    customer.isEnabled() ? "Enabled" : "Disabled"
            );
            listData.add(data);
        }
        return listData;
    }

    public void customerAdd() {
        try {
            Alert alert;

            if ( customer_id.getText().isEmpty()
                    || customer_fullName.getText().isEmpty()
                    || customer_mail.getText().isEmpty()
                    || customer_gender.getText().isEmpty()
                    || customer_status.getText().isEmpty()
            ) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                CustomerList customerList = new CustomerList();
                // Assuming a CustomerData class for representing customer data
                Customer add = new Customer(
                        Integer.parseInt(customer_id.getText()),
                        customer_mail.getText(),
                        customer_fullName.getText(),
                        customer_gender.getText(),
                        Objects.equals(customer_status.getText(), "Enabled")
                );
                boolean isAddedSuccessfully = customerList.addCustomer(add);
                if (!isAddedSuccessfully) {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    // Assuming a unique identifier for customers
                    alert.setContentText("Customer ID: " + customer_id.getText() + " was already exist!");
                    alert.showAndWait();
                } else {
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Added!");
                    alert.showAndWait();
                    customerShowListData();  // Assuming a method to display customer list
                    customerClear();  // Assuming a method to clear input fields
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void customerUpdate() {
        try {
            Alert alert;

            // Assuming fields for customer name, address, etc.
            if (customer_id.getText().isEmpty()
                    || customer_fullName.getText().isEmpty()
                    || customer_mail.getText().isEmpty()
                    || customer_gender.getText().isEmpty()
                    || customer_status.getText().isEmpty()) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                CustomerList customerList = new CustomerList();
                // Assuming a CustomerData class for representing customer data
                Customer update = new Customer(
                        Integer.parseInt(customer_id.getText()),  // Assuming ID is used for update
                        customer_fullName.getText(),
                        customer_mail.getText(),
                        customer_gender.getText(),
                        Objects.equals(customer_status.getText(), "Enabled")
                );
                boolean isUpdatedSuccessfully = customerList.updateCustomer(update);
                if (!isUpdatedSuccessfully) {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Customer ID: " + customer_id.getText() + " was not found!");
                    alert.showAndWait();
                } else {
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Updated!");
                    alert.showAndWait();
                    customerShowListData();  // Assuming a method to display customer list
                    customerClear();  // Assuming a method to clear input fields
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    private ObservableList<customerData> customerList;

    public void customerShowListData() {
        customerList = customerListData();
        customer_col_id.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        customer_col_fullName.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        customer_col_mail.setCellValueFactory(new PropertyValueFactory<>("email"));
        customer_col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        customer_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));
        customer_tableView.setItems(customerList);
    }

    public void customerSelect() {
        customerData customer = customer_tableView.getSelectionModel().getSelectedItem();
        int num = customer_tableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        customer_id.setText(String.valueOf(customer.getCustomerID()));
        customer_fullName.setText(customer.getFullName());
        customer_mail.setText(customer.getEmail());
        customer_gender.setText(customer.getGender());
        customer_status.setText(Objects.equals(customer.getStatus(), "Enabled") ? "Enabled" : "Disabled");
    }

    public void customerSearch() {
        // Create a filtered list based on the customerList
        FilteredList<customerData> filteredList = new FilteredList<>(customerList, e -> true);

        // Add a listener to the text property of the search TextField
        customer_search.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            // Set the predicate for filtering based on the search text
            filteredList.setPredicate(customerData -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true; // Show all items if the search text is empty
                }

                String searchKey = newValue.toLowerCase();
                return String.valueOf(customerData.getCustomerID()).contains(searchKey) ||
                        customerData.getFullName().toLowerCase().contains(searchKey) ||
                        customerData.getEmail().toLowerCase().contains(searchKey) ||
                        customerData.getGender().toLowerCase().contains(searchKey) ||
                        String.valueOf(customerData.getStatus()).toLowerCase().contains(searchKey);
            });
            // Create a sorted list based on the filtered list
            SortedList<customerData> sortedList = new SortedList<>(filteredList);

            // Bind the comparator of the sorted list to the comparator of the TableView
            sortedList.comparatorProperty().bind(customer_tableView.comparatorProperty());

            // Set the items of the TableView to the sorted list
            customer_tableView.setItems(sortedList);
        });
    }

    public ObservableList<orderData> orderListData(){
        OrderList feOrderList = new OrderList();
        ObservableList<orderData> listData = FXCollections.observableArrayList();
        ArrayList<OrderItem> orders = feOrderList.loadOrdersFromDatabase();
        for (OrderItem order : orders) {
            orderData data = new orderData(
                    order.getOrderId(),
                    order.getOrderDate(),
                    order.getEmployee().getFullname(),
                    order.getCustomer().getFullName(),
                    order.getSalePrice()
            );
            listData.add(data);
        }
        return listData;
    }

    private ObservableList<orderData> orderList;
    public void orderShowListData(){
        orderList = orderListData();
        order_col_orderID.setCellValueFactory(new PropertyValueFactory<>("orderID"));
        order_col_time.setCellValueFactory(new PropertyValueFactory<>("date"));
        order_col_employee.setCellValueFactory(new PropertyValueFactory<>("employeeName"));
        order_col_customer.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        order_col_total.setCellValueFactory(new PropertyValueFactory<>("total"));
        order_tableView.setItems(orderList);
    }

    public void orderSelect(){
        orderData orderD = order_tableView.getSelectionModel().getSelectedItem();
        if (orderD == null) return;

        order_orderID.setText(String.valueOf(orderD.getOrderID()));
        order_time.setText(orderD.getDate().toString());
        order_employee.setText(orderD.getEmployeeName());
        order_customer.setText(orderD.getCustomerName());
        order_total.setText(String.valueOf(orderD.getTotal()));
    }

    public void orderSearch() {
        // Create a filtered list based on the orderList
        FilteredList<orderData> filteredList = new FilteredList<>(orderList, e -> true);

        // Add a listener to the text property of the search TextField
        order_search.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            // Set the predicate for filtering based on the search text
            filteredList.setPredicate(orderData -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true; // Show all items if the search text is empty
                }

                String searchKey = newValue.toLowerCase();
                return orderData.getOrderID().toString().contains(searchKey) ||
                        orderData.getDate().toString().toLowerCase().contains(searchKey) ||
                        orderData.getEmployeeName().toLowerCase().contains(searchKey) ||
                        orderData.getCustomerName().toLowerCase().contains(searchKey) ||
                        String.valueOf(orderData.getTotal()).toLowerCase().contains(searchKey);
            });
            // Create a sorted list based on the filtered list
            SortedList<orderData> sortedList = new SortedList<>(filteredList);

            // Bind the comparator of the sorted list to the comparator of the TableView
            sortedList.comparatorProperty().bind(order_tableView.comparatorProperty());

            // Set the items of the TableView to the sorted list
            order_tableView.setItems(sortedList);
        });
    }

    public ObservableList<orderBooksData> orderBooksListData(){
        OrderBooksList feOrderBooksList = new OrderBooksList();
        ObservableList<orderBooksData> listData = FXCollections.observableArrayList();
        ArrayList<OrderBook> orderBooks = feOrderBooksList.loadOrderBooksFromDatabase();
        for (OrderBook ob : orderBooks) {
            orderBooksData data = new orderBooksData(
                    ob.getBookName(),
                    ob.getOrderId(),
                    ob.getBookId(),
                    ob.getQuantity(),
                    ob.getPrice()
            );
            listData.add(data);
        }
        return listData;
    }

    private ObservableList<orderBooksData> orderBooksList;
    public void orderBooksShowListData(){
        orderBooksList = orderBooksListData();
        order_books_col_orderID.setCellValueFactory(new PropertyValueFactory<>("orderID"));
        order_books_col_bookID.setCellValueFactory(new PropertyValueFactory<>("bookID"));
        order_books_col_quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        order_books_col_price.setCellValueFactory(new PropertyValueFactory<>("price"));
        order_books_col_name.setCellValueFactory(new PropertyValueFactory<>("bookName"));
        order_books_tableView.setItems(orderBooksList);
    }

    public void orderBooksSelect(){
        orderBooksData orderBooksD = order_books_tableView.getSelectionModel().getSelectedItem();
        if (orderBooksD == null) return;
        order_books_orderID.setText(String.valueOf(orderBooksD.getOrderID()));
        order_books_bookID.setText(String.valueOf(orderBooksD.getBookID()));
        order_books_name.setText(String.valueOf(orderBooksD.getBookName()));
        order_books_quantity.setText(String.valueOf(orderBooksD.getQuantity()));
        order_books_price.setText(String.valueOf(orderBooksD.getPrice()));
    }

    public void orderBooksSearch() {
        // Create a filtered list based on the orderBooksList
        FilteredList<orderBooksData> filteredList = new FilteredList<>(orderBooksList, e -> true);

        // Add a listener to the text property of the search TextField
        order_books_search.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            // Set the predicate for filtering based on the search text
            filteredList.setPredicate(orderBooksData -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true; // Show all items if the search text is empty
                }

                String searchKey = newValue.toLowerCase();
                return orderBooksData.getOrderID().toString().contains(searchKey) ||
                        orderBooksData.getBookName().toLowerCase().contains(searchKey) ||
                        orderBooksData.getBookID().toString().contains(searchKey) ||
                        orderBooksData.getQuantity().toString().contains(searchKey) ||
                        orderBooksData.getPrice().toString().contains(searchKey);
            });
            // Create a sorted list based on the filtered list
            SortedList<orderBooksData> sortedList = new SortedList<>(filteredList);

            // Bind the comparator of the sorted list to the comparator of the TableView
            sortedList.comparatorProperty().bind(order_books_tableView.comparatorProperty());

            // Set the items of the TableView to the sorted list
            order_books_tableView.setItems(sortedList);
        });
    }

}
