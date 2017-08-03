package dummy;

import ganttchart.model.Person;
import ganttchart.model.Project;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;

/**
 * Created by gwszymanowski on 2017-07-25.
 */
public final class ProjectDummy {

    private ProjectDummy(){}

    public static Project project1() {
        Project project1 = new Project();

        project1.setName("School management system");
        LocalDate beggining = LocalDate.of(2017, 4, 10);
        project1.setStartDate(beggining);
        Person[] people = {new Person("Adam Smith"), new Person("John Locke")};
        project1.setMembers(new HashSet<>(Arrays.asList(people)));
        project1.setAssignments(AssignmentDummy.assignments1());

        return project1;
    }

    public static Project project2() {
        Project project1 = new Project();

        project1.setName("Security system");
        LocalDate beggining = LocalDate.of(2017, 7, 5);
        project1.setStartDate(beggining);
        Person[] people = {new Person("Hans Zimmerman"), new Person("Krzysztof Marchewka")};
        project1.setMembers(new HashSet<>(Arrays.asList(people)));
        project1.setAssignments(AssignmentDummy.assignments2());

        return project1;
    }

}
