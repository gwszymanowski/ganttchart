package ganttchart.util;

import com.mongodb.BasicDBList;
import dummy.AssignmentDummy;
import dummy.ProjectDummy;
import ganttchart.model.Assignment;
import ganttchart.model.Person;
import ganttchart.model.Project;
import ganttchart.service.AssignmentService;
import ganttchart.service.PersonService;
import ganttchart.service.ProjectService;
import javafx.scene.control.TableColumn;
import org.bson.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import java.time.LocalDate;
import java.util.*;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by gwszymanowski on 2017-07-25.
 */
@RunWith(Parameterized.class)
public class ProjectServiceTest {

    @Parameters
    public static Collection<Project[]> projects() {
        Collection<Project[]> collection = new ArrayList<>();

        Project[] all = {ProjectDummy.project1(), ProjectDummy.project2()};
        collection.add(all);

        return collection;
    }

    @Parameter
    public Project project1;

    @Parameter(1)
    public Project project2;

    @Test
    public void projectToDocumentTest() {
        Document document = new Document();
        document.append("name" , "School management system");
        document.append("startDate", "10-04-2017");

        BasicDBList members = new BasicDBList();
        members.add(PersonService.toDocument(new Person("Adam Smith")));
        members.add(PersonService.toDocument(new Person("John Locke")));
        document.append("members", members);

        Set<Assignment> dummy = AssignmentDummy.assignments1();
        BasicDBList tasks = new BasicDBList();
        dummy.stream().forEach(x -> tasks.add(AssignmentService.toDocument(x)));
        document.append("tasks", tasks);

        assertTrue(document.equals(ProjectService.toDocument(project1)));
    }

    @Test
    public void getLastDayTest1() {
        assertEquals(LocalDate.of(2017, 4, 15), ProjectService.getLastDay(project1.getTasks()));
    }

    @Test
    public void getLastDayTest2() {
        assertEquals(LocalDate.of(2017, 7, 10), ProjectService.getLastDay(project2.getTasks()));
    }

    @Test
    public void getMembersToArrayTest1() {
        String[] expected = {"Adam Smith", "John Locke"};
        String[] given = ProjectService.getMembersToArray(project1.getMembers());
        assertArrayEquals(expected, given);
    }

    @Test
    public void getMembersToArrayTest2() {
        String[] expected = {"Hans Zimmerman", "Krzysztof Marchewka"};
        String[] given = ProjectService.getMembersToArray(project2.getMembers());
        assertArrayEquals(expected, given);
    }

}
