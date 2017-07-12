package ganttchart.gui.elements;

import ganttchart.model.Project;
import ganttchart.repository.ProjectRepository;
import ganttchart.util.AlertElementType;
import ganttchart.util.AlertReason;
import ganttchart.util.AlertFactory;
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
public class CreateProjectDialog extends Dialog<ButtonType> implements Dialogable {

    private CreateProjectGridPane gridpane = new CreateProjectGridPane();
    private ProjectRepository repo = new ProjectRepository();

    public CreateProjectDialog() {
        setTitle("Create project");
        setHeaderText(null);
        setGraphic(null);
        ButtonType confirmButtonType = new ButtonType("Create");

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
            AlertFactory.getSaveConfirmAlert(AlertElementType.PROJECT).showAndWait();
        }

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
