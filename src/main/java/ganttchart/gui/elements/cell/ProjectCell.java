package ganttchart.gui.elements.cell;

import ganttchart.controller.ProjectController;
import ganttchart.gui.elements.dialog.ProjectDialog;
import ganttchart.model.Project;
import ganttchart.repository.ProjectRepository;
import ganttchart.util.ElementType;
import ganttchart.util.AlertFactory;
import ganttchart.util.OperationType;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

/**
 * Created by gwszymanowski on 2017-07-02.
 */
public class ProjectCell extends TableCell<Project, String> {

    private String title;
    private ProjectRepository repo = new ProjectRepository();

    public ProjectCell() {
        super();
    }

    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);

            if(getTableRow().getItem() != null) {
                this.title = item;
                initializeComponents();
            }

    }

    private void initializeComponents() {
        HBox cellBox = new HBox(10);
        Label label = new Label(title);
        Button goButton = new Button("Go");
        goButton.setOnAction(new GoButtonManager());
        Button editButton = new Button("Edit");
        editButton.setOnAction(new EditProjectEvent());
        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(new DeleteProjectEvent());
        label.prefHeightProperty().bind(goButton.heightProperty());
        cellBox.getChildren().addAll(goButton, editButton, deleteButton, label );
        setGraphic(cellBox);
    }

    private class EditProjectEvent implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            ProjectDialog cp = new ProjectDialog();
            cp.fillFields(title);
            cp.showAndWait();
        }
    }

    private class DeleteProjectEvent implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            Alert alert = AlertFactory.getWarningAlert(ElementType.PROJECT);
            ButtonType deleteButtonType = new ButtonType("Delete", ButtonBar.ButtonData.APPLY);
            alert.getButtonTypes().setAll(deleteButtonType);

            Optional<ButtonType> result = alert.showAndWait();
            if(result.isPresent() && Optional.of(result.get()).get().getButtonData() == ButtonBar.ButtonData.APPLY) {
                repo.delete(title);
                AlertFactory.getInformationAlert(ElementType.PERSON, OperationType.DELETE).showAndWait();
            }
        }
    }

    private class GoButtonManager implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            Stage stage = null;
            Parent root = null;
            ProjectController projectController = new ProjectController(title);

            try {
                stage = (Stage) getScene().getWindow();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/project.fxml"));
                loader.setController(projectController);
                root = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }
    }

}
