package ganttchart.gui.elements;

import ganttchart.model.Person;
import ganttchart.repository.PersonRepository;
import ganttchart.util.AlertElementType;
import ganttchart.util.AlertReason;
import ganttchart.util.AlertFactory;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

/**
 * Created by gwszymanowski on 2017-07-10.
 */
public class CreatePersonDialog extends Dialog<ButtonType> implements Dialogable {

    private CreatePersonGridPane gridpane = new CreatePersonGridPane();
    private PersonRepository repo = new PersonRepository();

    public CreatePersonDialog() {
        setTitle("Create person");
        setHeaderText(null);
        setGraphic(null);

        ButtonType loginButtonType = new ButtonType("Create");
        getDialogPane().getButtonTypes().addAll(loginButtonType);

        getDialogPane().setContent(gridpane);
    }

    @Override
    public void save() {
        String firstname = gridpane.firstnameField.getText();
        String lastname = gridpane.lastnameField.getText();
        if(firstname.length() == 0 || lastname.length() == 0)
            AlertFactory.getErrorAlert(AlertReason.ZERO_LENGTH).showAndWait();
        else if(repo.ifExists(firstname, lastname))
            AlertFactory.getErrorAlert(AlertReason.ALREADY_EXISTS).showAndWait();
        else {
            repo.save(new Person(firstname, lastname));
            AlertFactory.getSaveConfirmAlert(AlertElementType.PROJECT).showAndWait();
        }

    }

    private class CreatePersonGridPane extends GridPane {

        private TextField firstnameField, lastnameField;

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
