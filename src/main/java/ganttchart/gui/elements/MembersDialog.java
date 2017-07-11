package ganttchart.gui.elements;

import javafx.geometry.Insets;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;
import org.controlsfx.control.ListSelectionView;

/**
 * Created by gwszymanowski on 2017-07-07.
 */
public class MembersDialog extends Dialog<ButtonType> implements Dialogable {

    public MembersDialog() {
        setTitle("Edit members");
        setHeaderText(null);
        setGraphic(null);

        ButtonType loginButtonType = new ButtonType("Change", ButtonBar.ButtonData.OK_DONE);
        getDialogPane().getButtonTypes().addAll(loginButtonType);

        getDialogPane().setContent(new MembersGridPane());
    }

    @Override
    public void save() {

    }

    private class MembersGridPane extends GridPane {
        public MembersGridPane() {
            setHgap(10);
            setVgap(10);
            setPadding(new Insets(20, 150, 10, 10));

            ListSelectionView<String> view = new ListSelectionView<>();
            view.getSourceItems().addAll("m1", "m2", "m3");
            view.getTargetItems().addAll("m4", "m5");
            add(view, 1, 1);
        }
    }
}
