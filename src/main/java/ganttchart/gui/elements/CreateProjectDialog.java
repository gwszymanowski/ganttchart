package ganttchart.gui.elements;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

/**
 * Created by gwszymanowski on 2017-07-10.
 */
public class CreateProjectDialog extends Dialog<Pair<String,String>> implements Dialogable {

    public CreateProjectDialog() {
        setTitle("Create project");
        setHeaderText(null);
        setGraphic(null);

        ButtonType loginButtonType = new ButtonType("Create", ButtonBar.ButtonData.OK_DONE);
        getDialogPane().getButtonTypes().addAll(loginButtonType);

       getDialogPane().setContent(new CreateProjectGridPane());
    }

    private class CreateProjectGridPane extends GridPane {

        private TextField nameField;

        public CreateProjectGridPane() {
            setHgap(10);
            setVgap(10);
            setPadding(new Insets(20, 150, 10, 10));

            nameField = new TextField();

            add(new Label("Name: "), 0, 1);
            add(nameField, 1, 1);

        }
    }

}
