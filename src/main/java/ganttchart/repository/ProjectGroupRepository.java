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

import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import static com.mongodb.client.model.Filters.eq;

/**
 * Created by gwszymanowski on 2017-05-17.
 */
public class ProjectGroupRepository implements CrudI<ProjectGroup>{

    private MongoCollection<Document> collection = null;

    public ProjectGroupRepository() {
        MongoClientURI uri = new MongoClientURI(
                "mongodb://writer:writer@ganttchart-shard-00-00-ib0bs.mongodb.net:27017,ganttchart-shard-00-01-ib0bs.mongodb.net:27017,ganttchart-shard-00-02-ib0bs.mongodb.net:27017/ganttchart?ssl=true&replicaSet=ganttchart-shard-0&authSource=admin");

        MongoClient mongoClient = new MongoClient(uri);
        MongoDatabase database = mongoClient.getDatabase("ganttchart");
        collection = database.getCollection("projectGroup");
        System.out.println(collection.count());
    }

    @Override
    public void save(ProjectGroup entity) {
        collection.insertOne(entity.toDocument());
    }

    @Override
    public void delete(ProjectGroup entity) {
        Document doc = new Document();
        collection.deleteOne(doc);
    }

//    @Override
//    public ProjectGroup findById(int id) {
//        Document doc = collection.find(eq("id", id)).first();
//        Gson gson = new Gson();
//        return gson.fromJson(String.valueOf(doc), ProjectGroup.class);
//    }

    @Override
    public List<ProjectGroup> getAll() {
        List<ProjectGroup> groups = new LinkedList<>();
        Iterator<Document> documents = collection.find().iterator();

        Gson gson = new Gson();

        while(documents.hasNext()) {
            ProjectGroup group = gson.fromJson(String.valueOf(documents.next()), ProjectGroup.class);
            groups.add(group);
        }

        return groups;
    }
}
