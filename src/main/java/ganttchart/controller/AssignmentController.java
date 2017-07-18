package ganttchart.controller;

import ganttchart.gui.elements.cell.DatesCell;
import ganttchart.gui.elements.cell.PersonCell;
import ganttchart.gui.elements.cell.ProjectCell;
import ganttchart.gui.elements.dialog.Dialogable;
import ganttchart.gui.elements.dialog.MembersDialog;
import ganttchart.gui.elements.dialog.AssignmentDialog;
import ganttchart.model.Assignment;
import ganttchart.model.Project;
import ganttchart.repository.ProjectRepository;
import ganttchart.service.AssignmentService;
import ganttchart.service.ProjectService;
import ganttchart.util.FileUtil;
import ganttchart.util.TableColumnFactory;
import ganttchart.util.alert.AlertFactory;
import ganttchart.util.alert.ElementType;
import ganttchart.util.alert.OperationType;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.util.Callback;

import java.net.URL;
import java.time.LocalDate;
import java.util.*;

/**
 * Created by gwszymanowski on 2017-06-30.
 */
public class AssignmentController implements Initializable {

    @FXML
    private TableColumn titleColumn;

    @FXML
    private TableColumn typeColumn;

    @FXML
    private TableColumn taskOwnerColumn;

    @FXML
    private TableColumn startColumn;

    @FXML
    private TableColumn endColumn;

    @FXML
    private TableColumn durationColumn;

    @FXML
    private TableColumn completedColumn;

    @FXML
    private TableColumn workingDaysColumn;

    @FXML
    private Label titleLabel;

    @FXML
    private Label startDateLabel;

    @FXML
    private Label todayIsLabel;

    @FXML
    private TableView tableView;

    @FXML
    private Button newAssignment;

    @FXML
    private Button memberList;

    private String title;
    private ProjectRepository projectRepository = new ProjectRepository();
    private Project p;

    public AssignmentController(String title) {
        this.title = title;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.p = projectRepository.findByName(title);

        initializeLabels();
        initializeTableView();
        initializeDatesTableView();
        fillTable();
       // fillDatesTable();
    }

    private void initializeLabels() {
        titleLabel.setText(title);
        memberList.setOnAction(new DialogButtonAction(new MembersDialog(p)));
        newAssignment.setOnAction(new DialogButtonAction(new AssignmentDialog(p)));
        startDateLabel.setText(FileUtil.concatenateString("Startdate is: " , FileUtil.convertDateToString(p.getStartDate())));
        todayIsLabel.setText(FileUtil.concatenateString("Today is: " , FileUtil.convertDateToString(LocalDate.now())));
    }

    private void initializeTableView() {
        TableColumnFactory factory = new TableColumnFactory();
        durationColumn.setGraphic(factory.getRotated("Duration(days)"));
        completedColumn.setGraphic(factory.getRotated("(%) Completed"));
        workingDaysColumn.setGraphic(factory.getRotated("Working days"));

    }

    private void initializeDatesTableView() {
       ObservableList<TableColumn> tableColumns = tableView.getColumns();
       List<TableColumn> dates = ProjectService.getPeriod(p);

       dates.stream().forEach(x -> tableColumns.add(x));

//       for(TableColumn tb : dates) {
//           tableColumns.add(tb);
//       }

//        tableView.getColumns().addAll();
//        System.out.println(datesList.size());
//        tableView.getColumns().addAll(datesList);
     //   System.out.println(tableView.getColumns().size());
//        tableView.getColumns().addListener(new ListChangeListener() {
//            public boolean suspended;
//
//            @Override
//            public void onChanged(Change change) {
//                change.next();
//                if (change.wasReplaced() && !suspended) {
//                    this.suspended = true;
//                    tableView.getColumns().setAll(ProjectService.getPeriod(p));
//                    this.suspended = false;
//                }
//            }
//        });

    }

    private void fillTable() {
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        taskOwnerColumn.setCellValueFactory(new PropertyValueFactory<>("taskOwner"));
        startColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        endColumn.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        durationColumn.setCellValueFactory(new PropertyValueFactory<>("duration"));
        completedColumn.setCellValueFactory(new PropertyValueFactory<>("completed"));
        workingDaysColumn.setCellValueFactory(new PropertyValueFactory<>("workingDays"));

       // List<String> dates = ProjectService.getAllDaysToString(p);

        ObservableList<TableColumn> tableColumns = tableView.getColumns();

        int tbsize = tableColumns.size();

        for(Assignment a : p.getTasks()) {
            List<String> dates = AssignmentService.getAllDaysToString(a);

            System.out.println(dates);
            for(int i = 7; i < tbsize; i++) {

                TableColumn help = tableColumns.get(i);
                if(dates.contains(help.getGraphic().getAccessibleHelp())){
                    help.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(help.getGraphic().getAccessibleHelp()));
                    //help.setCellFactory(column -> new DatesCell(AssignmentService.getAllDaysToString(a)));

                    help.setCellFactory(column -> new DatesCell(p));
                }

            }
        }




        refresh();
    }
//
//    private void fillDatesTable() {
//        ObservableList<TableColumn> columns = datesTableView.getColumns();
//        List<String> dates = ProjectService.getAllDaysToString(p);
//        Iterator<String> it = dates.iterator();
//        for(TableColumn tb : columns) {
//            tb.setCellValueFactory(new PropertyValueFactory<>(it.next()));
//        }
//
//        List<Assignment> tasks = p.getTasks();
//
//        for(Assignment a : tasks) {
//            datesTableView.getItems().add(AssignmentService.getAllDays(a));
//        }
//
//
////        List<String> dates = ProjectService.getAllDaysToString(p);
////
//
//
//    }

    public void refresh() {
        tableView.getItems().setAll(p.getTasks());
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

}
