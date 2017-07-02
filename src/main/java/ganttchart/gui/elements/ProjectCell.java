package ganttchart.gui.elements;

import ganttchart.repository.ProjectGroupRepository;
import ganttchart.repository.UserRepository;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TreeCell;
import javafx.scene.layout.HBox;

/**
 * Created by gwszymanowski on 2017-07-02.
 */
public class ProjectCell extends TreeCell<String> {

    final private UserRepository userRepository = new UserRepository();

    public ProjectCell() {
        super();
    }

    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        if (empty) {
            setGraphic(null);
            setText(null);
        } else if(getTreeItem().getParent() != null && getTreeItem().getParent().getValue().equals("Projects")){
            HBox cellBox = new HBox(10);
            Label label = new Label(item);
            Button editButton = new Button("Edit");
            //editButton.setOnAction(new GroupCell.ClickManager());
            label.prefHeightProperty().bind(editButton.heightProperty());
            cellBox.getChildren().addAll(editButton, label );
            setGraphic(cellBox);
        } else {
            setGraphic(null);
            setText(item);
        }

    }

}
