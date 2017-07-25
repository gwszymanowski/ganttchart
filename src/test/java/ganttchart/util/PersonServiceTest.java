package ganttchart.util;

import com.mongodb.BasicDBList;
import ganttchart.model.Person;
import ganttchart.service.PersonService;
import org.bson.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.junit.runners.Parameterized.Parameter;

import java.util.*;
import java.util.stream.Stream;

import static org.junit.Assert.assertTrue;

/**
 * Created by gwszymanowski on 2017-07-24.
 */
@RunWith(Parameterized.class)
public class PersonServiceTest {

    @Parameters
    public static Collection<Person[][]> foundingFathers() {
        Collection<Person[][]> collection = new ArrayList<>();

        Person[] people1 = {new Person("Benjamin Graham"), new Person("George Washington")};
        Person[] people2 = {new Person("Alexander Hamilton"), new Person("Joseph Warren"),
                new Person("Thomas Jefferson"), new Person("Thomas Paine"),
                new Person("Samuel Adams"), new Person("John Adams")};
        Person[] joined = Stream.concat(Arrays.stream(people1), Arrays.stream(people2))
                .toArray(Person[]::new);

        Person[][] all = {people1, people2, joined};

        collection.add(all);

        return collection;
    }

    @Parameter
    public Person[] people1;

    @Parameter(1)
    public Person[] people2;

    @Parameter(2)
    public Person[] joined;


    @Test
    public void personToDocumentTest() {
        Person person = people1[0];

        Document document = new Document();
        document.append("firstname", "Benjamin");
        document.append("lastname", "Graham");

        assertTrue(document.equals(PersonService.toDocument(person)));
    }

    @Test
    public void documentToPersonTest() {
        Document document = new Document();
        document.append("firstname", "Alexander");
        document.append("lastname", "Hamilton");

        assertTrue(people2[0].equals(PersonService.fromDocument(document)));
    }

    @Test
    public void getMembersToStringTest() {
        Set<Person> people = new HashSet<>(Arrays.asList(people1));
        Set<String> peopleString = new HashSet<>(Arrays.asList("Benjamin Graham", "George Washington"));

        assertTrue(peopleString.equals(PersonService.getMembersToString(people)));
    }

    @Test
    public void getNotAddedTest() {
        Set<Person> firstSet = new HashSet<>(Arrays.asList(people1));
        Set<Person> secondSet = new HashSet<>(Arrays.asList(people2));
        Set<Person> all = new HashSet<>(Arrays.asList(joined));

        PersonService.getNotAdded(firstSet, all);

        assertTrue(secondSet.equals(secondSet));
    }


}
