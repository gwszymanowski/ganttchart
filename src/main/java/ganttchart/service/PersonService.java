package ganttchart.service;

import com.mongodb.BasicDBList;
import ganttchart.model.Person;
import javafx.collections.ObservableList;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by gwszymanowski on 2017-07-09.
 */
public class PersonService {

    private PersonService() {
    }

    public static Document toDocument(Person person) {
        Document document = new Document();
        document.append("firstname", person.getFirstname());
        document.append("lastname", person.getLastname());
        return document;
    }

    public static Person fromDocument(Document document) {
        Person person = new Person();

        String firstname = (String) document.get("firstname");
        String lastname = (String)document.get("lastname");

        person.setFirstname(firstname);
        person.setLastname(lastname);

        return person;
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
