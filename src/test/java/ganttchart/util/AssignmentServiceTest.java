package ganttchart.util;

import ganttchart.model.Assignment;
import ganttchart.service.AssignmentService;
import org.junit.Test;
import org.springframework.expression.spel.ast.Assign;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by gwszymanowski on 2017-07-18.
 */
public class AssignmentServiceTest {

    @Test
    public void getDayNumberTest() {
        LocalDate date = LocalDate.of(2017, 10, 12);
        String someDayInString = "15-10-2017";

        assertEquals(3, AssignmentService.getDayNumber(date, someDayInString));
    }

    @Test
    public void getAllDaysToStringTest() {
        LocalDate someDay = LocalDate.of(2017, 10, 15);
        LocalDate someOtherDay = LocalDate.of(2017, 10, 25);

        Assignment ass = new Assignment();
        ass.setStartDate(someDay);
        ass.setFinishDate(someOtherDay);

        List<String> dates = AssignmentService.getAllDaysToString(ass);

        assertEquals(10, dates.size());
    }

}
