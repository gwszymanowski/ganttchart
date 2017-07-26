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

import static org.hamcrest.CoreMatchers.both;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
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
        Document expected = new Document();
        expected.append("firstname", "Benjamin");
        expected.append("lastname", "Graham");

        Person person = people1[0];
        Document given = PersonService.toDocument(person);

        assertThat(expected, is(equalTo(given)));
    }

    @Test
    public void documentToPersonTest() {
        Document document = new Document();
        document.append("firstname", "Alexander");
        document.append("lastname", "Hamilton");

        Person given = PersonService.fromDocument(document);
        Person expected = people2[0];
        assertThat(expected, is(equalTo(given)));
    }

    @Test
    public void getMembersToStringTest() {
        Set<String> expected = new HashSet<>(Arrays.asList("Benjamin Graham", "George Washington"));

        Set<Person> data = new HashSet<>(Arrays.asList(people1));
        Set<String> given = PersonService.getMembersToString(data);

        assertThat(expected, both(hasSize(2)).and(is(equalTo(given))));
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
