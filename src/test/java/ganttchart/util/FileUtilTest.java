package ganttchart.util;


import org.junit.Test;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

/**
 * Created by gwszymanowski on 2017-07-06.
 */
public class FileUtilTest {

    @Test
    public void convertDateToString() {
        LocalDate date =  LocalDate.of(2013, 12, 18);
        String dateString = FileUtil.convertDateToString(date);
        assertEquals("18-12-2013", dateString);
    }

    @Test
    public void convertStringtoDate() {
        LocalDate date =  LocalDate.of(2013, 12, 18);
        String dateString = "18-12-2013";
        LocalDate given = FileUtil.convertStringToLocalDate(dateString);
        assertEquals(date, given);
    }

    @Test
    public void convertStringtoDateAmericanFormat() {
        LocalDate date =  LocalDate.of(2013, 12, 18);
        String dateString = "2013-12-18";
        LocalDate given = FileUtil.convertStringToLocalDateUSFormat(dateString);
        assertEquals(date, given);
    }

    @Test
    public void stringConcatenation() {
        assertEquals(14, FileUtil.concatenateString("Ala ", "has ", "a ", "cat.").length());
    }

}
