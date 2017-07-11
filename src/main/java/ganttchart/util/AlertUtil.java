package ganttchart.util;

import javafx.scene.control.Alert;

/**
 * Created by gwszymanowski on 2017-07-11.
 */
public class AlertUtil {

    private AlertUtil() {}

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
        String text = "New " + elementType + " has been created!";
        alert.setHeaderText(text);
        return alert;
    }

}
