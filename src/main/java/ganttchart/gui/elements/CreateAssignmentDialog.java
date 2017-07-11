package ganttchart.gui.elements;

import ganttchart.model.Project;
import javafx.geometry.Insets;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 * Created by gwszymanowski on 2017-07-07.
 */
public class CreateAssignmentDialog extends Dialog<ButtonType> implements Dialogable {

    public CreateAssignmentDialog(Project project) {
        setTitle("Create assignment");
        setHeaderText(null);
        setGraphic(null);

        ButtonType loginButtonType = new ButtonType("Create");
        getDialogPane().getButtonTypes().addAll(loginButtonType);

        getDialogPane().setContent(new CreateAssignmentGridPane(project));
    }

    @Override
    public void save() {

    }

    private class CreateAssignmentGridPane extends GridPane {
        public CreateAssignmentGridPane(Project project) {
            setHgap(10);
            setVgap(10);
            setPadding(new Insets(20, 150, 10, 10));

            TextField titleField = new TextField();

            add(new Label("Firstname: "), 0, 1);
            add(titleField, 1, 1);

            TextField taskOwnerField = new TextField();
            add(new Label("Task owner: "), 0, 2);
            add(taskOwnerField, 1, 2);
        }
    }


}
