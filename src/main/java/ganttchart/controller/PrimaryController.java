package ganttchart.controller;

import ganttchart.gui.elements.ProjectCell;
import ganttchart.model.Project;
import ganttchart.model.User;
import ganttchart.entity.ProjectRepository;
import ganttchart.entity.UserRepository;
import impl.org.controlsfx.autocompletion.AutoCompletionTextFieldBinding;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by gwszymanowski on 2017-05-27.
 */
public class PrimaryController implements Initializable{

    @FXML
    private TreeView projectTreeView;
    @FXML
    private TextField nameField;
    @FXML
    private TextField leaderField;

    private UserRepository userRepository = new UserRepository();

    private ProjectRepository projectRepository = new ProjectRepository();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        new AutoCompletionTextFieldBinding(leaderField, param -> userRepository.getAll());

        List<Project> projects = projectRepository.getAll();

        TreeItem<String> rootItem = new TreeItem<> ("Projects");

        projectTreeView.setCellFactory(param -> new ProjectCell());

        for(Project p : projects) {
            TreeItem<String> rootItem1 = new TreeItem<> (p.getName());
            rootItem1.setExpanded(true);

            for(User u : p.getMembers()) {
                TreeItem<String> item = new TreeItem<> (u.toString());
                rootItem1.getChildren().add(item);
            }

            rootItem.getChildren().add(rootItem1);
        }
        projectTreeView.setRoot(rootItem);
    }


    public void createProjectAction() {
        String name = nameField.getText();

        if(!projectRepository.validateByName(name) && name.length() > 0) {
            Project p = new Project(name);
            projectRepository.save(p);
        }
    }
}
