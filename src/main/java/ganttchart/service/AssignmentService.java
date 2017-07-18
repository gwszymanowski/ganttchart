package ganttchart.service;

import com.mongodb.BasicDBList;
import ganttchart.model.Assignment;
import ganttchart.model.Person;
import ganttchart.model.Project;
import ganttchart.util.FileUtil;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.expression.spel.ast.Assign;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by gwszymanowski on 2017-07-09.
 */
public class AssignmentService {

    private AssignmentService() {
    }

    public static Document toDocument(Assignment assignment) {
        Document document = new Document();
        document.append("title", assignment.getTitle());
        document.append("number", assignment.getNumber());
        document.append("startDate", assignment.startDateString());
        document.append("finishDate", assignment.finishDateString());
        document.append("workingDays", assignment.getWorkingDays());
        document.append("completed", assignment.getCompleted());
        return document;
    }

    public static Assignment fromDocument(Document document) {
        Assignment a = new Assignment();
        ObjectId _id = (ObjectId) document.get("_id");
        String title = (String) document.get("title");
        Integer number = (Integer) document.get("number");
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

    public static BasicDBList getAssignmentList(final List<Assignment> assignments) {
        return assignments.stream().map(AssignmentService::toDocument).collect(Collectors.toCollection(BasicDBList::new));
    }

    public static List<LocalDate> getAllDays(Assignment a) {
        List<LocalDate> list = new LinkedList<>();
        LocalDate start = a.getStartDate();
        LocalDate end = a.getFinishDate();

        while(!start.equals(end)) {
            list.add(start);
            start = start.plusDays(1);
        }

        return list;
    }

    public static List<String> getAllDaysToString(Assignment a) {
        List<String> list = new LinkedList<>();
        LocalDate start = a.getStartDate();
        LocalDate end = a.getFinishDate();

        while(!start.equals(end)) {
            list.add(FileUtil.convertDateToString(start));
            start = start.plusDays(1);
        }

        return list;
    }



}
