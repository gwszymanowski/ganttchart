package ganttchart.repository;


import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import ganttchart.model.Person;
import ganttchart.service.UserService;
import ganttchart.util.ConnectionManager;
import org.bson.Document;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

/**
 * Created by gwszymanowski on 2017-05-17.
 */
public class UserRepository {

    private MongoCollection<Document> collection = null;

    public UserRepository() {
        collection = ConnectionManager.getDatabase().getCollection("user");
    }

    public void save(Person entity) {
        collection.insertOne(UserService.toDocument(entity));
    }

    public boolean validateByUsername(String username) {
        FindIterable<Document> it = collection.find(eq("username", username)).limit(1);
        return it.first() != null ? true : false;
    }

    public boolean validateByLastname(String lastname) {
        FindIterable<Document> it = collection.find(eq("lastname", lastname)).limit(1);
        return it.first() != null ? true : false;
    }

    public Person findByLastname(String lastname) {
        FindIterable<Document> it = collection.find(eq("lastname", lastname)).limit(1);
        return it.first() != null ? UserService.fromDocument(it.first()) : new Person();
    }

    public boolean validate(String username, String password) {
        BasicDBObject query = new BasicDBObject();
        query.put("username", username);
        query.put("password", password);

        FindIterable<Document> it = collection.find(query).limit(1);

        return it.first() != null ? true : false;
    }

    public List<Person> getAll() {
        List<Person> people = new LinkedList<>();
        Iterator<Document> documents = collection.find().iterator();

        while(documents.hasNext()) {
            Document next = documents.next();
            people.add(UserService.fromDocument(next));
        }

        return people;
    }

}
