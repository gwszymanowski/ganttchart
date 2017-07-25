package ganttchart.util;


import ganttchart.model.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.junit.runners.Parameterized.Parameter;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

/**
 * Created by gwszymanowski on 2017-07-06.
 */
@RunWith(Parameterized.class)
public class FileUtilTest {

    @Parameters
    public static Collection<LocalDate[]> days() {
        Collection<LocalDate[]> collection = new ArrayList<>();

        LocalDate day1 =  LocalDate.of(2013, 12, 18);
        LocalDate day2 =  LocalDate.of(2015, 8, 3);

        LocalDate[] arrays = {day1, day2};
        collection.add(arrays);

        return collection;
    }

    @Parameter
    public LocalDate day1;

    @Parameter(1)
    public LocalDate day2;

    @Test
    public void convertDateToString() {
        String dateString = FileUtil.convertDateToString(day1);
        assertEquals("18-12-2013", dateString);
    }

    @Test
    public void convertStringtoDate() {
        String dateString = "03-08-2015";
        LocalDate given = FileUtil.convertStringToLocalDate(dateString);
        assertEquals(day2, given);
    }

    @Test
    public void convertStringtoDateAmericanFormat() {
        String dateString = "2013-12-18";
        LocalDate given = FileUtil.convertStringToLocalDate(dateString);
        assertEquals(day1, given);
    }

    @Test
    public void stringConcatenation() {
        assertEquals(14, FileUtil.concatenateString("Ala ", "has ", "a ", "cat.").length());
    }

}
