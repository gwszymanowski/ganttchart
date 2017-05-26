import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import ganttchart.model.Project;
import ganttchart.model.ProjectGroup;
import ganttchart.repository.ProjectGroupRepository;
import ganttchart.repository.ProjectRepository;
import org.bson.Document;

/**
 * Created by gwszymanowski on 2017-05-26.
 */
public class MainXD {

    public static void main(String[] args) {

//        MongoClientURI uri = new MongoClientURI(
//                "mongodb://writer:writer@ganttchart-shard-00-00-ib0bs.mongodb.net:27017,ganttchart-shard-00-01-ib0bs.mongodb.net:27017,ganttchart-shard-00-02-ib0bs.mongodb.net:27017/ganttchart?ssl=true&replicaSet=ganttchart-shard-0&authSource=admin");
//
//        MongoClient mongoClient = new MongoClient(uri);
//        MongoDatabase database = mongoClient.getDatabase("ganttchart");
//
//        database.createCollection("assignment");
//        database.createCollection("projectGroup");
//        database.createCollection("user");

        ProjectRepository repo = new ProjectRepository();
        System.out.println(repo.findById(0));

    }

}
