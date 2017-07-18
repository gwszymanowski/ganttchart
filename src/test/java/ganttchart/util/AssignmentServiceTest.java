package ganttchart.util;

import ganttchart.service.AssignmentService;
import org.junit.Test;
import org.springframework.expression.spel.ast.Assign;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

/**
 * Created by gwszymanowski on 2017-07-18.
 */
public class AssignmentServiceTest {

    @Test
    public void getDayNumber() {
        LocalDate date = LocalDate.of(2017, 10, 12);
        String someDayInString = "2017-10-15";

        assertEquals(3, AssignmentService.getDayNumber(date, someDayInString));
    }

}
