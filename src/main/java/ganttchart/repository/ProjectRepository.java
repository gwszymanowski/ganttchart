package ganttchart.repository;


import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import ganttchart.model.Project;
import org.bson.Document;

import java.util.List;

/**
 * Created by gwszymanowski on 2017-05-17.
 */
public class ProjectRepository implements CrudI<Project>{

    private MongoCollection<Document> collection = null;

    public ProjectRepository() {
        MongoClientURI uri = new MongoClientURI(
                "mongodb://writer:writer@ganttchart-shard-00-00-ib0bs.mongodb.net:27017,ganttchart-shard-00-01-ib0bs.mongodb.net:27017,ganttchart-shard-00-02-ib0bs.mongodb.net:27017/ganttchart?ssl=true&replicaSet=ganttchart-shard-0&authSource=admin");

        MongoClient mongoClient = new MongoClient(uri);
        MongoDatabase database = mongoClient.getDatabase("ganttchart");
        collection = database.getCollection("project");
    }

    @Override
    public void save(Project entity) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Project findById(int id) {
        return null;
    }

    @Override
    public List<Project> getAll() {
        return null;
    }
}
