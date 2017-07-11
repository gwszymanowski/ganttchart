package ganttchart.util;

/**
 * Created by gwszymanowski on 2017-07-11.
 */
public enum AlertElementType {
    PROJECT("project"), PERSON("person"), ASSIGNMENT("assignment"), OTHER("element");

    private final String name;

    AlertElementType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
