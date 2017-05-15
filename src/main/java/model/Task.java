package model;

import java.beans.Transient;
import java.time.Duration;
import java.time.LocalDateTime;

/**
 * Created by gwszymanowski on 2017-05-13.
 */
public class Task implements Comparable<Task> {

    private String id;
    private int number;
    private Type mark;
    private String name;
    private LocalDateTime startDate;
    private LocalDateTime finishDate;
    private Duration duration;
    private int workingDays;
    private int completed; // in %


    private String durationString;

    public Task() {
    }

    public void init() {
        this.duration = this.durationString == null ? null : Duration.parse(this.durationString);
    };


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
    public int compareTo(Task o) {
        return startDate.compareTo(o.getStartDate());
    }
}
