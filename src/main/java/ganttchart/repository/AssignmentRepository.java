package ganttchart.repository;

import com.mongodb.client.MongoCollection;
import ganttchart.model.Assignment;
import ganttchart.service.AssignmentService;
import ganttchart.util.ConnectionManager;
import org.bson.Document;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

/**
 * Created by gwszymanowski on 2017-05-26.
 */
public class AssignmentRepository implements CRUD {

    private MongoCollection<Document> collection = null;

    public AssignmentRepository() {
        collection = ConnectionManager.getDatabase().getCollection("assignment");
    }

    public void save(Assignment entity) {
        collection.insertOne(AssignmentService.toDocument(entity));
    }

    public void delete(String title) {
        Document doc = collection.find(eq("title", title)).first();
        collection.deleteOne(doc);
    }

    public Assignment findByTitle(String title) {
        Document doc = collection.find(eq("title", title)).first();
        return AssignmentService.fromDocument(doc);
    }

    public List<Assignment> getAll() {
        List<Assignment> assignments = new LinkedList<>();

        Iterator<Document> documents = collection.find().iterator();
        while(documents.hasNext()) {
            Document next = documents.next();
            assignments.add(AssignmentService.fromDocument(next));
        }
        return assignments;
    }
}
