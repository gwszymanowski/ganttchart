package gui.gui.general;

import gui.GanttMenu;
import gui.MainTable;
import javafx.scene.layout.BorderPane;

/**
 * Created by gwszymanowski on 2017-05-02.
 */
public class MainFrame extends BorderPane {

    private MainTable mainTable;
    private GanttMenu ganttMenu;

    public MainFrame() {
        initializeMenu();
        initializeBody();
    }

    private void initializeMenu() {
        ganttMenu = new GanttMenu();
        setTop(ganttMenu);
    }

    private void initializeBody() {
        mainTable = new MainTable();
        setCenter(mainTable);
    }


}
