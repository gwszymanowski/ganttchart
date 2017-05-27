package ganttchart.repository;

import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import ganttchart.model.Assignment;
import org.bson.Document;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

/**
 * Created by gwszymanowski on 2017-05-26.
 */
public class AssignmentRepository {

    private MongoCollection<Document> collection = null;

    public AssignmentRepository() {
        MongoClientURI uri = new MongoClientURI(
                "mongodb://writer:writer@ganttchart-shard-00-00-ib0bs.mongodb.net:27017,ganttchart-shard-00-01-ib0bs.mongodb.net:27017,ganttchart-shard-00-02-ib0bs.mongodb.net:27017/ganttchart?ssl=true&replicaSet=ganttchart-shard-0&authSource=admin");

        MongoClient mongoClient = new MongoClient(uri);
        MongoDatabase database = mongoClient.getDatabase("ganttchart");
        collection = database.getCollection("assignment");
    }

    public void save(Assignment entity) {
        Document document = new Document();
        document.append("title", entity.getTitle());
        document.append("number", entity.getNumber());
        document.append("mark", entity.getMark());
        document.append("startDate", entity.getStartDate());
        document.append("finishDate", entity.getFinishDate());
        document.append("workingDays", entity.getWorkingDays());
        document.append("completed", entity.getCompleted());
        document.append("duration", entity.getDurationString());
        collection.insertOne(document);
    }

    public void delete(String title) {
        Document doc = collection.find(eq("title", title)).first();
        collection.deleteOne(doc);
    }

    public Assignment findByTitle(String title) {
        Document doc = collection.find(eq("title", title)).first();
        return Assignment.fromDocument(doc);
    }

    public List<Assignment> getAll() {
        List<Assignment> assignments = new LinkedList<>();

        Iterator<Document> documents = collection.find().iterator();
        while(documents.hasNext()) {
            Document next = documents.next();
            assignments.add(Assignment.fromDocument(next));
        }

        return assignments;
    }
}
