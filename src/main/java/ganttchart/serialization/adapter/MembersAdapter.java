package ganttchart.serialization.adapter;

import ganttchart.model.wrappers.Members;
import ganttchart.model.Person;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.util.Set;

/**
 * Created by gwszymanowski on 2017-08-04.
 */
@XmlRootElement(name = "members")
@XmlAccessorType(XmlAccessType.FIELD)
public class MembersAdapter extends XmlAdapter<Members, Set<Person>> {

    @Override
    public Set<Person> unmarshal(Members v) throws Exception {
        return v.getMembers();
    }

    @Override
    public Members marshal(Set<Person> v) throws Exception {
        return new Members(v);
    }

}
