package ganttchart.controller;

import ganttchart.model.Project;
import ganttchart.repository.ProjectRepository;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by gwszymanowski on 2017-06-30.
 */
public class ProjectController implements Initializable {

    private final String title;
    private ProjectRepository projectRepository = new ProjectRepository();

    public ProjectController(String title) {
        this.title = title;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Inicjalizuje sobie");
        Project p = projectRepository.findByName(title);
    }
}
