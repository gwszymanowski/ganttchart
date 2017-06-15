package ganttchart.controller;

import ganttchart.model.ProjectGroup;
import ganttchart.model.User;
import ganttchart.repository.ProjectGroupRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

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
    private TreeView<String> groupList = new TreeView<String>();

    @FXML
    private Label name;

    @FXML
    private TextField nameField;

    private ProjectGroupRepository repo = new ProjectGroupRepository();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<ProjectGroup> groups = repo.getAll();

        List<TreeItem<String>> lista = new LinkedList<TreeItem<String>>();
        for(ProjectGroup group : groups) {
            TreeItem<String> groupItem = new TreeItem<>();
            groupItem.setValue(group.getName());
            groupItem.getChildren().addAll(group.getTreeItem());
            lista.add(groupItem);
        }


       // groupList.getRoot().getChildren().addAll(lista);


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
