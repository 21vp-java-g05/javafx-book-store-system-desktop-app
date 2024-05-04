package main.frontend.controller;

import java.net.URL;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.frontend.BookstoreManagementApplication;

public class FXMLDocumentController implements Initializable {

    @FXML
    public Button loginBtn1;
    @FXML
    public PasswordField password1;
    @FXML
    public TextField username1;
    @FXML
    public AnchorPane main_form1;

    @FXML
    private AnchorPane main_form;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Button loginBtn;

    @FXML
    private Button close;

    @FXML
    private ComboBox<?> login_user;
//    private Connection connect;
//    private PreparedStatement prepare;
//    private ResultSet result;

    private double x = 0;
    private double y = 0;

    public void loginAdmin(){

//        connect = database.connectDb();
//
//        String sql = "SELECT * FROM ACCOUNT WHERE username = ? and password = ?"; // admin is our table name

        try{
            Alert alert;

//            prepare = connect.prepareStatement(sql);
//            prepare.setString(1, username.getText());
//            prepare.setString(2, password.getText());
//
//            result = prepare.executeQuery();

            if(username.getText().isEmpty() || password.getText().isEmpty()){
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            }else{
//                if(result.next()){
//                    // IF CORRECT USERNAME AND PASSWORD
//
//                    getData.username = username.getText();

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Login");
                    alert.showAndWait();

                    // TO HIDE YOUR LOGIN FORM
                    Stage loginStage = (Stage) username.getScene().getWindow();
                    loginStage.close();
//
                    // LINK YOUR DASHBOARD FORM : )
                    Parent root = FXMLLoader.load(Objects.requireNonNull(BookstoreManagementApplication.class.getResource("/main/frontend/fxml/dashboardAdmin.fxml")));
                    Stage stage = new Stage();
                    Scene scene = new Scene(root);

                    root.setOnMousePressed((MouseEvent event) ->{
                        x = event.getSceneX();
                        y = event.getSceneY();
                    });
//
                    root.setOnMouseDragged((MouseEvent event) ->{
                        stage.setX(event.getScreenX() - x);
                        stage.setY(event.getScreenY() - y);
                    });

                    stage.initStyle(StageStyle.TRANSPARENT);

                    stage.setScene(scene);
                    stage.show();

//                }else{ // IF WRONG USERNAME OR PASSWORD
//                    alert = new Alert(AlertType.ERROR);
//                    alert.setTitle("Error Message");
//                    alert.setHeaderText(null);
//                    alert.setContentText("Wrong Username/Password");
//                    alert.showAndWait();
//                }
            }

        }catch(Exception e){e.printStackTrace();}

    }

    public void loginEmployee(){

//        connect = database.connectDb();
//
//        String sql = "SELECT * FROM admin WHERE username = ? and password = ?"; // admin is our table name

        try{
            Alert alert;

//            prepare = connect.prepareStatement(sql);
//            prepare.setString(1, username.getText());
//            prepare.setString(2, password.getText());
//
//            result = prepare.executeQuery();

            if(username1.getText().isEmpty() || password1.getText().isEmpty()){
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            }else{
//                if(result.next()){
//                    // IF CORRECT USERNAME AND PASSWORD
//
//                    getData.username = username.getText();

                alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Login");
                alert.showAndWait();

                // TO HIDE YOUR LOGIN FORM
//                    loginBtn.getScene().getWindow().hide();
//
                // LINK YOUR DASHBOARD FORM : )
                Parent root = FXMLLoader.load(Objects.requireNonNull(BookstoreManagementApplication.class.getResource("/main/frontend/fxml/dashboard.fxml")));
                Stage stage = new Stage();
                Scene scene = new Scene(root);

                root.setOnMousePressed((MouseEvent event) ->{
                    x = event.getSceneX();
                    y = event.getSceneY();
                });

                root.setOnMouseDragged((MouseEvent event) ->{
                    stage.setX(event.getScreenX() - x);
                    stage.setY(event.getScreenY() - y);
                });

                stage.initStyle(StageStyle.TRANSPARENT);

                stage.setScene(scene);
                stage.show();

//                }else{ // IF WRONG USERNAME OR PASSWORD
//                    alert = new Alert(AlertType.ERROR);
//                    alert.setTitle("Error Message");
//                    alert.setHeaderText(null);
//                    alert.setContentText("Wrong Username/Password");
//                    alert.showAndWait();
//                }
            }

        }catch(Exception e){e.printStackTrace();}

    }
    public void userList(){
        List<String> listU = new ArrayList<>();

        for (String data : Users.user){
            listU.add(data);
        }
        ObservableList listData = FXCollections.observableList(listU);
        login_user.setItems(listData);
    }

    public void switchPage() {

        if (login_user.getSelectionModel().getSelectedItem() == "Administrator") {

            try {

                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/main/frontend/fxml/FXMLDocument.fxml")));
                Stage stage = new Stage();

                stage.setTitle("Administrator");


                stage.setScene(new Scene(root));
                stage.show();

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (login_user.getSelectionModel().getSelectedItem() == "Employee") {

            try {

                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/main/frontend/fxml/FXMLEmployee.fxml")));
                Stage stage = new Stage();

                stage.setTitle("Employee");


                stage.setScene(new Scene(root));
                stage.show();

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        login_user.getScene().getWindow().hide();

    }
    public void close(){
        System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        userList();
    }

}
