package ganttchart.util;

import ganttchart.repository.CRUD;
import javafx.scene.control.Alert;

/**
 * Created by gwszymanowski on 2017-07-13.
 */
public class DeleteAlert extends Alert {

    private CRUD repository;

    public DeleteAlert(AlertType alertType) {
        super(alertType);
    }

    public DeleteAlert(AlertType alertType, CRUD repositoryImpl) {
        this(alertType);
        this.repository = repositoryImpl;
    }
}
