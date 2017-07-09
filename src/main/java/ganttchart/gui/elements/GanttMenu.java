package ganttchart.gui.elements;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

/**
 * Created by gwszymanowski on 2017-07-09.
 */
public class GanttMenu extends MenuBar {

    MenuItem projectItem, personItem, projectListItem, personListItem;

    public GanttMenu() {
        super();
        getMenus().addAll(getCreateMenu(), getViewMenu());
    }

    private Menu getCreateMenu() {
        Menu createMenu = new Menu("Create");
        projectItem = new MenuItem("project");
        personItem = new MenuItem("person");
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
}
