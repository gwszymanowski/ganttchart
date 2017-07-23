package ganttchart.gui.elements.cell;

import ganttchart.gui.elements.alert.AlertFactory;
import ganttchart.gui.elements.alert.AssignmentChoiceAlert;
import ganttchart.gui.elements.alert.ElementType;
import ganttchart.gui.elements.alert.OperationType;
import ganttchart.model.Person;
import ganttchart.util.FileUtil;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.Pair;
import org.controlsfx.control.PopOver;

import java.time.LocalDate;
import java.util.Optional;

/**
 * Created by gwszymanowski on 2017-07-22.
 */
public class AssignmentCell extends TableCell<Pair<String, Object>, Object> {

    @Override
    protected void updateItem(Object item, boolean empty) {
        super.updateItem(item, empty);

        if(!empty) {
            if(item instanceof String)
                setText((String)item);
            else if(item instanceof Person)
                setText(item.toString());
            else if(item instanceof LocalDate)
                setText(FileUtil.convertDateToString((LocalDate)item));
            else
                setText(String.valueOf(item));
            setOnMouseClicked(new PopupEvent());
        }
    }

    private class PopupEvent implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent event) {
            if (event.getButton().equals(MouseButton.SECONDARY)) {
                AssignmentChoiceAlert alert = new AssignmentChoiceAlert(Alert.AlertType.CONFIRMATION);
                Optional<ButtonType> result = alert.showAndWait();
                if(result.isPresent()) {

                    ButtonBar.ButtonData data = Optional.of(result.get()).get().getButtonData();

                    if(data == ButtonBar.ButtonData.NO) {
                        System.out.println("EDYTUJEMY");
                    } else if(data == ButtonBar.ButtonData.HELP_2) {
                        System.out.println("USUWAMY");
                    } else if (data == ButtonBar.ButtonData.LEFT) {
                        System.out.println("ZAPISUJEMY PROGRESS");
                    }

                }
            }
        }
    }
}
