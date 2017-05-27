package ganttchart.model;

import org.bson.Document;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by gwszymanowski on 2017-05-13.
 */
public class ProjectGroup {

    private String _id;
    private String name;
    private List<User> members = new LinkedList<>();

    public ProjectGroup() {
    }

    public ProjectGroup(String name) {
        this.name = name;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getMembers() {
        return members;
    }

    public void setMembers(List<User> members) {
        this.members = members;
    }

    public Document toDocument() {
        Document document = new Document();
        document.append("id", this._id);

        String[] array = new String[members.size()];

        for(int i = 0; i < array.length; i++)
            array[i] = members.get(i).get_id();

        document.append("members", array);

        return document;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProjectGroup group = (ProjectGroup) o;

        return name.equals(group.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
