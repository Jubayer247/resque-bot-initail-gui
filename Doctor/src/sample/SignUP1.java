package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.ArrayList;

public class SignUP1 extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("doctor.fxml"));
        primaryStage.setTitle("Doctor /Patient collaboration ");
        primaryStage.setScene(new Scene(root, 400, 300));
        primaryStage.show();
        ChoiceBox choiceBox=(ChoiceBox)root.lookup("#choice_box");
        Button loginBtn=(Button)root.lookup("#continue_btn");

        choiceBox.setItems(FXCollections.observableArrayList(
                "Doctor",
                new Separator(), "Patient ")
        );

        loginBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
          //      String s=email.getText();
            //    String s2=passwordField.getText();
              //  System.out.println(s+"/n "+s2);
            }
        });

    }


    public static void main(String[] args) {
        launch(args);
    }
}
