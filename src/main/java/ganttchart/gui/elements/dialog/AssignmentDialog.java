package ganttchart.gui.elements.dialog;

import ganttchart.model.Assignment;
import ganttchart.model.Person;
import ganttchart.model.Project;
import ganttchart.repository.ProjectRepository;
import ganttchart.service.ProjectService;
import ganttchart.gui.elements.alert.AlertFactory;
import ganttchart.gui.elements.alert.AlertReason;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Created by gwszymanowski on 2017-07-07.
 */
public class AssignmentDialog extends Dialog<ButtonType> implements Dialogable {

    private CreateAssignmentGridPane gridpane;
    private Project project;
    private ProjectRepository repo = new ProjectRepository();

    public AssignmentDialog(Project project) {
        this.project = project;
        setTitle("Assignment");

        ButtonType loginButtonType = new ButtonType("Change", ButtonBar.ButtonData.APPLY);
        getDialogPane().getButtonTypes().addAll(loginButtonType);

        gridpane = new CreateAssignmentGridPane(project);
        getDialogPane().setContent(gridpane);
    }


    @Override
    public void save() {
        String title = gridpane.titleField.getText();
        LocalDate startDate = gridpane.startDatePicker.getValue();
        LocalDate endDate = gridpane.endDatePicker.getValue();
        String taskOwnerText = gridpane.taskOwnerBox.getValue().toString();

        if(repo.ifExists(title))
            AlertFactory.getErrorAlert(AlertReason.ALREADY_EXISTS).showAndWait();
        else {

            Assignment ass = new Assignment();
            ass.setTitle(title);
            ass.setStartDate(startDate);
            ass.setFinishDate(endDate);

            if(taskOwnerText.length() != 0) {
                String[] taskOwner = taskOwnerText.split(" ");
                ass.setTaskOwner(new Person(taskOwner[0], taskOwner[1]));
            }

            project.addTask(ass);

            repo.save(project);
        }

    }

    public void update(Project project, String previousTitle) {
        makeActions(previousTitle);
        repo.update(project);
    }

    public void makeActions(String previousTitle) {
        String title = gridpane.titleField.getText();
        LocalDate startDate = gridpane.startDatePicker.getValue();
        LocalDate endDate = gridpane.endDatePicker.getValue();
        String taskOwnerText = gridpane.taskOwnerBox.getValue().toString();

        if(title.length() == 0)
            AlertFactory.getErrorAlert(AlertReason.ZERO_LENGTH).showAndWait();
        else {
            Optional<Assignment> optional = project.getTasks().stream().filter(x -> x.getTitle().equals(previousTitle)).findFirst();
            Assignment ass = optional.get();
            ass.setTitle(title);
            ass.setStartDate(startDate);
            ass.setFinishDate(endDate);

            if(taskOwnerText.length() != 0) {
                String[] taskOwner = taskOwnerText.split(" ");
                ass.setTaskOwner(new Person(taskOwner[0], taskOwner[1]));
            }

            fillFields(title, startDate, endDate);

        }
    }

    public void fillFields(String title, LocalDate startDate, LocalDate endDate) {
        gridpane.setValues(title, startDate, endDate);
    }

    private class CreateAssignmentGridPane extends GridPane {

        private TextField titleField;
        private DatePicker startDatePicker, endDatePicker;
        private ComboBox taskOwnerBox;

        public CreateAssignmentGridPane(Project project) {
            setHgap(10);
            setVgap(10);
            setPadding(new Insets(20, 150, 10, 10));

            titleField = new TextField();

            add(new Label("Title: "), 0, 1);
            add(titleField, 1, 1);

            ObservableList<String> options =
            FXCollections.observableArrayList(
                    ProjectService.getMembersToArray(project.getMembers())
            );
            taskOwnerBox = new ComboBox(options);
            add(new Label("Task owner: "), 0, 2);
            add(taskOwnerBox, 1, 2);

            startDatePicker = new DatePicker();
            add(new Label("Start: "), 0, 3);
            add(startDatePicker, 1, 3);

            endDatePicker = new DatePicker();
            add(new Label("End: "), 0, 4);
            add(endDatePicker, 1, 4);

        }

        public void setValues(String title, LocalDate startDate, LocalDate endDate) {
            titleField.setText(title);
            startDatePicker.setValue(startDate);
            endDatePicker.setValue(endDate);
        }
    }

}
