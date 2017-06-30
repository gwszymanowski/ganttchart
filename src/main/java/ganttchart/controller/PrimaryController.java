package ganttchart.controller;

import ganttchart.repository.UserRepository;
import impl.org.controlsfx.autocompletion.AutoCompletionTextFieldBinding;
import impl.org.controlsfx.autocompletion.SuggestionProvider;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.util.Callback;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

import java.net.URL;
import java.util.Arrays;
import java.util.Collection;
import java.util.ResourceBundle;

/**
 * Created by gwszymanowski on 2017-05-27.
 */
public class PrimaryController implements Initializable{

    @FXML
    private TreeItem treeItem;
    @FXML
    private Label name;
    @FXML
    private TextField nameField;
    @FXML
    private Label leader;
    @FXML
    private TextField leaderField;

    private UserRepository userRepository = new UserRepository();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        new AutoCompletionTextFieldBinding(leaderField, param -> userRepository.getAll());
    }
}
