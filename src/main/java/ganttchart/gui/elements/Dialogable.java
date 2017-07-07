package ganttchart.gui.elements;

import javafx.util.Pair;

import java.util.Optional;

/**
 * Created by gwszymanowski on 2017-07-07.
 */
public interface Dialogable {
    Optional<Pair<String, String>> showAndWait();
}
