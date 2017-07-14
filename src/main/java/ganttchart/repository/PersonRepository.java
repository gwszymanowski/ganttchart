package ganttchart.repository;


import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import ganttchart.model.Person;
import ganttchart.service.PersonService;
import ganttchart.util.ConnectionManager;
import org.bson.Document;

import java.util.*;

/**
 * Created by gwszymanowski on 2017-05-17.
 */
public class PersonRepository implements CRUD {

    private MongoCollection<Document> collection = null;

    public PersonRepository() {
        collection = ConnectionManager.getDatabase().getCollection("person");
    }

    public void save(Person entity) {
        collection.insertOne(PersonService.toDocument(entity));
    }

    public Set<Person> getAll() {
        Set<Person> people = new HashSet<>();
        Iterator<Document> documents = collection.find().iterator();

        while(documents.hasNext()) {
            Document next = documents.next();
            people.add(PersonService.fromDocument(next));
        }

        return people;
    }

    public boolean ifExists(String firstname, String lastname) {
        Document doc = collection.find(Filters.and( Filters.eq("firstname", firstname),
                Filters.eq("lastname", lastname))).first();
        return doc != null ? true : false;
    }

    public void delete(String firstname, String lastname) {
        Document doc = collection.find(Filters.and( Filters.eq("firstname", firstname),
                Filters.eq("lastname", lastname))).first();
        collection.deleteOne(doc);
    }

}
