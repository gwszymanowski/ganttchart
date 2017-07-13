package ganttchart.util;

import javafx.scene.control.Alert;

/**
 * Created by gwszymanowski on 2017-07-11.
 */
public class AlertFactory {

    private AlertFactory() {}

    public static Alert getErrorAlert(AlertReason reason) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText(null);
        alert.setHeaderText(reason.toString());
        return alert;
    }

    public static Alert getInformationAlert(ElementType elementType, OperationType operationType) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setContentText(null);
        alert.setHeaderText(FileUtil.concatenateString("The ", elementType.toString(), operationType.toString()));
        return alert;
    }

    public static Alert getWarningAlert(ElementType elementType) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setContentText(null);
        alert.setHeaderText(FileUtil.concatenateString("Are you sure you want to delete this ", elementType.toString(), "?"));
        return alert;
    }
}
