package ganttchart.gui.elements;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

/**
 * Created by gwszymanowski on 2017-07-10.
 */
public class CreatePersonDialog extends Dialog<Pair<String,String>> implements Dialogable {

    public CreatePersonDialog() {
        setTitle("Create person");
        setHeaderText(null);
        setGraphic(null);

        ButtonType loginButtonType = new ButtonType("Create", ButtonBar.ButtonData.OK_DONE);
        getDialogPane().getButtonTypes().addAll(loginButtonType);

        getDialogPane().setContent(new CreatePersonGridPane());
    }

    private class CreatePersonGridPane extends GridPane {

        TextField firstnameField, lastnameField;

        public CreatePersonGridPane() {
            setHgap(10);
            setVgap(10);
            setPadding(new Insets(20, 150, 10, 10));

            firstnameField = new TextField();

            add(new Label("Firstname: "), 0, 1);
            add(firstnameField, 1, 1);


            lastnameField = new TextField();
            add(new Label("Lastname: "), 0, 2);
            add(lastnameField, 1, 2);
        }
    }
}
