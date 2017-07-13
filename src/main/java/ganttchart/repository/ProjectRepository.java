package ganttchart.repository;


import com.mongodb.client.MongoCollection;
import ganttchart.model.Project;
import ganttchart.service.ProjectService;
import ganttchart.util.ConnectionManager;
import org.bson.Document;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

/**
 * Created by gwszymanowski on 2017-05-17.
 */
public class ProjectRepository implements CRUD {

    private MongoCollection<Document> collection = null;

    public ProjectRepository() {
        collection = ConnectionManager.getDatabase().getCollection("project");
    }

    public void save(Project entity) {
        collection.insertOne(ProjectService.toDocument(entity));
    }

    public List<Project> getAll() {
        List<Project> projects = new LinkedList<>();

        Iterator<Document> documents = collection.find().iterator();

        while(documents.hasNext()) {
            Document next = documents.next();
            projects.add(ProjectService.fromDocument(next));
        }

        return projects;
    }

    public Project findByName(String name) {
        Document doc = collection.find(eq("name", name)).first();
        return ProjectService.fromDocument(doc);
    }

    public boolean ifExists(String name) {
        Document doc = collection.find(eq("name", name)).first();
        return doc != null ? true : false;
    }

    public void delete(String title) {
        Document doc = collection.find(eq("title", title)).first();
        collection.deleteOne(doc);
    }
}
