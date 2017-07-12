package ganttchart.controller;

import ganttchart.gui.elements.ProjectCell;
import ganttchart.model.Project;
import ganttchart.repository.ProjectRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by gwszymanowski on 2017-05-27.
 */
public class PrimaryController implements Initializable{

    @FXML
    private TableView projectTable;

    @FXML
    private TableColumn<Project, String> nameColumn;

    @FXML
    private TableColumn<Project, String> optionsColumn;

    private ProjectRepository repo = new ProjectRepository();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        projectTable.setColumnResizePolicy(p -> true);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        optionsColumn.setCellFactory(column -> {
            return new ProjectCell();
        });
        projectTable.getItems().setAll(repo.getAll());
    }

}
