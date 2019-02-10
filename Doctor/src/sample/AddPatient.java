package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class AddPatient extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("PatientSignUp.fxml"));
        primaryStage.setTitle("Doctor /Patient collaboration ");
        primaryStage.setScene(new Scene(root, 600, 600));
        primaryStage.show();
        TextField f_name=(TextField)root.lookup("#f_name");
        TextField l_name=(TextField)root.lookup("#l_name");
        TextField blood_group=(TextField)root.lookup("#blood_group");
        TextField age=(TextField)root.lookup("#age");
        RadioButton r_male=(RadioButton)root.lookup("#r_male");
        RadioButton r_female=(RadioButton)root.lookup("#r_female");
        TextField nid=(TextField)root.lookup("#nid");
        TextField weight=(TextField)root.lookup("#weight");
        TextField email=(TextField)root.lookup("#email");
        TextField password=(TextField)root.lookup("#password");
        Button singUpBtn=(Button)root.lookup("#signUpBtn");
        final String gender=r_male.isSelected()?"Male":"Female";
        boolean m=r_male.isSelected();

        singUpBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {


                CreateUser.addPatient(f_name.getText(),l_name.getText(),blood_group.getText(),Integer.parseInt(weight.getText()),gender,Integer.parseInt(age.getText()),email.getText(),password.getText());
            }
        });

    }


    public static void main(String[] args) {
        launch(args);
    }
}
