package ganttchart.service;

import ganttchart.model.Person;
import org.bson.Document;
import org.bson.types.ObjectId;

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
}
