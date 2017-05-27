package ganttchart.gui.gui.general;

import ganttchart.gui.GanttMenu;
import ganttchart.gui.MainTable;
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
