package ganttchart.repository;

/**
 * Created by gwszymanowski on 2017-08-03.
 */
public interface Repositorable<Serializable> {
   void save(Serializable object);
}
