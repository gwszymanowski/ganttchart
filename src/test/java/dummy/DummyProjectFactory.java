package dummy;

import ganttchart.model.Assignment;
import ganttchart.model.Project;
import org.apache.tomcat.jni.Local;

import java.time.LocalDate;

/**
 * Created by gwszymanowski on 2017-07-22.
 */
public final class DummyProjectFactory {

    private DummyProjectFactory() {}

    public static Project project1() {
        Project p = new Project();
        p.setName("project 1");
        LocalDate someDay = LocalDate.of(2017, 10, 1);
        p.setStartDate(someDay);

        Assignment ass1 = new Assignment();
        LocalDate day1 = LocalDate.of(2017, 10, 15);
        LocalDate day1next = LocalDate.of(2017, 10, 25);
        ass1.setStartDate(day1);
        ass1.setFinishDate(day1next);

        Assignment ass2 = new Assignment();
        LocalDate day2 = LocalDate.of(2017, 10, 12);
        LocalDate day2next = LocalDate.of(2017, 10, 15);
        ass2.setStartDate(day2);
        ass2.setFinishDate(day2next);

        p.addTasks(ass1, ass2);

        return p;
    }


    public static Project project2() {
        Project p = new Project();
        p.setName("project 2");
        LocalDate someDay = LocalDate.of(2017, 6, 1);
        p.setStartDate(someDay);

        Assignment ass1 = new Assignment();
        LocalDate day1 = LocalDate.of(2017, 6, 10);
        LocalDate day1next = LocalDate.of(2017, 6, 12);
        ass1.setStartDate(day1);
        ass1.setFinishDate(day1next);

        Assignment ass2 = new Assignment();
        LocalDate day2 = LocalDate.of(2017, 6, 16);
        LocalDate day2next = LocalDate.of(2017, 6, 18);
        ass2.setStartDate(day2);
        ass2.setFinishDate(day2next);

        p.addTasks(ass1, ass2);

        return p;
    }

}
