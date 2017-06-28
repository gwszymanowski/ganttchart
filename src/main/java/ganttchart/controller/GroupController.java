package ganttchart.controller;

import ganttchart.model.Project;
import ganttchart.model.ProjectGroup;
import ganttchart.model.User;
import ganttchart.repository.ProjectGroupRepository;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by gwszymanowski on 2017-06-15.
 */
public class GroupController implements Initializable {

    @FXML
    private TreeView<String> groupTreeView;
    @FXML
    private Label name;
    @FXML
    private TextField nameField;

    private ProjectGroupRepository repo = new ProjectGroupRepository();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        List<ProjectGroup> groups = repo.getAll();

        TreeItem<String> rootItem = new TreeItem<> ("Grupy");

        for(ProjectGroup g : groups) {

            TreeItem<String> rootItem1 = new TreeItem<> (g.getName());
            rootItem1.setExpanded(true);

            for(User u : g.getMembers()) {
                TreeItem<String> item = new TreeItem<> (u.getFirstname() + " " + u.getLastname());
                rootItem1.getChildren().add(item);
            }

            rootItem.getChildren().add(rootItem1);
        }
        groupTreeView.setRoot(rootItem);
    }

    private TreeView<String> createTreeView(TreeItem<String> root1, TreeItem<String> root2) {
        TreeItem<String> dummyRoot = new TreeItem<>();
        dummyRoot.getChildren().addAll(root1, root2);
        TreeView<String> tree = new TreeView<>(dummyRoot);
        tree.setShowRoot(true);
        return tree ;
    }

    public void createProjectAction(ActionEvent actionEvent) {
        if(validate()) {
            repo.save(new ProjectGroup(nameField.getText()));
        }
    }

    private boolean validate() {
        if(nameField.getText().length() == 0)
         return false;
        return repo.isEmpty(nameField.getText());
    }
}
