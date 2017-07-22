package ganttchart.gui.elements.cell;

import ganttchart.model.Assignment;
import ganttchart.model.Person;
import ganttchart.util.FileUtil;
import javafx.event.EventHandler;
import javafx.scene.control.TableCell;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.util.Pair;

import java.time.LocalDate;

/**
 * Created by gwszymanowski on 2017-07-22.
 */
public class AssignmentCell extends TableCell<Pair<String, Object>, Object> {

    @Override
    protected void updateItem(Object item, boolean empty) {
        super.updateItem(item, empty);

        if(!empty) {

            if(item instanceof String) {
                setText((String)item);
            } else if(item instanceof Person) {
                setText(item.toString());
            }else if(item instanceof LocalDate) {
                setText(FileUtil.convertDateToString((LocalDate)item));
            } else {
                setText(String.valueOf(item));
            }



           // setOnMouseClicked(new PopupEvent(item));
        }

    }

    private class PopupEvent implements EventHandler<MouseEvent> {

        private String item;

        public PopupEvent(String item) {
            this.item = item;
        }

        @Override
        public void handle(MouseEvent event) {
            if (event.getButton().equals(MouseButton.SECONDARY)) {
                System.out.println(item);
            }
        }
    }
}
