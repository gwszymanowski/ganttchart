package ganttchart.controller;

import ganttchart.model.Person;
import ganttchart.repository.PersonRepository;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by gwszymanowski on 2017-07-11.
 */
public class PersonController implements Initializable {

    @FXML
    private TableView personTable;

    @FXML
    private TableColumn<Person, String> firstnameColumn;

    @FXML
    private TableColumn<Person, String> lastnameColumn;

    private PersonRepository repo = new PersonRepository();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        personTable.setColumnResizePolicy(p -> true);
        firstnameColumn.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        lastnameColumn.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        refresh();
    }

    public void refresh() {
        personTable.getItems().setAll(repo.getAll());
    }
}
