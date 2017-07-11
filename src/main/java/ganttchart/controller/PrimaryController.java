package ganttchart.controller;

import ganttchart.model.Project;
import ganttchart.repository.ProjectRepository;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by gwszymanowski on 2017-05-27.
 */
public class PrimaryController implements Initializable{

    @FXML
    private TableView projectTable;

//    @FXML
//    private TableColumn<Project, String> nameColumn;

    private ProjectRepository repo = new ProjectRepository();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        projectTable.setColumnResizePolicy(p -> true);
        projectTable.getItems().setAll(repo.getAll());
    }

}
