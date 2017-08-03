package ganttchart.gui.elements.cell;

import ganttchart.gui.elements.dialog.PersonDialog;
import ganttchart.model.Person;
import ganttchart.repository.PersonRepository;
import ganttchart.gui.elements.alert.ElementType;
import ganttchart.gui.elements.alert.AlertFactory;
import ganttchart.gui.elements.alert.OperationType;
import ganttchart.serialization.JSONSerializator;
import ganttchart.serialization.SerializeStrategy;
import ganttchart.serialization.XMLSerializator;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

import java.io.File;
import java.util.Optional;

/**
 * Created by gwszymanowski on 2017-07-13.
 */
public class PersonCell extends TableCell<Person, String> {

    private String[] rowContent;
    private PersonRepository repo;

    public PersonCell() {
        this.repo = new PersonRepository();
    }

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
        Button xmlButton = new Button("XML");
        xmlButton.setOnAction(new SerializationEvent(new XMLSerializator()));
        Button jsonButton = new Button("JSON");
        jsonButton.setOnAction(new SerializationEvent(new JSONSerializator()));
        cellBox.getChildren().addAll(editButton, deleteButton, xmlButton, jsonButton);
        setGraphic(cellBox);
    }

    private class EditPersonEvent implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            PersonDialog personDialog = new PersonDialog();
            Person oldPerson = new Person(rowContent[0], rowContent[1]);

            personDialog.fillFields(rowContent[0], rowContent[1]);

            Optional<ButtonType> result = personDialog.showAndWait();
            if(result.isPresent() && Optional.of(result.get()).get().getButtonData() == ButtonBar.ButtonData.OTHER) {
                personDialog.update(oldPerson);
                AlertFactory.getInformationAlert(ElementType.PERSON, OperationType.CHANGED).showAndWait();
            }
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

    private class SerializationEvent implements EventHandler<ActionEvent> {

        private SerializeStrategy<Person> strategy;

        public SerializationEvent(SerializeStrategy strategy) {
            this.strategy = strategy;
        }

        @Override
        public void handle(ActionEvent event) {
            Person toBeSerialized = new Person(rowContent[0], rowContent[1]);
            strategy.to(toBeSerialized);
        }
    }

}