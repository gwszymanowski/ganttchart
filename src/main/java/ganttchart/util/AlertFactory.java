package ganttchart.util;

import javafx.scene.control.Alert;

/**
 * Created by gwszymanowski on 2017-07-11.
 */
public class AlertFactory {

    private AlertFactory() {}

    public static Alert getErrorAlert(AlertReason reason) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Dialog");
        alert.setContentText(null);
        alert.setHeaderText(reason.toString());
        return alert;
    }

    public static Alert getSaveConfirmAlert(AlertElementType elementType) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setContentText(null);
        alert.setHeaderText(FileUtil.concatenateString("New ", elementType.toString(), " has been created!"));
        return alert;
    }

    public static Alert getWarningAlert(AlertElementType elementType) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning Dialog");
        alert.setContentText(null);
        alert.setHeaderText(FileUtil.concatenateString("Are you sure you want to delete this ", elementType.toString(), "?"));
        return alert;
    }



}
