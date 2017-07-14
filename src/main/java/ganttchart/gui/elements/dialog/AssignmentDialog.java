package ganttchart.gui.elements.dialog;

import ganttchart.model.Assignment;
import ganttchart.model.Person;
import ganttchart.model.Project;
import ganttchart.repository.AssignmentRepository;
import ganttchart.util.AlertFactory;
import ganttchart.util.AlertReason;
import ganttchart.util.ElementType;
import ganttchart.util.OperationType;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.time.LocalDate;

/**
 * Created by gwszymanowski on 2017-07-07.
 */
public class AssignmentDialog extends Dialog<ButtonType> implements Dialogable {

    private CreateAssignmentGridPane gridpane;
    private AssignmentRepository repo = new AssignmentRepository();

    public AssignmentDialog(Project project) {
        setTitle("Create assignment");
        setHeaderText(null);
        setGraphic(null);

        ButtonType loginButtonType = new ButtonType("Create");
        getDialogPane().getButtonTypes().addAll(loginButtonType);

        gridpane = new CreateAssignmentGridPane(project);
        getDialogPane().setContent(gridpane);
    }

    @Override
    public void save() {
        String title = gridpane.titleField.getText();
        String[] taskOwner = gridpane.taskOwnerField.getText().split(" ");
        LocalDate startDate = gridpane.startDatePicker.getValue();
        LocalDate endDate = gridpane.endDatePicker.getValue();

        if(title.length() == 0)
            AlertFactory.getErrorAlert(AlertReason.ZERO_LENGTH).showAndWait();
        else if(repo.ifExists(title, startDate, endDate))
            AlertFactory.getErrorAlert(AlertReason.ALREADY_EXISTS).showAndWait();
        else {
            repo.save(new Assignment(title, startDate, endDate, new Person(taskOwner[0], taskOwner[1])));
            fillFields("", "", null, null);
            AlertFactory.getInformationAlert(ElementType.PROJECT, OperationType.SAVE).showAndWait();
        }
    }

    public void fillFields(String title, String taskOwner, LocalDate startDate, LocalDate endDate) {
        gridpane.setValues(title, taskOwner, startDate, endDate);
    }

    private class CreateAssignmentGridPane extends GridPane {

        private TextField titleField, taskOwnerField;
        private DatePicker startDatePicker, endDatePicker;

        public CreateAssignmentGridPane(Project project) {
            setHgap(10);
            setVgap(10);
            setPadding(new Insets(20, 150, 10, 10));

            titleField = new TextField();

            add(new Label("Firstname: "), 0, 1);
            add(titleField, 1, 1);

            taskOwnerField = new TextField();
            add(new Label("Task owner: "), 0, 2);
            add(taskOwnerField, 1, 2);

            startDatePicker = new DatePicker();
            add(new Label("Start: "), 0, 3);
            add(startDatePicker, 1, 3);

            endDatePicker = new DatePicker();
            add(new Label("End: "), 0, 4);
            add(endDatePicker, 1, 4);

        }

        public void setValues(String title, String taskOwner, LocalDate startDate, LocalDate endDate) {
            titleField.setText(title);
            taskOwnerField.setText(taskOwner);
            startDatePicker.setValue(startDate);
            endDatePicker.setValue(endDate);
        }
    }

}
