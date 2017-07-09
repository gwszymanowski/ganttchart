package ganttchart.service;

import ganttchart.model.Project;
import ganttchart.model.User;
import ganttchart.util.FileUtil;
import ganttchart.util.TableColumnFactory;
import javafx.scene.control.TableColumn;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by gwszymanowski on 2017-07-09.
 */
public class ProjectService {

    private ProjectService() {}

    public static List<TableColumn> getPeriod(Project p) {
        List<TableColumn> tb = new LinkedList<>();
        TableColumnFactory tcf = new TableColumnFactory();
        LocalDate first = p.getStartDate();
        LocalDate last = p.getLastDay();

        while(!first.equals(last)) {
            TableColumn col = new TableColumn();
            col.setGraphic(tcf.getRotated(FileUtil.convertDateToString(first)));
            tb.add(col);
            first = first.plusDays(1);
        }
        return tb;
    }

    public static Document toDocument(Project project) {
        Document document = new Document();
        document.append("name", project.getName());
        document.append("startDate", project.getStartDateString());
        document.append("members", project.getMembersList());
        document.append("tasks", project.getTasks());
        return document;
    }

    public static Project fromDocument(Document document) {
        Project project = new Project();
        ObjectId _id = (ObjectId) document.get("_id");
        String name = (String) document.get("name");

        String startdateString = (String) document.get("startDate");
        project.set_id(_id);
        project.setName(name);

        List<User> members = project.getMembers();
        project.setMembers(members);
        project.setStartDate(FileUtil.convertStringToLocalDate(startdateString));

        return project;
    }

}
