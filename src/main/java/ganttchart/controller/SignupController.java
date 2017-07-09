package ganttchart.controller;

import ganttchart.model.Person;
import ganttchart.repository.UserRepository;
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
public class SignupController {

    @FXML
    private TextField firstnameField;
    @FXML
    private TextField lastnameField;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button signup;
    @FXML
    private Button returnButton;

    private UserRepository repository = new UserRepository();

    @FXML
    private void signupAction(ActionEvent event) {
      if(!validateUser())
          createUser();
      else
          System.out.println("Taki uzytkownik istnieje");
    }

    @FXML
    public void returnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = null;
        Parent root = null;
        if(actionEvent.getSource()== returnButton){
            stage=(Stage) returnButton.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/login.fxml"));
            root = loader.load();
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    private boolean validateUser() {
        if(firstnameField.getText().length() == 0 || lastnameField.getText().length() == 0 || usernameField.getText().length() == 0 || passwordField.getText().length() == 0)
            return false;
        return repository.validateByUsername(usernameField.getText());
    }

    private void createUser() {
        Person person = new Person(firstnameField.getText(), lastnameField.getText());
        repository.save(person);
    }

}
