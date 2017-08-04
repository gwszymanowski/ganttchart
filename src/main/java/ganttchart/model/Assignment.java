package ganttchart.model;

import ganttchart.serialization.adapter.LocalDateAdapter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;

/**
 * Created by gwszymanowski on 2017-05-17.
*/
@XmlRootElement
@XmlType(propOrder={"title","startDate","finishDate", "taskOwner", "completed"})
public class Assignment implements Comparable<Assignment>, Serializable {

    private String title = "null";
    private LocalDate startDate = LocalDate.now();
    private LocalDate finishDate = LocalDate.now();
    private Person taskOwner = new Person();
    private int completed; // in %
    private transient long duration;


    public Assignment() {
    }

    public Assignment(String title, LocalDate startDate, LocalDate finishDate) {
        this.title = title;
        this.startDate = startDate;
        this.finishDate = finishDate;
    }

    public Assignment(String title, LocalDate startDate, LocalDate finishDate, Person taskOwner) {
        this(title, startDate, finishDate);
        this.taskOwner = taskOwner;
    }

    @XmlElement(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.duration = Duration.between(startDate.atTime(0, 0), finishDate.atTime(0, 0)).toDays();
        this.startDate = startDate;
    }

    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    public LocalDate getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(LocalDate finishDate) {
        this.duration = Duration.between(startDate.atTime(0, 0), finishDate.atTime(0, 0)).toDays();
        this.finishDate = finishDate;
    }

    public long getDuration() {
        return duration;
    }

    public long getWorkingDays() {
        long duration =Duration.between(LocalDate.now().atTime(0, 0), finishDate.atTime(0, 0)).toDays();
        return duration < 0 ? 0 : duration;
    }

    public int getCompleted() {
        return completed;
    }

    public void setCompleted(int completed) {
        this.completed = completed;
    }

    @XmlElement(name = "taskOwner")
    public Person getTaskOwner() {
        return taskOwner;
    }

    public void setTaskOwner(Person taskOwner) {
        this.taskOwner = taskOwner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Assignment that = (Assignment) o;

        if (!title.equals(that.title)) return false;
        if (!startDate.equals(that.startDate)) return false;
        return finishDate.equals(that.finishDate);
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + startDate.hashCode();
        result = 31 * result + finishDate.hashCode();
        return result;
    }

    @Override
    public int compareTo(Assignment o) {
        return startDate.compareTo(o.getStartDate());
    }

    @Override
    public String toString() {
        return title;
    }
}
