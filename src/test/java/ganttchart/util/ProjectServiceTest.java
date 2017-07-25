package ganttchart.util;

import dummy.ProjectDummy;
import ganttchart.model.Project;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

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




}
