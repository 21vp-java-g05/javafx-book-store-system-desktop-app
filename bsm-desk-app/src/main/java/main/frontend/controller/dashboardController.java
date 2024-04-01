package main.frontend.controller;

import java.io.File;
import java.net.URL;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.Statement;
import java.util.*;

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
import javafx.scene.control.DatePicker;
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
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.frontend.BookstoreManagementApplication;
import main.frontend.backend.lists.AuthorList;
import main.frontend.backend.lists.BookList;
import main.frontend.backend.lists.CategoryList;
import main.frontend.backend.lists.PublisherList;
import main.frontend.backend.objects.Author;
import main.frontend.backend.objects.Book;
import main.frontend.backend.objects.Publisher;

public class dashboardController implements Initializable{


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
    private Button availableBooks_importBtn;

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
    private DatePicker availableBooks_date;

    @FXML
    private TextField availableBooks_price;

    @FXML
    private Button availableBooks_addBtn;

    @FXML
    private Button availableBooks_updateBtn;

    @FXML
    private Button availableBooks_clearBtn;

    @FXML
    private Button availableBooks_deleteBtn;

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
    private ComboBox<?> purchase_bookID;

    @FXML
    private ComboBox<?> purchase_bookTitle;

    @FXML
    private Label purchase_total;

    @FXML
    private Button purchase_addBtn;

    @FXML
    private Label purchase_info_bookID;

    @FXML
    private Label purchase_info_bookTitle;

    @FXML
    private Label purchase_info_author;

    @FXML
    private Label purchase_info_genre;

    @FXML
    private Label purchase_info_date;

    @FXML
    private Button purchase_payBtn;

    @FXML
    private TableView<customerData> purchase_tableView;

    @FXML
    private Spinner<Integer> purchase_quantity;

    @FXML
    private TableColumn<customerData, String> purchase_col_bookID;

    @FXML
    private TableColumn<customerData, String> purchase_col_bookTitle;

    @FXML
    private TableColumn<customerData, String> purchase_col_author;

    @FXML
    private TableColumn<customerData, String> purchase_col_genre;

    @FXML
    private TableColumn<customerData, String> purchase_col_quantity;

    @FXML
    private TableColumn<customerData, String> purchase_col_price;

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
    private Button publisher_importBtn;

    @FXML
    private Button publisher_addBtn;

    @FXML
    private Button publisher_updateBtn;

    @FXML
    private Button publisher_enableBtn;

    @FXML
    private Button publisher_disableBtn;

    @FXML
    private TableColumn<publisherData, String> publisher_col_publisherID;

    @FXML
    private TableColumn<publisherData, String> publisher_col_publisherName;
    @FXML
    private TableColumn<publisherData, String> publisher_col_publisherStatus;

    @FXML
    private AnchorPane author_form;

    @FXML
    private Button author_importBtn;

    @FXML
    private Button author_addBtn;

    @FXML
    private Button author_enableBtn;

    @FXML
    private Button author_updateBtn;

    @FXML
    private Button author_disableBtn;

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
        ArrayList<Book> books = feBookList.loadBooks_fromDatabase(null);
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

    private double totalP;
    public void purchaseAdd(){
        purchasecustomerId();

        String sql = "INSERT INTO customer (customer_id, book_id, title, author, genre, quantity, price, date) "
                + "VALUES(?,?,?,?,?,?,?,?)";

//        connect = database.connectDb();

        try{
            Alert alert;

            if(purchase_bookTitle.getSelectionModel().getSelectedItem() == null
                    || purchase_bookID.getSelectionModel().getSelectedItem() == null){
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error message");
                alert.setHeaderText(null);
                alert.setContentText("Please choose book first");
                alert.showAndWait();
            }else{

//                prepare = connect.prepareStatement(sql);
//                prepare.setString(1, String.valueOf(customerId));
//                prepare.setString(2, purchase_info_bookID.getText());
//                prepare.setString(3, purchase_info_bookTItle.getText());
//                prepare.setString(4, purchase_info_author.getText());
//                prepare.setString(5, purchase_info_genre.getText());
//                prepare.setString(6, String.valueOf(qty));

                String checkData = "SELECT title, price FROM book WHERE title = '"
                        +purchase_bookTitle.getSelectionModel().getSelectedItem()+"'";

                double priceD = 0;

//                statement = connect.createStatement();
//                result = statement.executeQuery(checkData);
//
//                if(result.next()){
//                    priceD = result.getDouble("price");
//                }
//
//                totalP = (qty * priceD);
//
//                prepare.setString(7, String.valueOf(totalP));
//
//                Date date = new Date();
//                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
//
//                prepare.setString(8, String.valueOf(sqlDate));
//
//                prepare.executeUpdate();

                purchaseDisplayTotal();
                purchaseShowCustomerListData();
            }
        }catch(Exception e){e.printStackTrace();}
    }

