package ganttchart.controller;

import ganttchart.repository.ProjectRepository;
import ganttchart.repository.UserRepository;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by gwszymanowski on 2017-05-27.
 */
public class PrimaryController implements Initializable{

//    @FXML
//    private TreeView projectTreeView;
//    @FXML
//    private TextField nameField;

    @FXML
    private TableView projectTable;

    private UserRepository userRepository = new UserRepository();

    private ProjectRepository projectRepository = new ProjectRepository();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        projectTable.setColumnResizePolicy(p -> true);
    }

}
