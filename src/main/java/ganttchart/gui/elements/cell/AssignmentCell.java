package ganttchart.gui.elements.cell;

import ganttchart.model.Assignment;
import javafx.scene.control.TableCell;

/**
 * Created by gwszymanowski on 2017-07-14.
 */
public class AssignmentCell extends TableCell<Assignment, String> {

    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        Object rowItem = getTableRow().getItem();
        if(rowItem != null) {

        }
    }

}
