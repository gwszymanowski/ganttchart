package ganttchart.gui.elements.alert;

import ganttchart.model.Person;
import ganttchart.model.Project;

/**
 * Created by gwszymanowski on 2017-07-11.
 */
public enum ElementType {
    PROJECT("project"), PERSON("person"), ASSIGNMENT("assignment"), PROGRESS("progress"), OTHER("element");

    private final String name;

    ElementType(String name) {
        this.name = name;
    }

    public final static ElementType get(Class type) {
        if(type == Person.class)
            return ElementType.PERSON;
        else if(type == Project.class)
            return ElementType.PROJECT;
        else return ElementType.OTHER;
    }

    @Override
    public String toString() {
        return name;
    }
}
