package ganttchart.gui.elements;

import ganttchart.controller.ProjectController;
import ganttchart.repository.UserRepository;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by gwszymanowski on 2017-07-02.
 */
public class ProjectCell extends TreeCell<String> {

    final private UserRepository userRepository = new UserRepository();
    private String title;

    public ProjectCell() {
        super();
    }

    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        if (empty) {
            setGraphic(null);
            setText(null);
        } else if(getTreeItem().getParent() != null && getTreeItem().getParent().getValue().equals("Projects")){
            this.title = item;
            HBox cellBox = new HBox(10);
            Label label = new Label(item);
            Button goButton = new Button("Go");
            goButton.setOnAction(new GoButtonManager());
            label.prefHeightProperty().bind(goButton.heightProperty());
            cellBox.getChildren().addAll(goButton, label );
            setGraphic(cellBox);

        } else {
            setGraphic(null);
            setText(item);
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
