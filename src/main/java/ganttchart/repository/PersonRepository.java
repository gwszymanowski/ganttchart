package ganttchart.repository;


import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import ganttchart.model.Person;
import ganttchart.model.Project;
import ganttchart.service.PersonService;
import ganttchart.service.ProjectService;
import ganttchart.util.ConnectionManager;
import org.bson.Document;

import java.util.*;

import static com.mongodb.client.model.Filters.eq;

/**
 * Created by gwszymanowski on 2017-05-17.
 */
public final class PersonRepository<Repositable> implements Repositorable<Person> {

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

    public void update(Person oldPerson, Person newPerson) {
        collection.replaceOne(Filters.and( Filters.eq("firstname", oldPerson.getFirstname()),
                Filters.eq("lastname", oldPerson.getLastname())), PersonService.toDocument(newPerson));
    }

}
