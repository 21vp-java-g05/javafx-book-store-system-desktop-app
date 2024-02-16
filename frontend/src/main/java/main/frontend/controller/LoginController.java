package main.frontend.controller;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import main.frontend.BookstoreManagementApplication;

import java.io.IOException;

public class LoginController {


    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    private BookstoreManagementApplication mainApplication;

    // This method is called when the login button is clicked
    @FXML
    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Perform login authentication here
        // For demonstration purposes, let's assume it's successful
        if (username.equals("admin") && password.equals("admin")) {
            try {
                // Close the login stage if necessary
                Stage stage = (Stage) usernameField.getScene().getWindow();
                mainApplication.showMainView(stage);
            } catch (IOException e) {
                e.printStackTrace(); // Handle the exception appropriately
            }
        } else {
            // Show an error message
            Alert alert = new Alert(Alert.AlertType.ERROR);
            ((Stage)alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image("https://cdn0.iconfinder.com/data/icons/shift-free/32/Error-512.png"));
            alert.setTitle("Login Failed");
            alert.setHeaderText(null);
            alert.setContentText("Invalid username or password. Please try again.");
            alert.showAndWait();
        }
    }


    // Setter method for main application reference
    public void setMainApplication(BookstoreManagementApplication mainApplication) {
        this.mainApplication = mainApplication;
    }
}
