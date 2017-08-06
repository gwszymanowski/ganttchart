package ganttchart.controller;

import ganttchart.gui.elements.cell.AssignmentCell;
import ganttchart.gui.elements.cell.DatesCell;
import ganttchart.gui.elements.dialog.Dialogable;
import ganttchart.gui.elements.dialog.MarshalizeAssignmentDialog;
import ganttchart.gui.elements.dialog.MembersDialog;
import ganttchart.gui.elements.dialog.AssignmentDialog;
import ganttchart.gui.elements.dialog.chart.ProgressChart;
import ganttchart.model.Assignment;
import ganttchart.model.Project;
import ganttchart.repository.ProjectRepository;
import ganttchart.serialization.JSONSerializator;
import ganttchart.serialization.XMLSerializator;
import ganttchart.service.AssignmentService;
import ganttchart.service.ProjectService;
import ganttchart.util.FileUtil;
import ganttchart.util.TableColumnFactory;
import ganttchart.gui.elements.alert.AlertFactory;
import ganttchart.gui.elements.alert.ElementType;
import ganttchart.gui.elements.alert.OperationType;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDate;
import java.util.*;

/**
 * Created by gwszymanowski on 2017-06-30.
 */
public class AssignmentController implements Initializable {

    @FXML
    private TableColumn title;

    @FXML
    private TableColumn taskOwner;

    @FXML
    private TableColumn startDate;

    @FXML
    private TableColumn finishDate;

    @FXML
    private TableColumn duration;

    @FXML
    private TableColumn completed;

    @FXML
    private TableColumn workingDays;

    @FXML
    private Label titleLabel;

    @FXML
    private Label startDateLabel;

    @FXML
    private Label todayIsLabel;

    @FXML
    private TableView tableView;

    @FXML
    private TableView datesTableView;

    @FXML
    private Button newAssignment;

    @FXML
    private Button memberList;

    @FXML
    private Button exportButton;

    private String titleValue;
    private ProjectRepository projectRepository;
    private Project project;

    public AssignmentController(String title) {
        this.titleValue = title;
        this.projectRepository = new ProjectRepository();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        project = projectRepository.findByName(titleValue);
        initializeLabel();
        initializeTableView();
        initializeDatesTableView();
        fillTable();
        fillDatesTable();
    }

    private void initializeLabel() {
        titleLabel.setText(titleValue);
        startDateLabel.setText(FileUtil.concatenateString("Startdate is: " , FileUtil.convertDateToString(project.getStartDate())));
        todayIsLabel.setText(FileUtil.concatenateString("Today is: " , FileUtil.convertDateToString(LocalDate.now())));
        memberList.setOnAction(new DialogButtonAction(new MembersDialog(project)));
        newAssignment.setOnAction(new DialogButtonAction(new AssignmentDialog(project)));
        exportButton.setOnAction(new MarshalAction());
    }

    private void initializeTableView() {
        TableColumnFactory factory = new TableColumnFactory();
        duration.setGraphic(factory.getRotated("Duration(days)"));
        workingDays.setGraphic(factory.getRotated("Days left"));
        completed.setGraphic(factory.getRotated("(%) Completed"));
    }

    private void initializeDatesTableView() {
       ObservableList<TableColumn> tableColumns = datesTableView.getColumns();
       List<TableColumn> dates = ProjectService.getPeriod(project);

       dates.stream().forEach(x -> tableColumns.add(x));
       datesTableView.getColumns().addListener(new ListChangeBlocker());
    }

    private void fillTable() {
        ObservableList<TableColumn> cols = tableView.getColumns();

        for(TableColumn tb : cols) {
            tb.setCellValueFactory(new PropertyValueFactory<>(tb.getId().toString()));
            tb.setCellFactory(column -> new AssignmentCell(project));
        }
        refresh();
    }

    private void fillDatesTable() {
        ObservableList<TableColumn> tableColumns = datesTableView.getColumns();

        int tbsize = tableColumns.size();

        for(Assignment a : project.getAssignments()) {
            List<String> dates = AssignmentService.getAllDaysToString(a);

            for(int i = 0; i < tbsize; i++) {
                TableColumn help = tableColumns.get(i);
                if(dates.contains(help.getGraphic().getAccessibleHelp())){
                    help.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(help.getGraphic().getAccessibleHelp()));
                    help.setCellFactory(column -> new DatesCell(project));
                }
            }
        }
        refresh();
    }

    public void refresh() {
        tableView.getItems().setAll(project.getAssignments());
        datesTableView.getItems().setAll(project.getAssignments());
    }

    private class DialogButtonAction implements EventHandler<ActionEvent> {

        private final Dialogable dialog;

        public DialogButtonAction(Dialogable dialog) {
            this.dialog = dialog;
        }

        @Override
        public void handle(ActionEvent event) {

            Optional<ButtonType> result = dialog.showAndWait();
            if(result.isPresent() && Optional.of(result.get()).get().getButtonData() == ButtonBar.ButtonData.APPLY) {
                dialog.save();
                AlertFactory.getInformationAlert(ElementType.OTHER, OperationType.SAVE).showAndWait();
            }
        }
    }

    private class ListChangeBlocker implements ListChangeListener {

        public boolean suspended;

        @Override
        public void onChanged(Change change) {
            change.next();
            if (change.wasReplaced() && !suspended) {
                this.suspended = true;
                datesTableView.getColumns().setAll(ProjectService.getPeriod(project));
                fillDatesTable();
                this.suspended = false;
            }
        }
    }

    private class MarshalAction implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {

            MarshalizeAssignmentDialog dialog = new MarshalizeAssignmentDialog(project);

            Optional<ButtonType> result = dialog.showAndWait();

            if(result.isPresent()) {
                ButtonBar.ButtonData data = Optional.of(result.get()).get().getButtonData();

                if(data == ButtonBar.ButtonData.BIG_GAP)
                    dialog.marshalize(new JSONSerializator(Project.class));
                else if(data == ButtonBar.ButtonData.SMALL_GAP)
                    dialog.marshalize(new XMLSerializator(Project.class));

            }
        }
    }

}
