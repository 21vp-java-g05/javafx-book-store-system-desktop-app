package main.frontend.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class updateUserInfoController implements Initializable {
    @FXML
    private TextField acc_ID;

    @FXML
    private TextField email;

    @FXML
    private TextField fullname;

    @FXML
    private ComboBox<String> gender;

    @FXML
    private TextField password;

    @FXML
    private ComboBox<String> status;

    @FXML
    private Button update_btn;


    public void setField(){
        acc_ID.setText(String.valueOf(customerData.customerID));
        fullname.setText(customerData.fullName);
        gender.getSelectionModel().select(customerData.gender);
        email.setText(customerData.email);
        status.getSelectionModel().select(customerData.status);

    }

    public void genderList(){
        List<String> listU = new ArrayList<>();

        for (String data : Users.timeRange){
            listU.add(data);
        }
        ObservableList listData = FXCollections.observableList(listU);
        gender.setItems(listData);
    }

    public void statusList(){
        List<String> listU = new ArrayList<>();

        for (String data : Users.timeRange){
            listU.add(data);
        }
        ObservableList listData = FXCollections.observableList(listU);
        status.setItems(listData);
    }
    public void updateInfo(){
        Alert alert;
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Message");
        alert.setHeaderText(null);
        alert.setContentText("Information updated successfully");
        alert.showAndWait();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setField();
    }
}
