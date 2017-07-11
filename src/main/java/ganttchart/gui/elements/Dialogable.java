package ganttchart.gui.elements;

import javafx.scene.control.ButtonType;
import javafx.util.Pair;

import java.util.Optional;

/**
 * Created by gwszymanowski on 2017-07-07.
 */
public interface Dialogable {
    Optional<ButtonType> showAndWait();
    void save();
}
