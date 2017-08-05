package ganttchart.service;

import com.mongodb.BasicDBList;
import ganttchart.model.Assignment;
import ganttchart.model.Person;
import ganttchart.model.Project;
import ganttchart.util.FileUtil;
import org.bson.Document;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by gwszymanowski on 2017-07-09.
 */
public final class AssignmentService {

    private AssignmentService() {
    }

    public static Document toDocument(final Assignment assignment) {
        Document document = new Document();
        document.append("title", assignment.getTitle());
        document.append("taskOwner", assignment.getTaskOwner().toString());
        document.append("startDate", FileUtil.convertDateToString(assignment.getStartDate()));
        document.append("finishDate", FileUtil.convertDateToString(assignment.getFinishDate()));
        document.append("completed", assignment.getCompleted());
        return document;
    }

    public static Assignment fromDocument(final Document document) {
        Assignment a = new Assignment();

        String title = (String) document.get("title");
        String fullname = (String) document.get("taskOwner");
        String startdateString = (String) document.get("startDate");
        String finishdateString = (String) document.get("finishDate");
        Integer completed = (Integer) document.get("completed");

        a.setTitle(title);
        a.setStartDate(FileUtil.convertStringToLocalDate(startdateString));
        a.setFinishDate(FileUtil.convertStringToLocalDate(finishdateString));
        a.setCompleted(completed);
        a.setTaskOwner(new Person(fullname));

        return a;
    }

    public static BasicDBList getAssignmentList(final Set<Assignment> assignments) {
        return assignments.stream().map(AssignmentService::toDocument).collect(Collectors.toCollection(BasicDBList::new));
    }

    public static List<LocalDate> getAllDays(Assignment a) {
        List<LocalDate> list = new LinkedList<>();
        LocalDate start = a.getStartDate();
        LocalDate end = a.getFinishDate().plusDays(1);

        while(!start.equals(end)) {
            list.add(start);
            start = start.plusDays(1);
        }

        return list;
    }

    public static List<String> getAllDaysToString(final Assignment a) {
        return getAllDays(a).stream().map(x -> FileUtil.convertDateToString(x)).collect(Collectors.toList());
    }

    public static int getDayNumber(LocalDate beginDate, final String currDateString) {
        LocalDate curr = FileUtil.convertStringToLocalDate(currDateString);
        return getDayNumber(beginDate, curr);
    }

    public static int getDayNumber(LocalDate beginDate, LocalDate finishDate) {
        int count = 0;
        LocalDate curr = finishDate;

        if(beginDate.compareTo(curr) > 0)
            throw new IllegalArgumentException("Second date cannot occur earlier than first one");

        while(!beginDate.equals(curr)) {
            count++;
            beginDate = beginDate.plusDays(1);
        }

        return count;
    }

    public static int getCellPercentageValue(LocalDate beginDate, LocalDate currDate) {
        String val = FileUtil.convertDateToString(currDate);
        return 100/getDayNumber(beginDate, val);
    }

    public static Assignment findAssignmentByTitle(Project p, String title) {
        Optional<Assignment> optional = p.getAssignments().stream().filter(x -> x.getTitle().equals(title)).findFirst();
        return optional.get();
    }

    public static long getLatePenalty(Assignment assignment) {
        int daysCount = getDayNumber(assignment.getStartDate(), assignment.getFinishDate());
        int currentCount = getDayNumber(assignment.getStartDate(), LocalDate.now());
        return currentCount/daysCount;
    }

}
