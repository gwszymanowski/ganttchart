package ganttchart.repository;


import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import ganttchart.model.Project;
import ganttchart.model.ProjectGroup;
import ganttchart.model.User;
import org.bson.Document;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

/**
 * Created by gwszymanowski on 2017-05-17.
 */
public class UserRepository {

    private MongoCollection<Document> collection = null;

    public UserRepository() {
        MongoClientURI uri = new MongoClientURI(
                "mongodb://writer:writer@ganttchart-shard-00-00-ib0bs.mongodb.net:27017,ganttchart-shard-00-01-ib0bs.mongodb.net:27017,ganttchart-shard-00-02-ib0bs.mongodb.net:27017/ganttchart?ssl=true&replicaSet=ganttchart-shard-0&authSource=admin");

        MongoClient mongoClient = new MongoClient(uri);
        MongoDatabase database = mongoClient.getDatabase("ganttchart");
        collection = database.getCollection("user");
    }

    public void save(User entity) {
        collection.insertOne(entity.toDocument());
    }

    public void delete(String username) {
        Document doc = collection.find(eq("username", username)).first();
        collection.deleteOne(doc);
    }

}