    public void purchasePay(){

        String sql = "INSERT INTO customer_info (customer_id, total, date) "
                + "VALUES(?,?,?)";

//        connect = database.connectDb();

        try{
            Alert alert;
            if(displayTotal == 0){
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error message");
                alert.setHeaderText(null);
                alert.setContentText("Invalid :3");
                alert.showAndWait();
            }else{
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure?");
                Optional<ButtonType> option = alert.showAndWait();

                if(option.get().equals(ButtonType.OK)){
//                    prepare = connect.prepareStatement(sql);
//                    prepare.setString(1, String.valueOf(customerId));
//                    prepare.setString(2, String.valueOf(displayTotal));
//
//                    Date date = new Date();
//                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
//
//                    prepare.setString(3, String.valueOf(sqlDate));
//
//                    prepare.executeUpdate();

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successful!");
                    alert.showAndWait();
                }
            }
        }catch(Exception e){e.printStackTrace();}

    }

    private double displayTotal;
    public void purchaseDisplayTotal(){
        purchasecustomerId();

        String sql = "SELECT SUM(price) FROM customer WHERE customer_id = '"+customerId+"'";

//        connect = database.connectDb();

        try{
//            prepare = connect.prepareStatement(sql);
//            result = prepare.executeQuery();
//
//            if(result.next()){
//                displayTotal = result.getDouble("SUM(price)");
//            }

            purchase_total.setText("$" + String.valueOf(displayTotal));

        }catch(Exception e){e.printStackTrace();}

    }

    public void purchaseBookId(){

        String sql = "SELECT book_id FROM book";

//        connect = database.connectDb();

        try{
//            prepare = connect.prepareStatement(sql);
//            result = prepare.executeQuery();

            ObservableList listData = FXCollections.observableArrayList();

//            while(result.next()){
//                listData.add(result.getString("book_id"));
//            }

            purchase_bookID.setItems(listData);
            purchaseBookTitle();
        }catch(Exception e){e.printStackTrace();}

    }

    public void purchaseBookTitle(){

        String sql = "SELECT book_id, title FROM book WHERE book_id = '"
                +purchase_bookID.getSelectionModel().getSelectedItem()+"'";

//        connect = database.connectDb();

        try{
//            prepare = connect.prepareStatement(sql);
//            result = prepare.executeQuery();

            ObservableList listData = FXCollections.observableArrayList();

//            while(result.next()){
//                listData.add(result.getString("title"));
//            }

            purchase_bookTitle.setItems(listData);

            purchaseBookInfo();

        }catch(Exception e){e.printStackTrace();}

    }

    public void purchaseBookInfo(){

        String sql = "SELECT * FROM book WHERE title = '"
                +purchase_bookTitle.getSelectionModel().getSelectedItem()+"'";

//        connect = database.connectDb();

        String bookId = "";
        String title = "";
        String author = "";
        String genre = "";
        String date = "";

        try{
//            prepare = connect.prepareStatement(sql);
//            result = prepare.executeQuery();
//
//            if(result.next()){
//                bookId = result.getString("book_id");
//                title = result.getString("title");
//                author = result.getString("author");
//                genre = result.getString("genre");
//                date = result.getString("pub_date");
//            }

            purchase_info_bookID.setText(bookId);
            purchase_info_bookTitle.setText(title);
            purchase_info_author.setText(author);
            purchase_info_genre.setText(genre);
            purchase_info_date.setText(date);

        }catch(Exception e){e.printStackTrace();}

    }

    public ObservableList<customerData> purchaseListData(){
        purchasecustomerId();
        String sql = "SELECT * FROM customer WHERE customer_id = '"+customerId+"'";

        ObservableList<customerData> listData = FXCollections.observableArrayList();

//        connect = database.connectDb();

        try{
//            prepare  = connect.prepareStatement(sql);
//            result = prepare.executeQuery();

            customerData customerD;

//            while(result.next()){
//                customerD = new customerData(result.getInt("customer_id")
//                        , result.getInt("book_id")
//                        , result.getString("title")
//                        , result.getString("author")
//                        , result.getString("genre")
//                        , result.getInt("quantity")
//                        , result.getDouble("price")
//                        , result.getDate("date"));
//
//                listData.add(customerD);
//            }

        }catch(Exception e){e.printStackTrace();}
        return listData;
    }

    public void purchaseShowCustomerListData(){
        ObservableList<customerData> purchaseCustomerList = purchaseListData();
        purchase_col_bookID.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        purchase_col_bookTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        purchase_col_author.setCellValueFactory(new PropertyValueFactory<>("author"));
        purchase_col_genre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        purchase_col_quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        purchase_col_price.setCellValueFactory(new PropertyValueFactory<>("price"));

        purchase_tableView.setItems(purchaseCustomerList);

    }

