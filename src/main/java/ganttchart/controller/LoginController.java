package ganttchart.controller;

import ganttchart.entity.UserRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by gwszymanowski on 2017-05-27.
 */
public class LoginController {

    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button signup;
    @FXML
    private Button login;

    private UserRepository repo = new UserRepository();

    public void signupView(ActionEvent actionEvent) throws IOException {
        Stage stage = null;
        Parent root = null;
        if(actionEvent.getSource()== signup){
            stage=(Stage) signup.getScene().getWindow();
            stage.setTitle("Sign up");
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/signup.fxml"));
            root = loader.load();
        }

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void loginAction(ActionEvent actionEvent) throws IOException {
        if(validate()) {
            Stage stage = null;
            Parent root = null;
            if(actionEvent.getSource()== login){
                stage=(Stage) login.getScene().getWindow();
                stage.setTitle("Available projects");
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/primary.fxml"));
                root = loader.load();
            }

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    private boolean validate() {
        if(usernameField.getText().length() == 0 || passwordField.getText().length() == 0)
            return false;
        return repo.validate(usernameField.getText(), passwordField.getText());
    }

}
