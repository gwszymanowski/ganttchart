package ganttchart.gui.elements.dialog;

import ganttchart.model.Person;
import ganttchart.model.Project;
import ganttchart.repository.PersonRepository;
import ganttchart.service.PersonService;
import ganttchart.service.ProjectService;
import javafx.geometry.Insets;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;
import org.controlsfx.control.ListSelectionView;

import java.util.List;
import java.util.Set;

/**
 * Created by gwszymanowski on 2017-07-07.
 */
public class MembersDialog extends Dialog<ButtonType> implements Dialogable {

    private Project project;
    private PersonRepository repository = new PersonRepository();

    public MembersDialog(Project project) {
        this.project = project;
        setTitle("Edit members");
        setHeaderText(null);
        setGraphic(null);

        ButtonType loginButtonType = new ButtonType("Change");
        getDialogPane().getButtonTypes().addAll(loginButtonType);

        getDialogPane().setContent(new MembersGridPane());
    }

    @Override
    public void save() {

    }

    private class MembersGridPane extends GridPane {
        public MembersGridPane() {
            setHgap(10);
            setVgap(10);
            setPadding(new Insets(20, 150, 10, 10));

            ListSelectionView<String> view = new ListSelectionView<>();
            view.getSourceItems().addAll(PersonService.getMembersToString(project.getMembers()));

            List<Person> notAdded = PersonService.getNotAdded(project.getMembers(), repository.getAll());

            view.getTargetItems().addAll(PersonService.getMembersToString(notAdded));
            add(view, 1, 1);
        }
    }
}
