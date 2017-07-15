package ganttchart.gui.elements.cell;

import ganttchart.model.Assignment;
import javafx.scene.control.TableCell;
import javafx.scene.paint.Color;

/**
 * Created by gwszymanowski on 2017-07-15.
 */
public class DatesCell extends TableCell<Assignment, String> {

    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        Object rowItem = getTableRow().getItem();
        if (!isEmpty()) {
            this.setTextFill(Color.RED);
            // Get fancy and change color based on data

            setText(item);
        }
    }
}
