package ganttchart.serialization.adapter;

import ganttchart.model.Assignment;
import ganttchart.model.Assignments;
import ganttchart.model.Members;
import ganttchart.model.Person;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.util.Set;

/**
 * Created by gwszymanowski on 2017-08-04.
 */
public class AssignmentsAdapter extends XmlAdapter<Assignments, Set<Assignment>> {


    @Override
    public Set<Assignment> unmarshal(Assignments v) throws Exception {
        return v.getAssignments();
    }

    @Override
    public Assignments marshal(Set<Assignment> v) throws Exception {
        return new Assignments(v);
    }
}
