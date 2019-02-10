package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

//import java.awt.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Doctor /Patient collaboration ");
        primaryStage.setScene(new Scene(root, 600, 600));
        primaryStage.show();
        TextField email=(TextField) root.lookup("#email_text");
        PasswordField passwordField=(PasswordField)root.lookup("#password_text");
        Button loginBtn=(Button)root.lookup("#loginBtn");
        Button sign_up=(Button) root.lookup("#open_sign_up");


        sign_up.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.close();
                SignUP1.launch(null);
            }
        });

    }


    public static void main(String[] args) {
        launch(args);
    }
}
