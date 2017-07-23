package ganttchart.gui.elements.cell;

import ganttchart.gui.elements.alert.AlertFactory;
import ganttchart.gui.elements.alert.AssignmentChoiceAlert;
import ganttchart.gui.elements.alert.ElementType;
import ganttchart.gui.elements.alert.OperationType;
import ganttchart.gui.elements.dialog.AssignmentDialog;
import ganttchart.model.Assignment;
import ganttchart.model.Person;
import ganttchart.model.Project;
import ganttchart.service.AssignmentService;
import ganttchart.util.FileUtil;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.Pair;
import org.apache.tomcat.jni.Local;
import org.controlsfx.control.PopOver;

import java.time.LocalDate;
import java.util.Optional;

/**
 * Created by gwszymanowski on 2017-07-22.
 */
public class AssignmentCell extends TableCell<Pair<String, Object>, Object> {

    private Project project;
    private String item;

    public AssignmentCell(Project project) {
        this.project = project;
    }

    @Override
    protected void updateItem(Object item, boolean empty) {
        super.updateItem(item, empty);

        if(!empty) {

            if(item instanceof Person)
                setText(item.toString());
            else if(item instanceof LocalDate)
                setText(FileUtil.convertDateToString((LocalDate)item));
            else {
                setText(String.valueOf(item));
            }


            this.item = String.valueOf(item);
            setOnMouseClicked(new PopupEvent());
        }
    }

    private class PopupEvent implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent event) {
            if (event.getButton().equals(MouseButton.SECONDARY)) {

                AssignmentChoiceAlert alert = new AssignmentChoiceAlert(Alert.AlertType.CONFIRMATION);
                Optional<ButtonType> result = alert.showAndWait();

                if (result.isPresent()) {

                    ButtonBar.ButtonData data = Optional.of(result.get()).get().getButtonData();

                    if (data == ButtonBar.ButtonData.NO)
                        updateAction();
                    else if (data == ButtonBar.ButtonData.HELP_2)
                        alert.delete(project, item);
                    else if (data == ButtonBar.ButtonData.LEFT)
                        alert.saveProgress(project, item);
                }
            }
        }

        private void updateAction() {
            Assignment currentTask = AssignmentService.findAssignmentByTitle(project, item);

            AssignmentDialog editDialog = new AssignmentDialog(project);
            editDialog.fillFields(currentTask.getTitle(),currentTask.getStartDate(), currentTask.getFinishDate());

            Optional<ButtonType> assignResult = editDialog.showAndWait();
            if(assignResult.isPresent() && Optional.of(assignResult.get()).get().getButtonData() == ButtonBar.ButtonData.APPLY) {
                editDialog.update(project, item);
                AlertFactory.getInformationAlert(ElementType.OTHER, OperationType.SAVE).showAndWait();
            }

        }
    }
}
