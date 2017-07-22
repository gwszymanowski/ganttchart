package ganttchart.util;

import dummy.DummyProjectFactory;
import ganttchart.model.Project;
import ganttchart.repository.ProjectRepository;
import ganttchart.service.ProjectService;
import javafx.scene.control.TableColumn;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertEquals;


/**
 * Created by gwszymanowski on 2017-07-21.
 */
public class ProjectServiceTest {


    @BeforeClass
    public static void setup() {

    }

    @Test
    public void getPeriodTest() {
        Project p = DummyProjectFactory.project1();

        List<TableColumn> columns = ProjectService.getPeriod(p);

        assertEquals(10, columns.size());

    }


}
