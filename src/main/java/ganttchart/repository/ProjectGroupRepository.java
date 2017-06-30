package ganttchart.repository;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import ganttchart.model.ProjectGroup;
import ganttchart.model.User;
import org.bson.Document;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

/**
 * Created by gwszymanowski on 2017-05-17.
 */
public class ProjectGroupRepository {

    private MongoCollection<Document> collection = null;

    public ProjectGroupRepository() {
        MongoClientURI uri = new MongoClientURI(
                "mongodb://writer:writer@ganttchart-shard-00-00-ib0bs.mongodb.net:27017,ganttchart-shard-00-01-ib0bs.mongodb.net:27017,ganttchart-shard-00-02-ib0bs.mongodb.net:27017/ganttchart?ssl=true&replicaSet=ganttchart-shard-0&authSource=admin");

        MongoClient mongoClient = new MongoClient(uri);
        MongoDatabase database = mongoClient.getDatabase("ganttchart");
        collection = database.getCollection("projectGroup");
    }

    public void save(ProjectGroup entity) {
        collection.insertOne(entity.toDocument());
    }

    public void delete(String name) {
        Document doc = collection.find(eq("name", name)).first();
        collection.deleteOne(doc);
    }

    public List<ProjectGroup> getAll() {
        List<ProjectGroup> groups = new LinkedList<>();
        Iterator<Document> documents = collection.find().iterator();

        while(documents.hasNext()) {
            Document next = documents.next();
            groups.add(ProjectGroup.fromDocument(next));
        }

        return groups;
    }

    public ProjectGroup findByName(String name) {
        Document doc = collection.find(eq("name", name)).first();
        return ProjectGroup.fromDocument(doc);
    }

    public boolean isEmpty(String name) {
        BasicDBObject query = new BasicDBObject();
        query.put("name", name);
        FindIterable<Document> it = collection.find(query).limit(1);

        return it.first() == null ? true : false;
    }

    public String[] getNotAddedToGroupToString(List<User> members, List<User> all) {
        String[] usersArray = new String[(all.size() - members.size())];
        int i = 0;
        for(User user : all)
            if(!members.contains(user)) {
                usersArray[i] = user.toString();
                i++;
            }

        return usersArray;
    }
}
