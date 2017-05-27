package ganttchart.model;

import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by gwszymanowski on 2017-05-13.
 */
public class ProjectGroup {

    private ObjectId _id;
    private String name = "null";
    private List<User> members = new LinkedList<>();

    public ProjectGroup() {
    }

    public ProjectGroup(String name) {
        this.name = name;
    }

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
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
        document.append("name", name);
        document.append("members", members);

        return document;
    }

    public static ProjectGroup fromDocument(Document document) {
        ObjectId _id = (ObjectId) document.get("_id");
        String group_name = (String) document.get("name");

        ProjectGroup group = new ProjectGroup();
        group.set_id(_id);
        group.setName(group_name);

        return group;
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

    @Override
    public String toString() {
        return name;
    }
}
