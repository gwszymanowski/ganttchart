package ganttchart.controller;

import ganttchart.gui.elements.Dialogable;
import ganttchart.gui.elements.MembersDialog;
import ganttchart.gui.elements.NewAssignmentDialog;
import ganttchart.model.Project;
import ganttchart.repository.ProjectRepository;
import ganttchart.service.ProjectService;
import ganttchart.util.FileUtil;
import ganttchart.util.TableColumnFactory;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;

import java.net.URL;
import java.time.LocalDate;
import java.util.*;

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

    @FXML
    private Button newAssignment;

    @FXML
    private Button memberList;

    private String title;
    private ProjectRepository projectRepository = new ProjectRepository();

    public ProjectController(String title) {
        this.title = title;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Project p = projectRepository.findByName(title);

        initializeLabels(p);
        initializeTableView();
        initializeDatesTableView(p);
    }

    private void initializeLabels(Project p) {
        titleLabel.setText(title);
        memberList.setOnAction(new DialogButtonAction(new MembersDialog()));
        newAssignment.setOnAction(new DialogButtonAction(new NewAssignmentDialog()));
        startDateLabel.setText("Startdate is: " + FileUtil.convertDateToString(p.getStartDate()));
        todayIsLabel.setText("Today is: " + FileUtil.convertDateToString(LocalDate.now()));
    }

    private void initializeTableView() {
        TableColumnFactory factory = new TableColumnFactory();
        duration.setGraphic(factory.getRotated("Duration(days)"));
        completed.setGraphic(factory.getRotated("(%) Completed"));
        workingDays.setGraphic(factory.getRotated("Working days"));
        daysCompleted.setGraphic(factory.getRotated("Days completed"));
        daysRemaining.setGraphic(factory.getRotated("Days remaining"));
    }

    private void initializeDatesTableView(Project p) {
        datesTableView.setEditable(false);
        List<TableColumn> tb = datesTableView.getColumns();
        tb.addAll(ProjectService.getPeriod(p));

        datesTableView.getColumns().addListener(new ListChangeListener() {
            public boolean suspended;

            @Override
            public void onChanged(Change change) {
                change.next();
                if (change.wasReplaced() && !suspended) {
                    this.suspended = true;
                    datesTableView.getColumns().setAll(ProjectService.getPeriod(p));
                    this.suspended = false;
                }
            }
        });

    }

    private class DialogButtonAction implements EventHandler<ActionEvent> {

        private final Dialogable dialog;

        public DialogButtonAction(Dialogable dialog) {
            this.dialog = dialog;
        }

        @Override
        public void handle(ActionEvent event) {
            dialog.showAndWait();
        }
    }

}
