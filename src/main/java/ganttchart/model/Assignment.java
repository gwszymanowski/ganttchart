package ganttchart.model;

import ganttchart.util.FileUtil;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

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

    private String finishDateString() {
        return FileUtil.convertDateToString(finishDate);
    }

    public Document toDocument() {
        Document document = new Document();
        document.append("title", title);
        document.append("number", number);
        document.append("startDate", startDateString());
        document.append("finishDate", finishDateString());
        document.append("workingDays", workingDays);
        document.append("completed", completed);
        return document;
    }

    public static Assignment fromDocument(Document document) {
        Assignment a = new Assignment();
        ObjectId _id = (ObjectId) document.get("_id");
        String title = (String) document.get("title");
        Integer number = (Integer) document.get("number");
        String mark = (String) document.get("mark");
        String startdateString = (String) document.get("startDate");
        String finishdateString = (String) document.get("finishDate");
        Integer workingDays = (Integer) document.get("workingDays");
        Integer completed = (Integer) document.get("completed");

        a.set_id(_id);
        a.setTitle(title);
        a.setNumber(number);
        a.setStartDate(FileUtil.convertStringToLocalDate(startdateString));
        a.setFinishDate(FileUtil.convertStringToLocalDate(finishdateString));
        a.setWorkingDays(workingDays);
        a.setCompleted(completed);

        return a;
    }

    @Override
    public int compareTo(Assignment o) {
        return startDate.compareTo(o.getStartDate());
    }
}
