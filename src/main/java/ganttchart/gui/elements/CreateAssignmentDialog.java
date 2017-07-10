package ganttchart.gui.elements;

import ganttchart.model.Assignment;
import ganttchart.model.Project;
import impl.org.controlsfx.autocompletion.AutoCompletionTextFieldBinding;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;
import javafx.util.Pair;
import org.controlsfx.control.textfield.AutoCompletionBinding;

import java.util.Collection;

/**
 * Created by gwszymanowski on 2017-07-07.
 */
public class CreateAssignmentDialog extends Dialog<Pair<String,String>> implements Dialogable {

    public CreateAssignmentDialog(Project project) {
        setTitle("Create assignment");
        setHeaderText(null);
        setGraphic(null);

        ButtonType loginButtonType = new ButtonType("Create", ButtonBar.ButtonData.OK_DONE);
        getDialogPane().getButtonTypes().addAll(loginButtonType);

        getDialogPane().setContent(new CreateAssignmentGridPane(project));
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
