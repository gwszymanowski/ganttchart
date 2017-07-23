package ganttchart.gui.elements.alert;

import ganttchart.gui.elements.dialog.Dialogable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import javax.swing.*;

import static javafx.scene.layout.HBox.setHgrow;

/**
 * Created by gwszymanowski on 2017-07-22.
 */
public class AssignmentChoiceAlert extends Alert implements Dialogable {

    private AssignmentChoiceGridpane gridpane;

    public AssignmentChoiceAlert(AlertType alertType) {
        super(alertType);

        setTitle("Edit Assignment");
        setHeaderText("Choose your option or quickly edit progress.");

        gridpane = new AssignmentChoiceGridpane();

        getDialogPane().setContent(gridpane);

        ButtonType editButtonType = new ButtonType("Edit", ButtonBar.ButtonData.NO);
        ButtonType deleteButtonType = new ButtonType("Delete", ButtonBar.ButtonData.HELP_2);
        ButtonType saveProgressButtonType = new ButtonType("Save progress", ButtonBar.ButtonData.LEFT);
        ButtonType cancelButtonType = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        getButtonTypes().setAll(editButtonType, deleteButtonType, saveProgressButtonType, cancelButtonType);
    }

    @Override
    public void save() {

    }

    private class AssignmentChoiceGridpane extends GridPane {

        private TextField percentFinished;

        public AssignmentChoiceGridpane() {
            setHgap(10);
            setVgap(10);
            setPadding(new Insets(20, 150, 10, 10));

            percentFinished = new TextField();
            percentFinished.setPromptText("% finished");

            add(percentFinished, 15, 0);
        }
    }

}
