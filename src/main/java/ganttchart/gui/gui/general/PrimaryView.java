package ganttchart.gui.gui.general;

import ganttchart.gui.CreateProjectView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;

/**
 * Created by gwszymanowski on 2017-05-13.
 */
public class PrimaryView extends BorderPane {

    public PrimaryView() {
        setLeft();
        setRight();
    }

    private void setLeft() {
        TreeItem<String> existingProjects = new TreeItem<>("Existing projects");
        existingProjects.setExpanded(true);
        for (int i = 1; i < 6; i++) {
            TreeItem<String> item = new TreeItem<String> ("Project #" + i);
            existingProjects.getChildren().add(item);
        }

        TreeView<String> root = new TreeView<>(existingProjects);

        setLeft(root);
    }

    private void setRight() {
        CreateProjectView lv = new CreateProjectView();
        setCenter(lv);
    }

}
