package dummy;

import ganttchart.model.Assignment;
import ganttchart.model.Person;

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
        Assignment a1 = new Assignment("Creating CRUD", a1begin, a1end, new Person("Adam Smith"));

        LocalDate a2begin = LocalDate.of(2017, 4, 10);
        LocalDate a2end = LocalDate.of(2017, 4, 13);
        Assignment a2 = new Assignment("Creating REST API", a2begin, a2end, new Person("John Locke"));

        return new LinkedHashSet<>(Arrays.asList(a1, a2));
    }

    public static Set<Assignment> assignments2() {
        LocalDate a1begin = LocalDate.of(2017, 7, 5);
        LocalDate a1end = LocalDate.of(2017, 7, 7);
        Assignment a1 = new Assignment("Developing system", a1begin, a1end, new Person("Hans Zimmerman"));

        LocalDate a2begin = LocalDate.of(2017, 7, 6);
        LocalDate a2end = LocalDate.of(2017, 7, 10);
        Assignment a2 = new Assignment("Refactoring", a2begin, a2end, new Person("Krzysztof Marchewka"));

        return new LinkedHashSet<>(Arrays.asList(a1, a2));
    }

    public static Assignment single1() {
        LocalDate a1begin = LocalDate.of(2017, 6, 25);
        LocalDate a1end = LocalDate.of(2017, 7, 7);

        return new Assignment("Having a break", a1begin, a1end, new Person("Johny Walker"));
    }

    public static Assignment single2() {
        LocalDate a1begin = LocalDate.of(2017, 7, 25);
        LocalDate a1end = LocalDate.of(2017, 8, 7);

        return new Assignment("Coming back to work", a1begin, a1end, new Person("Johny Walker"));
    }

}
