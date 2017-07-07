package ganttchart.gui.elements;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

/**
 * Created by gwszymanowski on 2017-07-07.
 */
public class NewAssignmentDialog extends Dialog<Pair<String,String>> implements Dialogable {

    public NewAssignmentDialog() {
        setTitle("New assignment");
        setHeaderText(null);
        setGraphic(null);

        ButtonType loginButtonType = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
        getDialogPane().getButtonTypes().addAll(loginButtonType);

        getDialogPane().setContent(new NewAssignmentGridPane());
    }

    private class NewAssignmentGridPane extends GridPane {
        public NewAssignmentGridPane() {
            setHgap(10);
            setVgap(10);
            setPadding(new Insets(20, 150, 10, 10));

            TextField titleField = new TextField();

            add(new Label("Title: "), 0, 1);
            add(titleField, 1, 1);

            DatePicker beginDatePicker = new DatePicker();

            add(new Label("Begin date: "), 0, 2);
            add(beginDatePicker, 1, 2);

            DatePicker endDatePicker = new DatePicker();

            add(new Label("End date: "), 0, 3);
            add(endDatePicker, 1, 3);

            TextField taskOwnerField = new TextField();
            //new AutoCompletionTextFieldBinding(executorField, Collections.EMPTY_LIST);
            add(new Label("Task owner: "), 0, 4);
            add(taskOwnerField, 1, 4);
        }
    }


}
