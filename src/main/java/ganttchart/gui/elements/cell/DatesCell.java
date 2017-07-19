package ganttchart.gui.elements.cell;

import ganttchart.model.Assignment;
import ganttchart.model.Project;
import ganttchart.service.AssignmentService;
import javafx.scene.control.TableCell;

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

        if(!empty && getTableRow() != null) {
            Object tableItem = getTableRow().getItem();
            Optional<Assignment> assignmentOptional = project.getTasks().stream().filter(x -> x.getTitle().equals(tableItem.toString())).findFirst();

            if(assignmentOptional.isPresent()) {
                Assignment assignment = assignmentOptional.get();
                if (AssignmentService.getAllDaysToString(assignment).contains(item)) {

                    int dayNumber = AssignmentService.getDayNumber(assignment.getStartDate(), item);
                    int cellValue = AssignmentService.getCellPercentageValue(assignment.getStartDate(), assignment.getFinishDate());

                    if(dayNumber*cellValue < assignment.getCompleted())
                        setStyle("-fx-background-color:#8099FF");
                    else
                        setStyle("-fx-background-color:#808080");
                    setText(null);
                }
            }
        }

    }
}
