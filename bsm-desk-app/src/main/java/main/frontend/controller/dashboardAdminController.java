package main.frontend.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
    private TableView<?> userAccount_tableView;

public void switchForm(ActionEvent event){
    if(event.getSource() == revenue_btn){
        revenue_form.setVisible(true);
        userAccount_form.setVisible(false);
    } else if (event.getSource() == userAccount_btn) {
        revenue_form.setVisible(false);
        userAccount_form.setVisible(true);
    }
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
    }
}
