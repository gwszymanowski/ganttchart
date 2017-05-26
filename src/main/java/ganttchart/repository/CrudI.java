package ganttchart.repository;

import java.util.List;

/**
 * Created by gwszymanowski on 2017-05-26.
 */
public interface CrudI<T> {

    void save(T entity);
    void delete(int id);
    T findById(int id);
    List<T> getAll();

}
