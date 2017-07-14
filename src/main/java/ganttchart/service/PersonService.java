package ganttchart.service;

import com.mongodb.BasicDBList;
import ganttchart.model.Person;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.LinkedList;
import java.util.List;

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

        ObjectId _id = (ObjectId) document.get("_id");
        String firstname = (String) document.get("firstname");
        String lastname = (String)document.get("lastname");

        person.set_id(_id);
        person.setFirstname(firstname);
        person.setLastname(lastname);

        return person;
    }

    public static BasicDBList getMembersList(final List<Person> members) {
        BasicDBList list = new BasicDBList();

        for(int i = 0; i < members.size(); i++)
            list.add(members.get(i));

        return list;
    }

    public static List<String> getMembersToString(final List<Person> members) {
        List<String> list = new LinkedList<>();

        for(Person p : members)
            list.add(p.toString());

        return list;
    }

    public static List<Person> getNotAdded(final List<Person> alreadyAdded, List<Person> allPeople) {
        for(Person p : allPeople)
            if(alreadyAdded.contains(p))
                allPeople.remove(p);

        return allPeople;
    }

}
