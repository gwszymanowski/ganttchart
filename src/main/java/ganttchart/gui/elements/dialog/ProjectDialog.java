package ganttchart.gui.elements.dialog;

import ganttchart.gui.elements.alert.ElementType;
import ganttchart.gui.elements.alert.OperationType;
import ganttchart.model.Project;
import ganttchart.repository.ProjectRepository;
import ganttchart.gui.elements.alert.AlertReason;
import ganttchart.gui.elements.alert.AlertFactory;
import javafx.event.EventDispatchChain;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.time.LocalDate;

/**
 * Created by gwszymanowski on 2017-07-10.
 */
public class ProjectDialog extends Dialog<ButtonType> implements Dialogable {

    private CreateProjectGridPane gridpane;
    private ProjectRepository repo;

    public ProjectDialog() {
        this.gridpane = new CreateProjectGridPane();
        this.repo = new ProjectRepository();
        setTitle("Create project");
        ButtonType confirmButtonType = new ButtonType("Save", ButtonBar.ButtonData.APPLY);

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
        LocalDate startDate = gridpane.startDatePicker.getValue();

        if(name.length() == 0)
            AlertFactory.getErrorAlert(AlertReason.ZERO_LENGTH).showAndWait();
        else if(repo.ifExists(name))
            AlertFactory.getErrorAlert(AlertReason.ALREADY_EXISTS).showAndWait();
        else {
            repo.save(new Project(name, startDate));
            gridpane.nameField.setText("");
            AlertFactory.getInformationAlert(ElementType.PROJECT, OperationType.SAVE).showAndWait();
        }
    }

    public void update(Project project) {
        String oldName = project.getName();
        String name = gridpane.nameField.getText();
        project.setName(name);
        repo.update(project, oldName);
    }

    public void fillFields(String name) {
        gridpane.nameField.setText(name);
    }

    private class CreateProjectGridPane extends GridPane {

        private TextField nameField;
        private DatePicker startDatePicker;

        public CreateProjectGridPane() {
            setHgap(10);
            setVgap(10);
            setPadding(new Insets(20, 150, 10, 10));

            nameField = new TextField();

            add(new Label("Name: "), 0, 1);
            add(nameField, 1, 1);

            startDatePicker = new DatePicker();
            add(new Label("Start: "), 0, 2);
            add(startDatePicker, 1, 2);

        }
    }



}
