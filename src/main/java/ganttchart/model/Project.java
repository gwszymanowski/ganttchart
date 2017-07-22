package ganttchart.model;


import ganttchart.util.FileUtil;
import org.bson.types.ObjectId;

import java.time.LocalDate;
import java.util.*;

/**
 * Created by gwszymanowski on 2017-05-13.
 */
public class Project implements Comparable<Project> {

    private ObjectId _id;
    private String name = "null";
    private LocalDate startDate = LocalDate.now();
    private List<Assignment> tasks = new LinkedList<>();
    private Set<Person> members = new HashSet<>();

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

    public Set<Person> getMembers() { return members; }

    public void setMembers(Set<Person> members) { this.members = members; }

    public void addMember(Person person) {
        members.add(person);
    }

    public String[] getMembersToArray() {
        String[] array = new String[members.size()];

        Iterator<Person> people = members.iterator();
        int i = 0;
        while(people.hasNext()){
            array[i] = people.next().toString();
            i++;
        }

        return array;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public String getStartDateString() {
        return startDate != null ? FileUtil.convertDateToString(startDate) : "null";
    }

    public List<Assignment> getTasks() {
        return tasks;
    }

    public void setTasks(List<Assignment> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Assignment task) {
        tasks.add(task);
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
