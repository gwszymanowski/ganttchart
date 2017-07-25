package ganttchart.gui.elements.alert;

import ganttchart.gui.elements.dialog.Dialogable;
import ganttchart.model.Assignment;
import ganttchart.model.Project;
import ganttchart.repository.ProjectRepository;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import javax.swing.*;

import java.util.Optional;

import static javafx.scene.layout.HBox.setHgrow;

/**
 * Created by gwszymanowski on 2017-07-22.
 */
public class AssignmentChoiceAlert extends Alert {

    private AssignmentChoiceGridpane gridpane;
    private ProjectRepository projectRepository;



    public AssignmentChoiceAlert(AlertType alertType) {
        super(alertType);
        this.gridpane = new AssignmentChoiceGridpane();
        this.projectRepository = new ProjectRepository();

        setTitle("Edit Assignment");
        setHeaderText("Choose your option or quickly edit progress.");
        getDialogPane().setContent(gridpane);

        ButtonType editButtonType = new ButtonType("Edit", ButtonBar.ButtonData.NO);
        ButtonType deleteButtonType = new ButtonType("Delete", ButtonBar.ButtonData.HELP_2);
        ButtonType saveProgressButtonType = new ButtonType("Save progress", ButtonBar.ButtonData.LEFT);
        ButtonType cancelButtonType = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        getButtonTypes().setAll(editButtonType, deleteButtonType, saveProgressButtonType, cancelButtonType);
    }

    public void saveProgress(Project project, String assignmentTitle) {
        Optional<Assignment> optional = project.getTasks().stream().filter(x -> x.getTitle().equals(assignmentTitle)).findFirst();

        if(optional.isPresent()) {
            Assignment assignment = optional.get();
            String percentageString = gridpane.percentFinished.getText();
            int value = percentageString.length() == 0 ? 0 : Integer.parseInt(percentageString);
            assignment.setCompleted(value);
            projectRepository.update(project);
        }
    }

    public void delete(Project project, String assignmentTitle) {
        System.out.println(assignmentTitle);
        project.getTasks().removeIf(x -> x.getTitle().equals(assignmentTitle));
        projectRepository.update(project);
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
