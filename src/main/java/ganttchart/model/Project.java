package ganttchart.model;


import ganttchart.util.FileUtil;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by gwszymanowski on 2017-05-13.
 */
@XmlRootElement
@XmlType(propOrder = { "name", "startDate", "members", "assignments" })
public class Project implements Comparable<Project>, Serializable {

    private String name = "null";
    private LocalDate startDate = LocalDate.now();
    @XmlElementWrapper(name = "assignments")
    private Set<Assignment> assignments = new TreeSet<>();
    @XmlElementWrapper(name = "members")
    private Set<Person> members = new HashSet<>();

    public Project() {
    }

    public Project(String name) {
        this.name = name;
    }

    public Project(String name, LocalDate startDate) {
        this.name = name;
        this.startDate = startDate;
    }

    @XmlElement(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Person> getMembers() { return members; }

    public void setMembers(Set<Person> members) { this.members = members; }

    public void addMember(Person person) {
        members.add(person);
    }

    public void addMembers(Person ... people) {
        members.addAll(Arrays.asList(people));
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    @XmlElement(name = "startDate")
    public String getStartDateString() {
        return startDate != null ? FileUtil.convertDateToString(startDate) : "null";
    }

    public Set<Assignment> getAssignments() {
        return assignments;
    }

    public void setAssignments(Set<Assignment> tasks) {
        this.assignments = tasks;
    }

    public void addAssignment(Assignment task) {
        assignments.add(task);
    }

    public void addAssignments(Assignment ... tasks) {
        assignments.addAll(Arrays.asList(tasks));
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
