package repository;

import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import model.Project;
import util.MongoUtil;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by gwszymanowski on 2017-05-19.
 */
public abstract class AbstractMongoRepository<T>{

    protected DBCollection col = null;
    private final Class<T> typeParameterClass;

    protected AbstractMongoRepository(Class<T> typeParameterClass) {
        this.typeParameterClass = typeParameterClass;
    }

    public void save(T entity) {
        try {
            DBObject ent = MongoUtil.getDbObject(entity);
            col.save(ent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void delete(T entity) {
        try {
            DBObject ent = MongoUtil.getDbObject(entity);
            col.remove(ent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<T> getAll() {
        List<T> entities = new LinkedList<>();

        DBCursor cursor = col.find();
        while(cursor.hasNext()) {
            try {
                T project = MongoUtil.getPojo(cursor.next(), typeParameterClass);
                entities.add(project);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return entities;
    }
}
