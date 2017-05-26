package ganttchart.repository;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import ganttchart.model.Assignment;
import org.bson.Document;

import java.util.List;

/**
 * Created by gwszymanowski on 2017-05-26.
 */
public class AssignmentRepository implements CrudI<Assignment> {

    private MongoCollection<Document> collection = null;

    public AssignmentRepository() {
        MongoClientURI uri = new MongoClientURI(
                "mongodb://writer:writer@ganttchart-shard-00-00-ib0bs.mongodb.net:27017,ganttchart-shard-00-01-ib0bs.mongodb.net:27017,ganttchart-shard-00-02-ib0bs.mongodb.net:27017/ganttchart?ssl=true&replicaSet=ganttchart-shard-0&authSource=admin");

        MongoClient mongoClient = new MongoClient(uri);
        MongoDatabase database = mongoClient.getDatabase("ganttchart");
        collection = database.getCollection("assignment");
    }

    @Override
    public void save(Assignment entity) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Assignment findById(int id) {
        return null;
    }

    @Override
    public List<Assignment> getAll() {
        return null;
    }
}
