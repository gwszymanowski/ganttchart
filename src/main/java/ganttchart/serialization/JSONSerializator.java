package ganttchart.serialization;

import java.io.File;

/**
 * Created by gwszymanowski on 2017-08-03.
 */
public final class JSONSerializator<T> implements SerializeStrategy<T> {

    @Override
    public void to(T object) {
    }

    @Override
    public T from(File file) {
        return null;
    }
}