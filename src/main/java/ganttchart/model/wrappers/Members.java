package ganttchart.model.wrappers;

import ganttchart.model.Person;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;

/**
 * Created by gwszymanowski on 2017-08-04.
 */
@XmlRootElement(name = "members")
@XmlAccessorType(XmlAccessType.FIELD)
public final class Members {

    @XmlElement(name = "person")
    private Set<Person> members;

    public Members() {
    }

    public Members(Set<Person> members) {
        this.members = members;
    }

    public Set<Person> getMembers() {
        return members;
    }

    public void setMembers(Set<Person> members) {
        this.members = members;
    }
}
