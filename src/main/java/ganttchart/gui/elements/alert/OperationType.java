package ganttchart.gui.elements.alert;

/**
 * Created by gwszymanowski on 2017-07-13.
 */
public enum OperationType {
    SAVE(" has been created!"), DELETE(" has been deleted!");

    private String text;

    OperationType(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
