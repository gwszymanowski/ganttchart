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

import static org.hamcrest.CoreMatchers.both;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.isEmptyString;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
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
    public LocalDate expected1;

    @Parameter(1)
    public LocalDate expected2;

    @Test
    public void convertDateToString() {
        String given = FileUtil.convertDateToString(expected1);
        assertThat("18-12-2013", is(equalTo(given)));
    }

    @Test
    public void convertStringtoDate() {
        LocalDate given = FileUtil.convertStringToLocalDate("03-08-2015");
        assertThat(expected2, is(equalTo(given)));
    }

    @Test
    public void convertStringtoDateAmericanFormat() {
        LocalDate given = FileUtil.convertStringToLocalDate("2013-12-18");
        assertEquals(expected1, given);
    }

    @Test
    public void stringConcatenation() {
        String given = FileUtil.concatenateString("Ala ", "has ", "a ", "cat.");
        String expected = "Ala has a cat.";
        assertThat(expected, is(given));
    }

}
