package ganttchart.service;

import com.mongodb.BasicDBList;
import ganttchart.model.Person;
import javafx.collections.ObservableList;
import org.bson.Document;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by gwszymanowski on 2017-07-09.
 */
public final class PersonService {

    private PersonService() {
    }

    public static Document toDocument(final Person person) {
        Document document = new Document();
        document.append("firstname", person.getFirstname());
        document.append("lastname", person.getLastname());
        return document;
    }

    public static Person fromDocument(final Document document) {
        String firstname = (String) document.get("firstname");
        String lastname = (String)document.get("lastname");

        return new Person(firstname, lastname);
    }

    public static BasicDBList getMembersList(final Set<Person> members) {
        return members.stream().map(PersonService::toDocument).collect(Collectors.toCollection(BasicDBList::new));
    }

    public static Set<String> getMembersToString(final Set<Person> members) {
        return members.stream().map(Person::toString).collect(Collectors.toCollection(HashSet::new));
    }

    public static Set<Person> getNotAdded(final Set<Person> alreadyAdded, Set<Person> allPeople) {
        return allPeople.stream().filter(x -> !alreadyAdded.contains(x)).collect(Collectors.toCollection(HashSet::new));
    }

    public static Set<Person> getMembers(final ObservableList<String> elements) {
        Set<Person> people = new HashSet<>();
        for(String fullname : elements) {
            String[] text = fullname.split(" ");
            people.add(new Person(text[0], text[1]));
        }

        return people;
    }

}
