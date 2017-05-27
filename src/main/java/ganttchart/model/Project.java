package ganttchart.model;


import ganttchart.util.FileUtil;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by gwszymanowski on 2017-05-13.
 */
public class Project implements Comparable<Project> {

    private ObjectId _id;
    private String name = "null";
    private User leader = new User();
    private ProjectGroup group = new ProjectGroup();
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
        return FileUtil.convertDateToString(startDate);
    }

    public Set<Assignment> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Assignment> tasks) {
        this.tasks = tasks;
    }

    public Document toDocument() {
        Document document = new Document();
        document.append("name", name);
        document.append("leader_id", leader.get_id() );
        document.append("group_id", group.get_id());
        document.append("startDate", getStartDateString());
        document.append("tasks", tasks);
        return document;
    }

    public static Project fromDocument(Document document) {
        Project project = new Project();
        ObjectId _id = (ObjectId) document.get("_id");
        String name = (String) document.get("name");
        ObjectId leader_id = (ObjectId) document.get("leader_id");
        ObjectId group_id = (ObjectId) document.get("group_id");
        String startdateString = (String) document.get("startDate");

        project.set_id(_id);
        project.setName(name);
        User l = project.getLeader();
        l.set_id(leader_id);

        ProjectGroup pg = project.getGroup();
        pg.set_id(group_id);

        project.setStartDate(FileUtil.convertStringToLocalDateTime(startdateString));

        return project;
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
