package ganttchart.gui.elements.dialog;

import ganttchart.model.Project;
import ganttchart.serialization.SerializeStrategy;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

/**
 * Created by gwszymanowski on 2017-08-03.
 */
public class MarshalizeAssignmentDialog extends Dialog<ButtonType>  {

    private Project project;
    private SerializeStrategy<Project> strategy;

    public MarshalizeAssignmentDialog(Project project) {
        this.project = project;
        setTitle("Pick format");

        ButtonType editButtonType = new ButtonType("JSON", ButtonBar.ButtonData.BIG_GAP);
        ButtonType deleteButtonType = new ButtonType("XML", ButtonBar.ButtonData.SMALL_GAP);
        ButtonType cancelButtonType = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        getDialogPane().getButtonTypes().setAll(editButtonType, deleteButtonType, cancelButtonType);
    }


    public void marshalize(SerializeStrategy strategy) {
        strategy.marshal(project);
    }

}
