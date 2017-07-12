package ganttchart.repository;


import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import ganttchart.model.Person;
import ganttchart.service.PersonService;
import ganttchart.util.ConnectionManager;
import org.bson.Document;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

/**
 * Created by gwszymanowski on 2017-05-17.
 */
public class PersonRepository {

    private MongoCollection<Document> collection = null;

    public PersonRepository() {
        collection = ConnectionManager.getDatabase().getCollection("person");
    }

    public void save(Person entity) {
        collection.insertOne(PersonService.toDocument(entity));
    }

    public List<Person> getAll() {
        List<Person> people = new LinkedList<>();
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

}
