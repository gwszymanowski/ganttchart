package ganttchart.gui.elements.alert;

/**
 * Created by gwszymanowski on 2017-07-11.
 */
public enum AlertReason {
    ZERO_LENGTH("Field cannot be empty!"), ALREADY_EXISTS("Such element already exists!"),
    FILE_WRONG("Something is wrong with the file!");

    final String text;

    AlertReason(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }


}
