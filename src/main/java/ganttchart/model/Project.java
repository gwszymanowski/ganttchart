package ganttchart.model;


import org.bson.Document;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by gwszymanowski on 2017-05-13.
 */
public class Project implements Comparable<Project> {

    private String _id;
    private String name;
    private User leader;
    private ProjectGroup group;
    private LocalDateTime startDate = LocalDateTime.now();
    private Set<Assignment> tasks = new LinkedHashSet<>();

    public Project() {
    }

    public Project(String name) {
        this.name = name;
    }

    public Project(User leader, ProjectGroup group) {
        this.leader = leader;
        this.group = group;
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

    public User getLeader() {
        return leader;
    }

    public void setLeader(User leader) {
        this.leader = leader;
    }

    public ProjectGroup getGroup() {
        return group;
    }

    public void setGroup(ProjectGroup group) {
        this.group = group;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public String getStartDateString() {

        if(startDate == null)
            return "null";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        return startDate.format(formatter);
    }

    public Set<Assignment> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Assignment> tasks) {
        this.tasks = tasks;
    }

    public Document toDocument() {
        Document document = new Document();
        document.append("id", _id);
        document.append("leader_id", leader == null ? "null" : leader.get_id() );
        document.append("group_id", group == null ? "null" : group.get_id());
        document.append("startDate", getStartDateString());

        return document;
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
}
