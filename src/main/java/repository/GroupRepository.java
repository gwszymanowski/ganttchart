package repository;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import model.Project;
import model.ProjectGroup;

import java.net.UnknownHostException;
import java.util.List;

/**
 * Created by gwszymanowski on 2017-05-17.
 */
public class GroupRepository extends AbstractMongoRepository<ProjectGroup>{

    protected GroupRepository(Class<ProjectGroup> typeParameterClass) throws UnknownHostException {
        super(typeParameterClass);
        MongoClient mongo = new MongoClient( "localhost" , 27017 );
        DB db = mongo.getDB("ganttchart");
        col = db.getCollection("project");
    }


}
