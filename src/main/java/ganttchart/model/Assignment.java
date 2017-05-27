package ganttchart.model;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * Created by gwszymanowski on 2017-05-17.
*/

public class Assignment implements Comparable<Assignment>{

    private String _id;
    private String title;
    private int number;
    private Type mark;
    private String name;
    private LocalDateTime startDate;
    private LocalDateTime finishDate;
    private Duration duration;
    private int workingDays;
    private int completed; // in %
    private String durationString;

    public Assignment() {
    }

    public void init() {
        this.duration = this.durationString == null ? null : Duration.parse(this.durationString);
    };


    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
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

    public Type getMark() {
        return mark;
    }

    public void setMark(Type mark) {
        this.mark = mark;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(LocalDateTime finishDate) {
        this.finishDate = finishDate;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
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

    public String getDurationString() {
        return durationString;
    }

    public void setDurationString(Duration duration) {
        this.durationString = duration == null ? null : duration.toString();
    }

    @Override
    public int compareTo(Assignment o) {
        return startDate.compareTo(o.getStartDate());
    }
}
