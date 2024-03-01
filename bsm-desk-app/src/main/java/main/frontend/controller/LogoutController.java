package main.frontend.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert; 
import javafx.scene.image.Image;
import javafx.stage.Stage;
import main.frontend.BookstoreManagementApplication;


import java.io.IOException;

public class LogoutController {

    private BookstoreManagementApplication mainApplication;

    @FXML
    private void handleLogout() {
        // Show confirmation alert 
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        ((Stage)alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image("https://cdn0.iconfinder.com/data/icons/shift-free/32/Error-512.png"));
        alert.setTitle("Logout");
        alert.setHeaderText("You are about to logout");
        alert.setContentText("Are you sure you want to logout?");
        
        if(alert.showAndWait().get() == ButtonType.OK) {
            // Close employee view stage if open
            Stage stage = (Stage) mainApplication.getEmployeeViewStage(); 
            if(stage != null) {
                stage.close();
            }
            
            // Show login view
            try {
                mainApplication.showLoginView(); 
            } catch (IOException e) {
                e.printStackTrace();
            }
        } 
    }

    // Setter method 
    public void setMainApplication(BookstoreManagementApplication mainApplication) {
        this.mainApplication = mainApplication;
    }
}