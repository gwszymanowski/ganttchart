package ganttchart.gui.elements.dialog;

import ganttchart.gui.elements.alert.ElementType;
import ganttchart.gui.elements.alert.OperationType;
import ganttchart.model.Person;
import ganttchart.repository.PersonRepository;
import ganttchart.gui.elements.alert.AlertReason;
import ganttchart.gui.elements.alert.AlertFactory;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

/**
 * Created by gwszymanowski on 2017-07-10.
 */
public class PersonDialog extends Dialog<ButtonType> implements Dialogable {

    private CreatePersonGridPane gridpane;
    private PersonRepository repo;

    public PersonDialog() {
        this.gridpane = new CreatePersonGridPane();
        this.repo = new PersonRepository();
        setTitle("Create person");
        ButtonType loginButtonType = new ButtonType("Save");
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
            AlertFactory.getInformationAlert(ElementType.PROJECT, OperationType.SAVE).showAndWait();
        }
    }

    public void update(Person oldPerson) {
        String newFirstname = gridpane.firstnameField.getText();
        String newLastname = gridpane.lastnameField.getText();

        if(newFirstname.length() == 0 || newLastname.length() == 0)
            AlertFactory.getErrorAlert(AlertReason.ZERO_LENGTH).showAndWait();
        else {
            repo.update(oldPerson, new Person(newFirstname, newLastname));
            fillFields("", "");
        }

    }

    public void fillFields(String firstname, String lastname) {
        gridpane.firstnameField.setText(firstname);
        gridpane.lastnameField.setText(lastname);
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
