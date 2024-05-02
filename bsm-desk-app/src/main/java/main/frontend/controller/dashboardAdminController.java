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
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.layout.AnchorPane;

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
    private Button add_acc_btn;

    @FXML
    private TableColumn<?, ?> userAccount_col_accountID;

    @FXML
    private TableColumn<?, ?> userAccount_col_action;

    @FXML
    private TableColumn<?, ?> userAccount_col_email;

    @FXML
    private TableColumn<?, ?> userAccount_col_password;

    @FXML
    private TableColumn<?, ?> userAccount_col_status;

    @FXML
    private TableColumn<?, ?> userAccount_col_username;

    @FXML
    private AnchorPane userAccount_form;

    @FXML
    private AnchorPane profile_setting;

    @FXML
    private TableView<?> userAccount_tableView;

    @FXML
    private ComboBox<?> time_range;

public void switchForm(ActionEvent event){
    if(event.getSource() == revenue_btn){
        revenue_form.setVisible(true);
        userAccount_form.setVisible(false);
        profile_setting.setVisible(false);
    } else if (event.getSource() == userAccount_btn) {
        revenue_form.setVisible(false);
        userAccount_form.setVisible(true);
        profile_setting.setVisible(false);
    }else if (event.getSource() == profile_btn) {
        revenue_form.setVisible(false);
        userAccount_form.setVisible(false);
        profile_setting.setVisible(true);
    }
    else if (event.getSource() == add_acc_btn) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/frontend/fxml/userAccount.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        revenue_form.setVisible(false);
        userAccount_form.setVisible(false);
        profile_setting.setVisible(true);
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
        time_range.getScene().getWindow().hide();

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
