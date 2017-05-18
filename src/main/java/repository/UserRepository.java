package repository;

import com.mongodb.*;
import model.Project;
import model.User;
import util.MongoUtil;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by gwszymanowski on 2017-05-17.
 */
public class UserRepository extends AbstractMongoRepository<User>{

    protected UserRepository(Class<User> typeParameterClass) throws UnknownHostException {
        super(typeParameterClass);
        MongoClient mongo = new MongoClient( "localhost" , 27017 );
        DB db = mongo.getDB("ganttchart");
        col = db.getCollection("user");
    }

}
