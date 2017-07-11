package ganttchart.gui.elements;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;

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
        personListItem = new MenuItem("people");
        viewMenu.getItems().addAll(projectListItem, personListItem);
        return viewMenu;
    }

    private class ShowDialogEvent implements EventHandler<ActionEvent> {

        private Dialogable dialog;

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

}
