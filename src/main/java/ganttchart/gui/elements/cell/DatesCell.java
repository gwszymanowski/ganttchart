package ganttchart.gui.elements.cell;

import ganttchart.model.Assignment;
import ganttchart.model.Project;
import ganttchart.service.AssignmentService;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableRow;
import javafx.scene.paint.Color;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * Created by gwszymanowski on 2017-07-15.
 */
public class DatesCell extends TableCell<String, String> {

    private Project project;

    public DatesCell(Project project) {
        this.project = project;
    }

    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);

        if(!empty) {
            Object tableItem = getTableRow().getItem();
            Optional<Assignment> assignment = project.getTasks().stream().filter(x -> x.getTitle().equals(tableItem.toString())).findFirst();

            if(assignment.isPresent()) {
                if (AssignmentService.getAllDaysToString(assignment.get()).contains(item)) {
                    this.setTextFill(Color.RED);
                    setText(item);
                }
            }


        }

    }
}
