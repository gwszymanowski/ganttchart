package ganttchart.gui.elements.dialog;

import ganttchart.model.Project;
import ganttchart.repository.ProjectRepository;
import ganttchart.util.alert.ElementType;
import ganttchart.util.alert.AlertReason;
import ganttchart.util.alert.AlertFactory;
import ganttchart.util.alert.OperationType;
import javafx.event.EventDispatchChain;
import javafx.geometry.Insets;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 * Created by gwszymanowski on 2017-07-10.
 */
public class ProjectDialog extends Dialog<ButtonType> implements Dialogable {

    private CreateProjectGridPane gridpane = new CreateProjectGridPane();
    private ProjectRepository repo = new ProjectRepository();

    public ProjectDialog() {
        setTitle("Create project");
        ButtonType confirmButtonType = new ButtonType("Save");

        getDialogPane().getButtonTypes().addAll(confirmButtonType);
        getDialogPane().setContent(gridpane);
    }

    @Override
    public EventDispatchChain buildEventDispatchChain(EventDispatchChain tail) {
        return super.buildEventDispatchChain(tail);
    }

    @Override
    public void save() {
        String name = gridpane.nameField.getText();

        if(name.length() == 0)
            AlertFactory.getErrorAlert(AlertReason.ZERO_LENGTH).showAndWait();
        else if(repo.ifExists(name))
            AlertFactory.getErrorAlert(AlertReason.ALREADY_EXISTS).showAndWait();
        else {
            repo.save(new Project(name));
            gridpane.nameField.setText("");
            //AlertFactory.getInformationAlert(ElementType.PROJECT, OperationType.SAVE).showAndWait();
        }
    }

    public void fillFields(String name) {
        gridpane.nameField.setText(name);
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
