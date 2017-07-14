package ganttchart.gui.elements.cell;

import ganttchart.gui.elements.dialog.PersonDialog;
import ganttchart.model.Person;
import ganttchart.repository.PersonRepository;
import ganttchart.util.ElementType;
import ganttchart.util.AlertFactory;
import ganttchart.util.OperationType;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

import java.util.Optional;

/**
 * Created by gwszymanowski on 2017-07-13.
 */
public class PersonCell extends TableCell<Person, String> {

    private String[] rowContent;
    private PersonRepository repo = new PersonRepository();

    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        Object rowItem = getTableRow().getItem();
        if(rowItem != null) {
            rowContent = rowItem.toString().split(" ");
            initializeComponents();
        }
    }

    private void initializeComponents() {
        HBox cellBox = new HBox(10);
        Button editButton = new Button("Edit");
        editButton.setOnAction(new EditPersonEvent());
        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(new DeletePersonEvent());
        cellBox.getChildren().addAll(editButton, deleteButton);
        setGraphic(cellBox);
    }

    private class EditPersonEvent implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            PersonDialog cp = new PersonDialog();
            cp.fillFields(rowContent[0], rowContent[1]);
            cp.showAndWait();
        }
    }

    private class DeletePersonEvent implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            Alert alert = AlertFactory.getWarningAlert(ElementType.PERSON);

            ButtonType deleteButtonType = new ButtonType("Delete", ButtonBar.ButtonData.APPLY);
            alert.getButtonTypes().setAll(deleteButtonType);

            Optional<ButtonType> result = alert.showAndWait();
            if(result.isPresent() && Optional.of(result.get()).get().getButtonData() == ButtonBar.ButtonData.APPLY) {
                repo.delete(rowContent[0], rowContent[1]);
                AlertFactory.getInformationAlert(ElementType.PERSON, OperationType.DELETE).showAndWait();
            }

        }
    }

}