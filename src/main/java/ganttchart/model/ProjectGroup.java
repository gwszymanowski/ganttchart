package ganttchart.model;

import javafx.scene.control.TreeItem;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.*;

/**
 * Created by gwszymanowski on 2017-05-13.
 */
public class ProjectGroup {

    private ObjectId _id;
    private String name = "null";
    private List<User> members = new LinkedList<>();
    private User leader = new User();

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

    public ObjectId[] getMembersId() {
        ObjectId[] array = new ObjectId[members.size()];

        for(int i = 0; i < array.length; i++)
            array[i] = members.get(i).get_id();

        return array;
    }

    public String[] getMembersToString() {
        String[] array = new String[members.size()];

        for(int i = 0; i < array.length; i++)
            array[i] = members.get(i).toString();

        return array;
    }

    public User getLeader() {
        return leader;
    }

    public void setLeader(User leader) {
        this.leader = leader;
    }

    public Document toDocument() {
        Document document = new Document();
        document.append("name", name);
        document.append("members", members);
        document.append("leader_id", leader.get_id() );
        document.append("members", getMembersId());
        return document;
    }

    public static ProjectGroup fromDocument(Document document) {
        ProjectGroup group = new ProjectGroup();

        ObjectId _id = (ObjectId) document.get("_id");
        String group_name = (String) document.get("name");
        ObjectId leader_id = (ObjectId) document.get("leader_id");

        User l = group.getLeader();
        l.set_id(leader_id);

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
