package ganttchart.repository;


import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import ganttchart.model.Assignment;
import ganttchart.model.Project;
import ganttchart.model.ProjectGroup;
import ganttchart.model.User;
import org.bson.Document;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

/**
 * Created by gwszymanowski on 2017-05-17.
 */
public class ProjectRepository {

    private MongoCollection<Document> collection = null;

    public ProjectRepository() {
        MongoClientURI uri = new MongoClientURI(
                "mongodb://writer:writer@ganttchart-shard-00-00-ib0bs.mongodb.net:27017,ganttchart-shard-00-01-ib0bs.mongodb.net:27017,ganttchart-shard-00-02-ib0bs.mongodb.net:27017/ganttchart?ssl=true&replicaSet=ganttchart-shard-0&authSource=admin");

        MongoClient mongoClient = new MongoClient(uri);
        MongoDatabase database = mongoClient.getDatabase("ganttchart");
        collection = database.getCollection("project");
    }

    public void save(Project entity) {
        collection.insertOne(entity.toDocument());
    }

    public void delete(String name) {
        Document doc = collection.find(eq("name", name)).first();
        collection.deleteOne(doc);
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

    public long count() {
        return collection.count();
    }
}
