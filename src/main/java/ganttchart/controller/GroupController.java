package ganttchart.controller;

import ganttchart.gui.elements.GroupCell;
import ganttchart.model.Project;
import ganttchart.model.ProjectGroup;
import ganttchart.model.User;
import ganttchart.repository.ProjectGroupRepository;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import org.controlsfx.control.PopOver;

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
    private TextField nameField;

    private ProjectGroupRepository repo = new ProjectGroupRepository();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<ProjectGroup> groups = repo.getAll();

        TreeItem<String> rootItem = new TreeItem<> ("Groups");

        groupTreeView.setCellFactory(param -> new GroupCell());

        for(ProjectGroup g : groups) {
            TreeItem<String> rootItem1 = new TreeItem<> (g.getName());
            rootItem1.setExpanded(true);

            for(User u : g.getMembers()) {
                TreeItem<String> item = new TreeItem<> (u.toString());
                rootItem1.getChildren().add(item);
            }

            rootItem.getChildren().add(rootItem1);
        }
        groupTreeView.setRoot(rootItem);
    }

    public void createProjectAction() {
        if(validate()) {
            repo.save(new ProjectGroup(nameField.getText()));

            groupTreeView.refresh();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("New group has been created");

            alert.showAndWait();
        }
    }

    private boolean validate() {
        if(nameField.getText().length() == 0)
         return false;
        return repo.isEmpty(nameField.getText());
    }
}
