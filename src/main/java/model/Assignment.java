package model;

import com.fasterxml.jackson.annotation.JsonAnySetter;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * Created by gwszymanowski on 2017-05-17.
 */
public class Assignment implements Comparable<Assignment>{

    private int id;
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


    public int getId() {
        return id;
    }

    @JsonAnySetter
    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    @JsonAnySetter
    public void setTitle(String title) {
        this.title = title;
    }

    public int getNumber() {
        return number;
    }

    @JsonAnySetter
    public void setNumber(int number) {
        this.number = number;
    }

    public Type getMark() {
        return mark;
    }

    @JsonAnySetter
    public void setMark(Type mark) {
        this.mark = mark;
    }

    public String getName() {
        return name;
    }

    @JsonAnySetter
    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    @JsonAnySetter
    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getFinishDate() {
        return finishDate;
    }

    @JsonAnySetter
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

    @JsonAnySetter
    public void setWorkingDays(int workingDays) {
        this.workingDays = workingDays;
    }

    public int getCompleted() {
        return completed;
    }

    @JsonAnySetter
    public void setCompleted(int completed) {
        this.completed = completed;
    }

    public String getDurationString() {
        return durationString;
    }

    @JsonAnySetter
    public void setDurationString(Duration duration) {
        this.durationString = duration == null ? null : duration.toString();
    }

    @Override
    public int compareTo(Assignment o) {
        return startDate.compareTo(o.getStartDate());
    }
}
