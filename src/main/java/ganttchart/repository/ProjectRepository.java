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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

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
        collection.insertOne(entity.toDocument());
    }

    @Override
    public void delete(Project entity) {
        Document doc = new Document();
        collection.deleteOne(doc);
    }

//    @Override
//    public Project findById(int id) {
//        Document doc = collection.find(eq("id", id)).first();
//
//        Project p = new Project();
//        Integer project_id = (Integer) doc.get("id");
//        p.setId(project_id);
//
//        Integer leader_id = (Integer) doc.get("leader_id");
//        User leader = new User();
//        leader.setId(leader_id);
//        p.setLeader(leader);
//
//        Integer group_id = (Integer) doc.get("group_id");
//        ProjectGroup group = new ProjectGroup();
//        group.setId(group_id);
//        p.setGroup(group);
//
//        String startDateString = (String) doc.get("startDate");
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
//        LocalDateTime dateTime = LocalDateTime.parse(startDateString, formatter);
//        p.setStartDate(dateTime);
//
//        return p;
//    }

    @Override
    public List<Project> getAll() {
        List<Project> projects = new LinkedList<>();

        Iterator<Document> documents = collection.find().iterator();

        Gson gson = new Gson();

        while(documents.hasNext()) {
            Project project = gson.fromJson(String.valueOf(documents.next()), Project.class);
            projects.add(project);
        }

        return projects;
    }
}
