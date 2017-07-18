package ganttchart.model;

import ganttchart.util.FileUtil;
import org.bson.types.ObjectId;

import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.TemporalUnit;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by gwszymanowski on 2017-05-17.
*/
public class Assignment implements Comparable<Assignment>{

    private ObjectId _id;
    private String title = "null";
    private LocalDate startDate = LocalDate.now();
    private LocalDate finishDate = LocalDate.now();
    private transient int workingDays;
    private transient int completed; // in %
    private Person taskOwner = new Person();
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

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(LocalDate finishDate) {
        this.finishDate = finishDate;
    }

    public long getDuration() {
       // Duration duration = Duration.between(startDate, finishDate);
        return 0L;
    }

    public int getWorkingDays() {
        return workingDays;
    }

    public void setWorkingDays(int workingDays) {
        this.workingDays = workingDays;
    }

    public int getCompleted() {
        return completed;
    }

    public void setCompleted(int completed) {
        this.completed = completed;
    }

    public String startDateString() {
        return FileUtil.convertDateToString(startDate);
    }

    public String finishDateString() {
        return FileUtil.convertDateToString(finishDate);
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
