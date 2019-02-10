package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddDoctor extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("doctorSignUp.fxml"));
        primaryStage.setTitle("Doctor /Patient collaboration ");
        primaryStage.setScene(new Scene(root, 600, 600));
        primaryStage.show();
        TextField f_name=(TextField)root.lookup("#f_name");
        TextField l_name=(TextField)root.lookup("#l_name");
        TextArea edu_info=(TextArea)root.lookup("#edu_info");
        TextArea spec=(TextArea)root.lookup("#spec");
        TextArea address=(TextArea)root.lookup("#address");
        TextField nid=(TextField)root.lookup("#nid");
        TextField fee=(TextField)root.lookup("#fee");
        TextField email=(TextField)root.lookup("#email");
        TextField password=(TextField)root.lookup("#password");
        Button singUpBtn=(Button)root.lookup("#signUpBtn");

        singUpBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                CreateUser.addDoctor(f_name.getText(),l_name.getText(),edu_info.getText(),spec.getText(),address.getText(),nid.getText(),Integer.parseInt(fee.getText()),email.getText(),password.getText());
            }
        });

    }


    public static void main(String[] args) {
        launch(args);
    }
}
