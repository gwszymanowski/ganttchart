package ganttchart.entity;


import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import ganttchart.model.Project;
import ganttchart.util.ConnectionManager;
import org.bson.Document;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

/**
 * Created by gwszymanowski on 2017-05-17.
 */
public class
ProjectRepository {

    private MongoCollection<Document> collection = null;

    public ProjectRepository() {
        collection = ConnectionManager.getDatabase().getCollection("project");
    }

    public void save(Project entity) {
        collection.insertOne(entity.toDocument());
    }

    public List<Project> getAll() {
        List<Project> projects = new LinkedList<>();

        Iterator<Document> documents = collection.find().iterator();

        while(documents.hasNext()) {
            Document next = documents.next();
            projects.add(Project.fromDocument(next));
        }

        return projects;
    }

    public Project findByName(String name) {
        Document doc = collection.find(eq("name", name)).first();
        return Project.fromDocument(doc);
    }

    public boolean validateByName(String name) {
        Document doc = collection.find(eq("name", name)).first();
        return doc != null ? true : false;
    }

    public long count() {
        return collection.count();
    }
}
