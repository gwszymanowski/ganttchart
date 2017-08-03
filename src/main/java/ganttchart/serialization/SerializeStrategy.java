package ganttchart.serialization;

import java.io.File;
import java.io.Serializable;

/**
 * Created by gwszymanowski on 2017-08-03.
 */
public interface SerializeStrategy<Serializable> {

    void to(Serializable object);
    Serializable from();
    Class getGenericClass();

}
