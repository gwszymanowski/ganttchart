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
import java.util.Optional;
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
        document.append("taskOwner", assignment.getTaskOwner().toString());
        document.append("startDate", assignment.startDateString());
        document.append("finishDate", assignment.finishDateString());
        document.append("completed", assignment.getCompleted());
        return document;
    }

    public static Assignment fromDocument(Document document) {
        Assignment a = new Assignment();
        String title = (String) document.get("title");

      //  String fullname = (String) document.get("taskOwner");
        String startdateString = (String) document.get("startDate");
        String finishdateString = (String) document.get("finishDate");
        Integer completed = (Integer) document.get("completed");

        a.setTitle(title);
        a.setStartDate(FileUtil.convertStringToLocalDate(startdateString));
        a.setFinishDate(FileUtil.convertStringToLocalDate(finishdateString));
        a.setCompleted(completed);
        a.setTaskOwner(new Person("Seba galecki"));

        return a;
    }

    public static BasicDBList getAssignmentList(final List<Assignment> assignments) {
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

    public static List<String> getAllDaysToString(Assignment a) {
        List<String> list = new LinkedList<>();
        LocalDate start = a.getStartDate();
        LocalDate end = a.getFinishDate().plusDays(1);

        while(!start.equals(end)) {
            list.add(FileUtil.convertDateToString(start));
            start = start.plusDays(1);
        }

        return list;
    }

    public static int getDayNumber(LocalDate beginDate, String currDateString) {
        int count = 0;
        LocalDate curr = FileUtil.convertStringToLocalDate(currDateString);

        while(!beginDate.equals(curr)) {
            count++;
            beginDate = beginDate.plusDays(1);
        }

        return count;
    }

    public static int getCellPercentageValue(LocalDate beginDate, LocalDate currDate) {
        int count = 0;

        while(!beginDate.equals(currDate)) {
            count++;
            beginDate = beginDate.plusDays(1);
        }

        return 100/count;
    }

    public static Assignment findAssignmentByTitle(Project p, String title) {
        Optional<Assignment> optional = p.getTasks().stream().filter(x -> x.getTitle().equals(title)).findFirst();
        return optional.get();
    }

}
