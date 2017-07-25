package ganttchart.util;

import dummy.AssignmentDummy;
import ganttchart.model.Assignment;
import ganttchart.service.AssignmentService;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static ganttchart.service.AssignmentService.getCellPercentageValue;
import static org.junit.Assert.assertEquals;

/**
 * Created by gwszymanowski on 2017-07-18.
 */
@RunWith(Parameterized.class)
public class AssignmentServiceTest {

    @Parameters
    public static Collection<Assignment[]> assignments() {
        Collection<Assignment[]> collection = new ArrayList<>();
        Assignment[] all = {AssignmentDummy.single1(), AssignmentDummy.single2()};
        collection.add(all);

        return collection;
    }

    @Parameter
    public Assignment assignment1;

    @Parameter(1)
    public Assignment assignment2;

    @Test
    public void getDayNumberTest() {
        int given = AssignmentService.getDayNumber(assignment1.getStartDate(),"05-07-2017");
        assertEquals(10,given);
    }

    @Test(expected=IllegalArgumentException.class)
    public void getDayNumberTest2() {
        int given = AssignmentService.getDayNumber(assignment1.getStartDate(),"05-02-2017");
        assertEquals(10,given);
    }

    @Test
    public void getAllDaysToStringTest1() {
        List<String> dates = AssignmentService.getAllDaysToString(assignment1);
        assertEquals(13, dates.size());
    }

    @Test
    public void getAllDaysToStringTest2() {
        List<String> dates = AssignmentService.getAllDaysToString(assignment2);
        assertEquals(14, dates.size());
    }

    @Test
    public void getCellPercentageValueTest1() {
        int average = AssignmentService.getCellPercentageValue(assignment1.getStartDate(), assignment1.getFinishDate());
        assertEquals(8, average);
    }

    @Test
    public void getCellPercentageValueTest2() {
        int average = AssignmentService.getCellPercentageValue(assignment2.getStartDate(), assignment2.getFinishDate());
        assertEquals(7, average);
    }

}