    private SpinnerValueFactory<Integer> spinner;

    public void purchaseDisplayQTY(){
        spinner = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10, 0);
        purchase_quantity.setValueFactory(spinner);
    }
    private int qty;
    public void purhcaseQty(){
        qty = purchase_quantity.getValue();
    }

    private int customerId;
    public void purchasecustomerId(){

        String sql = "SELECT MAX(customer_id) FROM customer";
        int checkCID = 0 ;
//        connect = database.connectDb();

        try{
//            prepare = connect.prepareStatement(sql);
//            result = prepare.executeQuery();
//
//            if(result.next()){
//                customerId = result.getInt("MAX(customer_id)");
//            }

            String checkData = "SELECT MAX(customer_id) FROM customer_info";

//            prepare = connect.prepareStatement(checkData);
//            result = prepare.executeQuery();
//
//            if(result.next()){
//                checkCID = result.getInt("MAX(customer_id)");
//            }

            if(customerId == 0){
                customerId += 1;
            }else if(checkCID == customerId){
                customerId = checkCID + 1;
            }

        }catch(Exception e){e.printStackTrace();}

    }

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


    public void displayUsername(){
//        String user = getData.username;
//        user = user.substring(0, 1).toUpperCase() + user.substring(1);
//        username.setText(user);
        username.setText("Tan");
    }

    public void switchForm(ActionEvent event){

        if(event.getSource() == dashboard_btn){
            dashboard_form.setVisible(true);
            availableBooks_form.setVisible(false);
            purchase_form.setVisible(false);
            publisher_form.setVisible(false);
            author_form.setVisible(false);

            dashboard_btn.setStyle("-fx-background-color: linear-gradient(to top right, #9AD0C2, #E2F4C5);");
            availableBooks_btn.setStyle("-fx-background-color: transparent");
            purchase_btn.setStyle("-fx-background-color: transparent");
            publisher_btn.setStyle("-fx-background-color: transparent");
            author_btn.setStyle("-fx-background-color: transparent");

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

            availableBooks_btn.setStyle("-fx-background-color: linear-gradient(to top right, #9AD0C2, #E2F4C5);");
            dashboard_btn.setStyle("-fx-background-color: transparent");
            purchase_btn.setStyle("-fx-background-color: transparent");
            publisher_btn.setStyle("-fx-background-color: transparent");
            author_btn.setStyle("-fx-background-color: transparent");

            availableBooksShowListData();
            availableBooksSearch();

        }else if(event.getSource() == purchase_btn){
            dashboard_form.setVisible(false);
            availableBooks_form.setVisible(false);
            purchase_form.setVisible(true);
            publisher_form.setVisible(false);
            author_form.setVisible(false);

            purchase_btn.setStyle("-fx-background-color: linear-gradient(to top right, #9AD0C2, #E2F4C5);");
            availableBooks_btn.setStyle("-fx-background-color: transparent");
            dashboard_btn.setStyle("-fx-background-color: transparent");
            publisher_btn.setStyle("-fx-background-color: transparent");
            author_btn.setStyle("-fx-background-color: transparent");

            purchaseBookTitle();
            purchaseBookId();
            purchaseShowCustomerListData();
            purchaseDisplayQTY();
            purchaseDisplayTotal();

        } else if (event.getSource() == publisher_btn) {
            dashboard_form.setVisible(false);
            availableBooks_form.setVisible(false);
            publisher_form.setVisible(true);
            purchase_form.setVisible(false);
            author_form.setVisible(false);

            publisher_btn.setStyle("-fx-background-color: linear-gradient(to top right, #9AD0C2, #E2F4C5);");
            availableBooks_btn.setStyle("-fx-background-color: transparent");
            dashboard_btn.setStyle("-fx-background-color: transparent");
            purchase_btn.setStyle("-fx-background-color: transparent");
            author_btn.setStyle("-fx-background-color: transparent");

            publisherShowListData();

        } else if (event.getSource() == author_btn) {
            dashboard_form.setVisible(false);
            availableBooks_form.setVisible(false);
            publisher_form.setVisible(false);
            purchase_form.setVisible(false);
            author_form.setVisible(true);

            author_btn.setStyle("-fx-background-color: linear-gradient(to top right, #9AD0C2, #E2F4C5);");
            availableBooks_btn.setStyle("-fx-background-color: transparent");
            dashboard_btn.setStyle("-fx-background-color: transparent");
            purchase_btn.setStyle("-fx-background-color: transparent");
            publisher_btn.setStyle("-fx-background-color: transparent");

            authorShowListData();
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

        purchaseBookId();
        purchaseBookTitle();
        purchaseShowCustomerListData();
        purchaseDisplayQTY();
        purchaseDisplayTotal();
        publisherShowListData();
        authorShowListData();
    }
}
