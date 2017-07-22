package ganttchart.gui.elements.alert;

/**
 * Created by gwszymanowski on 2017-07-11.
 */
public enum ElementType {
    PROJECT("project"), PERSON("person"), ASSIGNMENT("assignment"), OTHER("element");

    private final String name;

    ElementType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
