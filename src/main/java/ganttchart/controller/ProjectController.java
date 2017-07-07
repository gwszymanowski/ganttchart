package ganttchart.controller;

import ganttchart.model.Project;
import ganttchart.entity.ProjectRepository;
import ganttchart.util.FileUtil;
import ganttchart.util.TableColumnFactory;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by gwszymanowski on 2017-06-30.
 */
public class ProjectController implements Initializable {

    @FXML
    private TableColumn duration;

    @FXML
    private TableColumn completed;

    @FXML
    private TableColumn workingDays;

    @FXML
    private TableColumn daysCompleted;

    @FXML
    private TableColumn daysRemaining;

    @FXML
    private Label titleLabel;

    @FXML
    private Label startDateLabel;

    @FXML
    private Label todayIsLabel;

    @FXML
    private TableView datesTableView;

    private String title;
    private ProjectRepository projectRepository = new ProjectRepository();

    public ProjectController(String title) {
        this.title = title;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        titleLabel.setText(title);

        Project p = projectRepository.findByName(title);

        startDateLabel.setText("Startdate is: " + FileUtil.convertDateToString(p.getStartDate()));
        todayIsLabel.setText("Today is: " + FileUtil.convertDateToString(LocalDate.now()));

        TableColumnFactory factory = new TableColumnFactory();
        duration.setGraphic(factory.getRotated("Duration(days)"));
        completed.setGraphic(factory.getRotated("(%) Completed"));
        workingDays.setGraphic(factory.getRotated("Working days"));
        daysCompleted.setGraphic(factory.getRotated("Days completed"));
        daysRemaining.setGraphic(factory.getRotated("Days remaining"));

        LocalDate first = p.getStartDate();
        LocalDate last = p.getLastDay();

//        ObservableList<TableColumn> list = datesTableView.getColumns();
//        while(!first.equals(last)) {
//            TableColumn tb = new TableColumn();
//            tb.setGraphic(factory.getRotated(FileUtil.convertDateToString(first))); //rotates the String
//            list.add(tb);
//            first = first.plusDays(1);
//        }
//        datesTableView.setItems(list);


    }
}
