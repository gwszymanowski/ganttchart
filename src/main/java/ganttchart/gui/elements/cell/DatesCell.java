package ganttchart.gui.elements.cell;

import ganttchart.gui.elements.dialog.chart.ProgressChart;
import ganttchart.model.Assignment;
import ganttchart.model.Project;
import ganttchart.service.AssignmentService;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TableCell;
import javafx.scene.input.MouseEvent;

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
            Optional<Assignment> assignmentOptional = project.getAssignments().stream().filter(x -> x.getTitle().equals(tableItem.toString())).findFirst();

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
                    setOnMouseClicked(new ProgressChartAction(assignment));
                }
            }
        }

    }

    private class ProgressChartAction implements EventHandler<MouseEvent> {

        private Assignment assignment;

        public ProgressChartAction(Assignment assignment) {
            this.assignment = assignment;
        }

        @Override
        public void handle(MouseEvent event) {
            ProgressChart chart = new ProgressChart(assignment);
            chart.showAndWait();
        }
    }
}
