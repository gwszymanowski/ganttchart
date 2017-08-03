package ganttchart.serialization;

import java.io.File;

/**
 * Created by gwszymanowski on 2017-08-03.
 */
public interface SerializeStrategy<T> {

    void to(T object);
    T from();

}
