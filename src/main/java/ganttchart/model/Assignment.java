package ganttchart.model;

import ganttchart.util.FileUtil;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by gwszymanowski on 2017-05-17.
*/
public class Assignment implements Comparable<Assignment>{

    private ObjectId _id;
    private String title = "null";
    private int number = 0;
    private LocalDate startDate = LocalDate.now();
    private LocalDate finishDate = LocalDate.now();
    private int workingDays;
    private int completed; // in %
    private List<Assignment> subTasks = new LinkedList<>();

    public Assignment() {
    }

    public ObjectId get_id() {
        return _id;
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

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
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

    public String getDuration() {
        return "NONE";
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

    public void addSubTask(Assignment assignment) {
        subTasks.add(assignment);
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
}
