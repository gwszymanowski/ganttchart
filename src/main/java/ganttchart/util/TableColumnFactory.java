package ganttchart.util;

import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.Label;

/**
 * Created by gwszymanowski on 2017-07-06.
 */
public final class TableColumnFactory {

    public Group getRotated(String title) {
        Label label = new Label(title);
        label.setRotate(270);
        label.setPadding(new Insets(5,5,5,5));
        label.setMinWidth(100);
        Group group = new Group(label);
        group.setAccessibleHelp(title);
        return group;
    }

}
