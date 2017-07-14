package ganttchart.gui.elements.dialog;

import ganttchart.model.Project;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

/**
 * Created by gwszymanowski on 2017-07-07.
 */
public class AssignmentDialog extends Dialog<ButtonType> implements Dialogable {

    private CreateAssignmentGridPane gridpane;

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
    }


}
