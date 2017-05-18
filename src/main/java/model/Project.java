package model;


import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by gwszymanowski on 2017-05-13.
 */
public class Project implements Comparable<Project>{

    private int id;
    private String name;
    private User leader;
    private ProjectGroup group;
    private LocalDateTime startDate;
    private Set<Assignment> tasks = new LinkedHashSet<>();

    public Project() {

    }

    public Project(User leader, ProjectGroup group) {
        this.leader = leader;
        this.group = group;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Set<Assignment> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Assignment> tasks) {
        this.tasks = tasks;
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
