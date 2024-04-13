package main.frontend;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.frontend.backend.users.Account;

import java.util.Objects;

public class BookstoreManagementApplication extends Application {
    private double x = 0;
    private double y = 0;

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(BookstoreManagementApplication.class.getResource("/main/frontend/fxml/FXMLDocument.fxml")));

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

    public static void main(String[] args) {
//            Account a2 = new Account("Pham Nguyen Gia Khiem", "pngkhiem21@vp.fitus.edu.vn", "cyderxda", "Cyderglxk03", 1);
//        System.out.println("Account 2 (copy of Account 1):");
//        System.out.println(a2);
//        System.out.println();
//
////        a2.add_toDatabase();
//
//        String hashedPassword = a2.hashPassword("Cyderglxk03");
//        System.out.println("Hashed password: " + hashedPassword);
//
////        boolean isLoggedIn = a2.login("cyderxda", "Cyderglxk03");
////        System.out.println("Login successed is " + isLoggedIn);
        launch(args);
    }
}