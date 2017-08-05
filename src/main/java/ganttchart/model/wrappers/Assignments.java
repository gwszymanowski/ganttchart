package ganttchart.model.wrappers;

import ganttchart.model.Assignment;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;

/**
 * Created by gwszymanowski on 2017-08-04.
 */
@XmlRootElement(name = "assignments")
@XmlAccessorType(XmlAccessType.FIELD)
public final class Assignments {

    @XmlElement(name = "assignment")
    private Set<Assignment> assignments;

    public Assignments(){}

    public Assignments(Set<Assignment> assignments) {
        this.assignments = assignments;
    }

    public Set<Assignment> getAssignments() {
        return assignments;
    }

    public void setAssignments(Set<Assignment> assignments) {
        this.assignments = assignments;
    }
}
