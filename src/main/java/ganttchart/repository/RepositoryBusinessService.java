package ganttchart.repository;

import ganttchart.model.Person;
import ganttchart.model.Project;

import java.io.Serializable;

/**
 * Created by gwszymanowski on 2017-08-03.
 */
public class RepositoryBusinessService {

    private Class type;

    public RepositoryBusinessService(Class type) {
        this.type = type;
    }

    public Repositorable<Serializable> getRepository() {
        if(type == Person.class)
            return new PersonRepository();
        else if(type == Project.class)
            return new ProjectRepository();
        else
            throw new IllegalArgumentException("Only 'Person' and 'Project' classes are allowed");
    }
}
