package ganttchart.gui.elements;

import ganttchart.gui.elements.alert.AlertFactory;
import ganttchart.gui.elements.alert.AlertReason;
import ganttchart.gui.elements.alert.ElementType;
import ganttchart.gui.elements.alert.OperationType;
import ganttchart.gui.elements.dialog.Dialogable;
import ganttchart.gui.elements.dialog.PersonDialog;
import ganttchart.gui.elements.dialog.ProjectDialog;
import ganttchart.model.Person;
import ganttchart.model.Project;
import ganttchart.repository.Repositorable;
import ganttchart.repository.RepositoryBusinessService;
import ganttchart.serialization.JSONSerializator;
import ganttchart.serialization.SerializeStrategy;
import ganttchart.serialization.XMLSerializator;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.Serializable;
import java.util.Optional;

/**
 * Created by gwszymanowski on 2017-07-09.
 */
public class GanttMenu extends MenuBar {

    public GanttMenu() {
        getMenus().addAll(getCreateMenu(), getViewMenu(), getImportMenu());
    }

    private Menu getCreateMenu() {
        Menu createMenu = new Menu("Create");
        MenuItem projectItem = new MenuItem("project");
        projectItem.setOnAction(new ShowDialogEvent(new ProjectDialog()));
        MenuItem personItem = new MenuItem("person");
        personItem.setOnAction(new ShowDialogEvent(new PersonDialog()));
        createMenu.getItems().addAll(projectItem, personItem);
        return createMenu;
    }

    private Menu getViewMenu() {
        Menu viewMenu = new Menu("View");
        MenuItem projectListItem = new MenuItem("projects");
        projectListItem.setOnAction(new SwitchViewEvent("/project.fxml"));
        MenuItem personListItem = new MenuItem("people");
        personListItem.setOnAction(new SwitchViewEvent("/person.fxml"));
        viewMenu.getItems().addAll(projectListItem, personListItem);
        return viewMenu;
    }

    private Menu getImportMenu() {
        Menu importMenu = new Menu("Import");
        Menu projectImport = new Menu("project");

        MenuItem projectXML = new MenuItem("XML");
        MenuItem projectJSON = new MenuItem("JSON");
        projectJSON.setOnAction(new DeserializationEvent(new JSONSerializator(Project.class)));
        projectImport.getItems().addAll(projectXML, projectJSON);

        Menu personImport = new Menu("person");

        MenuItem personXML = new MenuItem("XML");
        personXML.setOnAction(new DeserializationEvent(new XMLSerializator(Person.class)));
        MenuItem personJSON = new MenuItem("JSON");
        personJSON.setOnAction(new DeserializationEvent(new JSONSerializator(Person.class)));
        personImport.getItems().addAll(personXML, personJSON);

        importMenu.getItems().addAll(projectImport, personImport);
        return importMenu;
    }

    private class ShowDialogEvent implements EventHandler<ActionEvent> {

        private final Dialogable dialog;

        public ShowDialogEvent(Dialogable dialog) {
            this.dialog = dialog;
        }

        @Override
        public void handle(ActionEvent event) {
            Optional<ButtonType> result = dialog.showAndWait();

            if(result.isPresent() && Optional.of(result.get()).get().getButtonData() == ButtonBar.ButtonData.APPLY)
                dialog.save();
        }
    }

    private class SwitchViewEvent implements EventHandler<ActionEvent> {

        private final String path;

        public SwitchViewEvent(String path) {
            this.path = path;
        }

        @Override
        public void handle(ActionEvent event) {
            Stage stage = null;
            Parent root = null;
            try {
                stage = (Stage) getScene().getWindow();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(path));
                root = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    private class DeserializationEvent<Serializable> implements EventHandler<ActionEvent> {

        private SerializeStrategy<Serializable> strategy;

        public DeserializationEvent(SerializeStrategy strategy) {
            this.strategy = strategy;
        }

        @Override
        public void handle(ActionEvent event) {

            Serializable object = strategy.unmarshal();

            if(object != null) {
                RepositoryBusinessService service = new RepositoryBusinessService(object.getClass());
                Repositorable repo = service.getRepository();
                repo.save(object);

                Alert alert = AlertFactory.getInformationAlert(ElementType.get(object.getClass()), OperationType.IMPORTED);
                alert.showAndWait();
            } else {
                Alert error = AlertFactory.getErrorAlert(AlertReason.FILE_WRONG);
                error.showAndWait();
            }
        }
    }

}

