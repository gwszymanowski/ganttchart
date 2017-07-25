package ganttchart.gui.elements.dialog;

import ganttchart.model.Person;
import ganttchart.model.Project;
import ganttchart.repository.PersonRepository;
import ganttchart.repository.ProjectRepository;
import ganttchart.service.PersonService;
import ganttchart.service.ProjectService;
import javafx.collections.ObservableList;
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
    private PersonRepository personRepository;
    private ProjectRepository projectRepository;
    private MembersGridPane gridpane;

    public MembersDialog(Project project) {
        this.project = project;
        this.personRepository = new PersonRepository();
        this.projectRepository = new ProjectRepository();
        this.gridpane = new MembersGridPane();
        setTitle("Edit members");

        ButtonType loginButtonType = new ButtonType("Change", ButtonBar.ButtonData.APPLY);
        getDialogPane().getButtonTypes().addAll(loginButtonType);

        getDialogPane().setContent(gridpane);
    }

    @Override
    public void save() {
        Set<Person> added = PersonService.getMembers(gridpane.getSourceItems());
        project.setMembers(added);
        projectRepository.update(project);
    }

    private class MembersGridPane extends GridPane {

        private ListSelectionView<String> view;

        public MembersGridPane() {
            setHgap(10);
            setVgap(10);
            setPadding(new Insets(20, 150, 10, 10));

            this.view = new ListSelectionView<>();
            this.view.getSourceItems().addAll(PersonService.getMembersToString(project.getMembers()));

            Set<Person> notAdded = PersonService.getNotAdded(project.getMembers(), personRepository.getAll());

            this.view.getTargetItems().addAll(PersonService.getMembersToString(notAdded));
            add(view, 1, 1);
        }

        public ObservableList<String> getSourceItems() {
            return view.getSourceItems();
        }

    }
}
