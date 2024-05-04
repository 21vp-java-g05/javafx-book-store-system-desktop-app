package main.frontend.controller;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.scene.control.cell.PropertyValueFactory;

import javafx.stage.Stage;
import java.net.URL;

import java.util.ResourceBundle;
import java.text.SimpleDateFormat;

import java.util.*;
import java.util.Date;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.layout.AnchorPane;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

import main.frontend.backend.utils.DBconnect;

public class dashboardAdminController implements Initializable {
    @FXML
    private Label current_form;

    @FXML
    private Label date_time;

    @FXML
    private AnchorPane mainAdmin_form;

    @FXML
    private Label nav_adminID;

    @FXML
    private Label nav_username;

    @FXML
    private Button profile_btn;

    @FXML
    private Button log_out_btn_admin;

    @FXML
    private Button revenue_btn;

    @FXML
    private LineChart<?, ?> revenue_char_bookCategory;

    @FXML
    private LineChart<?, ?> revenue_char_books;

    @FXML
    private LineChart<?, ?> revenue_char_customers;

    @FXML
    private LineChart<?, ?> revenue_char_employees;

    @FXML
    private AnchorPane revenue_form;

    @FXML
    private Label top_username;

    @FXML
    private Button userAccount_btn;

    @FXML
    private AnchorPane profileSetting_form;

    @FXML
    private TextField userAccount_accountID;

    @FXML
    private Button userAccount_addBtn;

    @FXML
    private TableColumn<userAccountData, Integer> userAccount_col_accountID;

    @FXML
    private TableColumn<userAccountData, String> userAccount_col_role;

    @FXML
    private TableColumn<userAccountData, String> userAccount_col_email;

    @FXML
    private TableColumn<userAccountData, String> userAccount_col_password;

    @FXML
    private TableColumn<userAccountData, String> userAccount_col_status;

    @FXML
    private TableColumn<userAccountData, String> userAccount_col_username;

    @FXML
    private TextField userAccount_email;

    @FXML
    private AnchorPane userAccount_form;

    @FXML
    private TextField userAccount_password;

    @FXML
    private ComboBox<?> userAccount_role;

    @FXML

    private ComboBox<?> userAccount_status;

    @FXML
    private TableView<userAccountData> userAccount_tableView;

    @FXML
    private TextField userAccount_username;

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    public void userAccountRoleList(){
        List<String> listR = new ArrayList<>();
        for(String data : DataAdmin.role){
            listR.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listR);
        userAccount_role.setItems(listData);
    }

    public void userAccountStatusList(){
        List<String> listS = new ArrayList<>();
        for(String data : DataAdmin.status){
            listS.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listS);
        userAccount_status.setItems(listData);
    }

    public ObservableList<userAccountData> userAccountGetData() {

        ObservableList<userAccountData> listData = FXCollections.observableArrayList();

//        String sql = "SELECT * FROM appointment WHERE date_delete IS NULL and admin = '"
//                + DataAdmin.admin_ID + "'";
//
//        connect = Database.connectDB();
//
//        try {
//
//            prepare = connect.prepareStatement(sql);
//            result = prepare.executeQuery();
//
//            userAccountData appData;
//
//            while (result.next()) {
////            Integer accountID, String username, String status, String role, String password, String email
//
//                appData = new userAccountData(result.getInt("accountID"),
//                        result.getString("username"), result.getString("status"),
//                        result.getString("role"), result.getString("password"),
//                        result.getString("email"));
//                // STORE ALL DATA
//                listData.add(appData);
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return listData;
    }

    public ObservableList<userAccountData> userAccountListData;

    public void userAccountShowData() {
        userAccountListData = userAccountGetData();

        userAccount_col_accountID.setCellValueFactory(new PropertyValueFactory<>("accountID"));
        userAccount_col_username.setCellValueFactory(new PropertyValueFactory<>("username"));
        userAccount_col_password.setCellValueFactory(new PropertyValueFactory<>("password"));
        userAccount_col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        userAccount_col_role.setCellValueFactory(new PropertyValueFactory<>("role"));
        userAccount_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));

        userAccount_tableView.setItems(userAccountListData);
    }
// TO SELECT THE DATA PER ROW IN THE TABLE

    public void appointmentSelect() {

        userAccountData appData = userAccount_tableView.getSelectionModel().getSelectedItem();
        int num = userAccount_tableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        userAccount_accountID.setText("" + appData.getAccountID());
        userAccount_username.setText(appData.getUsername());
        userAccount_password.setText(appData.getPassword());
        userAccount_email.setText(appData.getmail());
//        userAccount_status.getSelectionModel().select(appData.getStatus());
//        userAccount_role.getSelectionModel().select(appData.getRole());

    }

    @FXML
    private ComboBox<?> time_range;

    public void switchForm(ActionEvent event){

        if(event.getSource() == revenue_btn){
            revenue_form.setVisible(true);
            userAccount_form.setVisible(false);
            profileSetting_form.setVisible(false);
        } else if (event.getSource() == userAccount_btn) {
            revenue_form.setVisible(false);
            userAccount_form.setVisible(true);
            profileSetting_form.setVisible(false);
        }else if (event.getSource() == profile_btn) {
            revenue_form.setVisible(false);
            userAccount_form.setVisible(false);
            profileSetting_form.setVisible(true);
        }
    }

    public void updateInfoAdmin()
    {
        Alert alert;
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Message");
        alert.setHeaderText(null);
        alert.setContentText("Your information is successfully updated");
        alert.showAndWait();
    }

    public void switchTimeRange() {

        if (time_range.getSelectionModel().getSelectedItem() == "A week ago") {

        } else if (time_range.getSelectionModel().getSelectedItem() == "A month ago") {

        }else if (time_range.getSelectionModel().getSelectedItem() == "From date to date")
        {

        }


    }

    public void timeRangeList(){
        List<String> listU = new ArrayList<>();

        for (String data : Users.timeRange){
            listU.add(data);
        }
        ObservableList listData = FXCollections.observableList(listU);
        time_range.setItems(listData);
    }

    public void displayAdminIDUsername(){
        String sql = "SELECT * FORM admin WHERE username = '" + DataAdmin.admin_username+ "'";

//        connect = Database.connectDB();
//
//        try{
//            prepare = connect.prepareStatement(sql);
//            result = prepare.executeQuerry();
//
//            if(result.next()){
//                nav_adminID.setText(result.getString("admin_ID"));
//                String tempUsername = result.getString("username");
//                tempUsername = tempUsername.substring(0, 1).toUpperCase() + tempUsername.substring(1);
//                nav_username.setText(tempUsername);
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }
    }

    public void runTime(){
        new Thread(){
            public void run(){
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                while(true){
                    try{
                        Thread.sleep(1000);
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                    Platform.runLater(() -> {
                        date_time.setText(format.format(new Date()));
                    });
                }
            }
        }.start();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources){
        runTime();
        displayAdminIDUsername();
        userAccountRoleList();
        userAccountStatusList();

        timeRangeList();

    }

    public void Logout(ActionEvent event){
        if (event.getSource() == log_out_btn_admin) {
            try {
                Stage currentStage = (Stage) log_out_btn_admin.getScene().getWindow();
                currentStage.close();

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/frontend/fxml/FXMLDocument.fxml"));
                Parent root = loader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}