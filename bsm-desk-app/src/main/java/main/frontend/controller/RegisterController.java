package main.frontend.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class RegisterController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private void handleRegister() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        // Validate input
        if(username.isEmpty()) {
            showAlert("Error", "Username cannot be empty");
            return;
        }
        
        if(password.isEmpty()) {
            showAlert("Error", "Password cannot be empty");
            return; 
        }
        
        if(!password.equals(confirmPassword)) {
            showAlert("Error", "Passwords do not match");
            return;
        }

        // Additional validation logic

        // If valid, register user
        registerUser(username, password);

        // Show registration success alert
        showAlert("Success","User registered successfully");
    }

    private void registerUser(String username, String password) {
        // Logic to register user
        // Save credentials, send verification email etc.

        // For demo
        System.out.println("Registered user: " + username);
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}