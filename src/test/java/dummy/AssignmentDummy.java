package dummy;

import ganttchart.model.Assignment;
import ganttchart.model.Person;
import ganttchart.model.builders.AssignmentBuilder;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by gwszymanowski on 2017-07-25.
 */
public final class AssignmentDummy {

    private AssignmentDummy(){}

    public static Set<Assignment> assignments1() {
        LocalDate a1begin = LocalDate.of(2017, 4, 10);
        LocalDate a1end = LocalDate.of(2017, 4, 15);

        Assignment a1 = new AssignmentBuilder().title("Creating CRUD").startDate(a1begin)
                .finishDate(a1end).taskOwner( new Person("Adam Smith")).build();

        LocalDate a2begin = LocalDate.of(2017, 4, 10);
        LocalDate a2end = LocalDate.of(2017, 4, 13);

        Assignment a2 = new AssignmentBuilder().title("Creating REST API").startDate(a2begin)
                .finishDate(a2end).taskOwner( new Person("John Locke")).build();

        return new LinkedHashSet<>(Arrays.asList(a1, a2));
    }

    public static Set<Assignment> assignments2() {
        LocalDate a1begin = LocalDate.of(2017, 7, 5);
        LocalDate a1end = LocalDate.of(2017, 7, 7);
        Assignment a1 = new AssignmentBuilder().title("Developing system").startDate(a1begin)
                .finishDate(a1end).taskOwner( new Person("Hans Zimmerman")).build();

        LocalDate a2begin = LocalDate.of(2017, 7, 6);
        LocalDate a2end = LocalDate.of(2017, 7, 10);

        Assignment a2 = new AssignmentBuilder().title("Refactoring").startDate(a2begin)
                .finishDate(a2end).taskOwner( new Person("Krzysztof Marchewka")).build();

        return new LinkedHashSet<>(Arrays.asList(a1, a2));
    }

    public static Assignment single1() {
        LocalDate a1begin = LocalDate.of(2017, 6, 25);
        LocalDate a1end = LocalDate.of(2017, 7, 7);

        return  new AssignmentBuilder().title("Having a break").startDate(a1begin)
                .finishDate(a1end).taskOwner( new Person("Johny Walker")).build();
    }

    public static Assignment single2() {
        LocalDate a1begin = LocalDate.of(2017, 7, 25);
        LocalDate a1end = LocalDate.of(2017, 8, 7);

        return  new AssignmentBuilder().title("Coming back to work").startDate(a1begin)
                .finishDate(a1end).taskOwner( new Person("Johny Walker")).build();
    }

}
