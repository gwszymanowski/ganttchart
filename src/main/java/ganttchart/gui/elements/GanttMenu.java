package ganttchart.gui.elements;

import ganttchart.controller.ProjectController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

/**
 * Created by gwszymanowski on 2017-07-09.
 */
public class GanttMenu extends MenuBar {

    private MenuItem projectItem, personItem, projectListItem, personListItem;

    public GanttMenu() {
        super();
        getMenus().addAll(getCreateMenu(), getViewMenu());
    }

    private Menu getCreateMenu() {
        Menu createMenu = new Menu("Create");
        projectItem = new MenuItem("project");
        projectItem.setOnAction(new ShowDialogEvent(new CreateProjectDialog()));
        personItem = new MenuItem("person");
        personItem.setOnAction(new ShowDialogEvent(new CreatePersonDialog()));
        createMenu.getItems().addAll(projectItem, personItem);
        return createMenu;
    }

    private Menu getViewMenu() {
        Menu viewMenu = new Menu("View");
        projectListItem = new MenuItem("projects");
        projectListItem.setOnAction(new SwitchViewEvent("/primary.fxml"));
        personListItem = new MenuItem("people");
        personListItem.setOnAction(new SwitchViewEvent("/person.fxml"));
        viewMenu.getItems().addAll(projectListItem, personListItem);
        return viewMenu;
    }

    private class ShowDialogEvent implements EventHandler<ActionEvent> {

        private final Dialogable dialog;

        public ShowDialogEvent(Dialogable dialog) {
            this.dialog = dialog;
        }

        @Override
        public void handle(ActionEvent event) {
            Optional<ButtonType> result = dialog.showAndWait();

            if(result.isPresent() && Optional.of(result.get()).get().getButtonData() == ButtonBar.ButtonData.OTHER)
                dialog.save();
        }
    }

    private class SwitchViewEvent implements EventHandler<ActionEvent> {

        private final String path;

        public SwitchViewEvent(String path) {
            this.path = path;
        }

        @Override
        public void handle(ActionEvent event) {
            Stage stage = null;
            Parent root = null;
            try {
                stage = (Stage) getScene().getWindow();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(path));
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

