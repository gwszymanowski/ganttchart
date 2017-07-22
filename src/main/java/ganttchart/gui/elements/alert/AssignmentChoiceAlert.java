package ganttchart.gui.elements.alert;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import javax.swing.*;

/**
 * Created by gwszymanowski on 2017-07-22.
 */
public class AssignmentChoiceAlert extends Alert {

    public AssignmentChoiceAlert(AlertType alertType) {
        super(alertType);

        setTitle("Edit Assignment");
        setHeaderText("Choose your option or quickly edit progress.");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField percentFinished = new TextField();
        percentFinished.setPromptText("% finished");

        grid.add(percentFinished, 0, 0);
        Button change = new Button("Save progress");
        grid.add(change, 1,0);

        getDialogPane().setContent(grid);

        ButtonType buttonTypeOne = new ButtonType("Edit all");
        ButtonType buttonTypeThree = new ButtonType("Delete");
        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        getButtonTypes().setAll(buttonTypeOne, buttonTypeThree, buttonTypeCancel);
    }
}
