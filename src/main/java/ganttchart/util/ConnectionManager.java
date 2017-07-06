package ganttchart.util;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

/**
 * Created by gwszymanowski on 2017-07-06.
 */
public class ConnectionManager {

    private static MongoDatabase database = null;

    private ConnectionManager() {
    }

    public static MongoDatabase getDatabase() {
        if(database == null) {
            MongoClientURI uri = new MongoClientURI(
                    "mongodb://writer:writer@ganttchart-shard-00-00-ib0bs.mongodb.net:27017,ganttchart-shard-00-01-ib0bs.mongodb.net:27017,ganttchart-shard-00-02-ib0bs.mongodb.net:27017/ganttchart?ssl=true&replicaSet=ganttchart-shard-0&authSource=admin");

            MongoClient mongoClient = new MongoClient(uri);
            database = mongoClient.getDatabase("ganttchart");
        }

        return database;
    }

}
