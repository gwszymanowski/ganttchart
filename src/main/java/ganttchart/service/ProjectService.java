package ganttchart.service;

import ganttchart.model.Assignment;
import ganttchart.model.Project;
import ganttchart.model.Person;
import ganttchart.util.FileUtil;
import ganttchart.util.TableColumnFactory;
import javafx.scene.control.TableColumn;
import org.bson.Document;

import java.time.LocalDate;
import java.util.*;

/**
 * Created by gwszymanowski on 2017-07-09.
 */
public final class ProjectService {

    private ProjectService() {}

    public static Document toDocument(final Project project) {
        Document document = new Document();
        document.append("name", project.getName());
        document.append("startDate", project.getStartDateString());
        document.append("members", PersonService.getMembersList(project.getMembers()));
        document.append("tasks", AssignmentService.getAssignmentList(project.getTasks()));
        return document;
    }

    public static Project fromDocument(final Document document) {
        Project project = new Project();

        String name = (String) document.get("name");
        project.setName(name);

        List<Document> memberDocuments = (List<Document>) document.get("members");
        memberDocuments.stream().forEach(x -> project.addMember(PersonService.fromDocument(x)));

        List<Document> taskDocuments = (List<Document>) document.get("tasks");
        taskDocuments.stream().forEach(x -> project.addTask(AssignmentService.fromDocument(x)));

        String startdateString = (String) document.get("startDate");
        project.setStartDate(FileUtil.convertStringToLocalDate(startdateString));

        return project;
    }

    public static List<TableColumn> getPeriod(Project p) {
        List<TableColumn> tb = new LinkedList<>();
        TableColumnFactory tcf = new TableColumnFactory();
        LocalDate first = p.getStartDate();
        LocalDate last = getLastDay(p.getTasks()).plusDays(1);

        while(!first.equals(last)) {
            TableColumn col = new TableColumn();
            col.setGraphic(tcf.getRotated(FileUtil.convertDateToString(first)));
            tb.add(col);
            first = first.plusDays(1);
        }
        return tb;
    }

    public static LocalDate getLastDay(final Set<Assignment> tasks) {
        return tasks.size() == 0 ? LocalDate.now() : tasks.stream().map(u -> u.getFinishDate()).max(LocalDate::compareTo).get();
    }

    public static String[] getMembersToArray(final Set<Person> members) {
        return members.stream().map(Person::toString).toArray(String[]::new);
    }

}
