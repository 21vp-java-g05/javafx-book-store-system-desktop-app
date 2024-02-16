package main.frontend;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.Screen;
import main.frontend.controller.LoginController;

import java.io.IOException;

public class BookstoreManagementApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // Load the login view initially
        FXMLLoader loginLoader = new FXMLLoader(BookstoreManagementApplication.class.getResource("/main/frontend/fxml/login-view.fxml"));
        Scene loginScene = new Scene(loginLoader.load());

        // Set up the login controller to handle login events
        LoginController loginController = loginLoader.getController();
        loginController.setMainApplication(this);
        stage.getIcons().add(new Image("https://upload.wikimedia.org/wikipedia/commons/3/3a/Book-icon-bible.png"));
        stage.setTitle("Login");
        stage.setScene(loginScene);
        stage.show();
    }

    private void centerStage(Stage stage) {
        // Get the primary screen bounds
        double screenWidth = Screen.getPrimary().getBounds().getWidth();
        double screenHeight = Screen.getPrimary().getBounds().getHeight();

        // Calculate the position to center the stage
        double stageWidth = stage.getWidth();
        double stageHeight = stage.getHeight();
        double x = (screenWidth - stageWidth) / 2;
        double y = (screenHeight - stageHeight) / 2;

        // Set the stage position
        stage.setX(x);
        stage.setY(y);
    }

    public void showMainView(Stage stage) throws IOException {
        // Load the main view after successful login
        FXMLLoader mainLoader = new FXMLLoader(BookstoreManagementApplication.class.getResource("/main/frontend/fxml/bookstore-management-view.fxml"));
        Scene mainScene = new Scene(mainLoader.load());

        stage.getIcons().add(new Image("https://upload.wikimedia.org/wikipedia/commons/3/3a/Book-icon-bible.png"));
        stage.setTitle("Bookstore Management");
        stage.setScene(mainScene);
        centerStage(stage);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}