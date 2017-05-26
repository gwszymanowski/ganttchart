package ganttchart.gui;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * Created by gwszymanowski on 2017-05-13.
 */
public class MainTable extends TableView {

    public MainTable() {
        setEditable(true);
        initializeColumns();
    }

    private void initializeColumns() {
        TableColumn idCol = new TableColumn("No.");
        TableColumn markCol = new TableColumn("Mark");
        TableColumn taskNameCol = new TableColumn("Task name");
        TableColumn startCol = new TableColumn("Start");
        TableColumn finishCol = new TableColumn("Finish");
        TableColumn durationCol = new TableColumn("Duration");
        TableColumn completedCol = new TableColumn("Completed");


        getColumns().addAll(idCol, markCol, taskNameCol, startCol, finishCol, durationCol, completedCol);
    }

}
