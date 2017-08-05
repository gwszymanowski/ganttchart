package ganttchart.model.builders;

import ganttchart.model.Assignment;
import ganttchart.model.Person;

import java.time.LocalDate;

/**
 * Created by gwszymanowski on 2017-08-05.
 */
public class AssignmentBuilder {

    private Assignment assignment;

    public AssignmentBuilder(){
        this.assignment = new Assignment();
    }

    public Assignment build() {
        return assignment;
    }

    public AssignmentBuilder title(String title) {
        assignment.setTitle(title);
        return this;
    }

    public AssignmentBuilder startDate(LocalDate startDate) {
        assignment.setStartDate(startDate);
        return this;
    }

    public AssignmentBuilder finishDate(LocalDate finishDate) {
        assignment.setFinishDate(finishDate);
        return this;
    }

    public AssignmentBuilder taskOwner(Person taskOwner) {
        assignment.setTaskOwner(taskOwner);
        return this;
    }

    public AssignmentBuilder completed(int completed) {
        assignment.setCompleted(completed);
        return this;
    }

}
