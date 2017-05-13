package gui;

import javafx.application.Platform;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;

/**
 * Created by gwszymanowski on 2017-05-02.
 */
public class GanttMenu extends MenuBar {

    public GanttMenu() {
        initializeProjectMenu();
    }

    private void initializeProjectMenu() {
        Menu projectMenu = new Menu("Project");
        MenuItem newMenuItem = new MenuItem("New");
        MenuItem saveMenuItem = new MenuItem("Save");
        MenuItem exitMenuItem = new MenuItem("Exit");
        exitMenuItem.setOnAction(actionEvent -> Platform.exit());

        projectMenu.getItems().addAll(newMenuItem, saveMenuItem, new SeparatorMenuItem(), exitMenuItem);
        getMenus().add(projectMenu);
    }

    private void initializeEditMenu() {

    }

    private void initializeTaskMenu() {

    }

    private void initializeResourcesMenu() {

    }

}
