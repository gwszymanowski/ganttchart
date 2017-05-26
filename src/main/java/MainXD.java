import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import ganttchart.model.Project;
import org.bson.Document;

/**
 * Created by gwszymanowski on 2017-05-26.
 */
public class MainXD {

    public static void main(String[] args) {

        MongoClientURI uri = new MongoClientURI(
                "mongodb://writer:writer@ganttchart-shard-00-00-ib0bs.mongodb.net:27017,ganttchart-shard-00-01-ib0bs.mongodb.net:27017,ganttchart-shard-00-02-ib0bs.mongodb.net:27017/ganttchart?ssl=true&replicaSet=ganttchart-shard-0&authSource=admin");

        MongoClient mongoClient = new MongoClient(uri);
        MongoDatabase database = mongoClient.getDatabase("ganttchart");

        MongoCollection<Document> documents = database.getCollection("project");

        Document docc = new Document();
        docc.append("name", "Gnicie");

        documents.insertOne(docc);

        System.out.println(documents.count());

    }

}
