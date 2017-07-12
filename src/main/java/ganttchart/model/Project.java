package ganttchart.model;


import com.mongodb.BasicDBList;
import ganttchart.util.FileUtil;
import org.bson.types.ObjectId;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by gwszymanowski on 2017-05-13.
 */
public class Project implements Comparable<Project> {

    private ObjectId _id;
    private String name = "null";
    private LocalDate startDate = LocalDate.now();
    private Set<Assignment> tasks = new LinkedHashSet<>();
    private List<Person> members = new LinkedList<>();

    public Project() {
    }

    public Project(String name) {
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

    public List<Person> getMembers() { return members; }

    public void setMembers(List<Person> members) { this.members = members; }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public String getStartDateString() {
        return startDate != null ? FileUtil.convertDateToString(startDate) : "null";
    }

    public Set<Assignment> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Assignment> tasks) {
        this.tasks = tasks;
    }

    public BasicDBList getMembersList() {
        BasicDBList members = new BasicDBList();

        for(int i = 0; i < members.size(); i++)
            members.add(members.get(i));

        return members;
    }

    public LocalDate getLastDay() {
        if(tasks.size() == 0)
            return LocalDate.now();

        return tasks.stream().map(u -> u.getFinishDate()).max(LocalDate::compareTo).get();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Project project = (Project) o;

        return name != null ? name.equals(project.name) : project.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    @Override
    public int compareTo(Project o) {
        return startDate.compareTo(o.getStartDate());
    }

    @Override
    public String toString() {
        return name;
    }
}
