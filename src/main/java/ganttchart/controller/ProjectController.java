package ganttchart.controller;

import ganttchart.model.Project;
import ganttchart.repository.ProjectRepository;
import ganttchart.util.TableColumnFactory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;

import java.net.URL;
import java.time.Instant;
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

    private String title;
    private ProjectRepository projectRepository = new ProjectRepository();

    public ProjectController(String title) {
        this.title = title;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        titleLabel.setText(title);
        startDateLabel.setText("Startdate is: ");
        todayIsLabel.setText("Today is: " + Instant.now().toString());

        TableColumnFactory factory = new TableColumnFactory();
        duration.setGraphic(factory.getRotated("Duration(days)"));
        completed.setGraphic(factory.getRotated("Completed"));
        workingDays.setGraphic(factory.getRotated("Working days"));
        daysCompleted.setGraphic(factory.getRotated("Days completed"));
        daysRemaining.setGraphic(factory.getRotated("Days remaining"));

        Project p = projectRepository.findByName(title);
    }
}
