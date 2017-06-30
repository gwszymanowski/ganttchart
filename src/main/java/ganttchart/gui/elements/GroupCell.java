package ganttchart.gui.elements;

import ganttchart.repository.UserRepository;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.util.Pair;
import org.controlsfx.control.ListSelectionView;

/**
 * Created by gwszymanowski on 2017-06-28.
 */
public class GroupCell extends TreeCell<String> {

    private UserRepository userRepository = new UserRepository();

    public GroupCell() {
        super();
    }

    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        if (empty) {
            setGraphic(null);
            setText(null);
        } else if(getTreeItem().getParent() != null && getTreeItem().getParent().getValue().equals("Groups")){
            HBox cellBox = new HBox(10);
            Label label = new Label(item);
            Button editButton = new Button("Edit");
            editButton.setOnAction(new ClickManager());
            label.prefHeightProperty().bind(editButton.heightProperty());
            cellBox.getChildren().addAll(editButton, label );
            setGraphic(cellBox);
        } else {
            setGraphic(null);
            setText(item);
        }

    }

    private class ClickManager implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent event) {
            Dialog<Pair<String, String>> dialog = new Dialog<>();
            dialog.setTitle("Edit project group");
            dialog.setHeaderText(null);
            dialog.setGraphic(null);

            ButtonType loginButtonType = new ButtonType("Change", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(loginButtonType);

            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(20, 150, 10, 10));

            TextField groupname = new TextField();
            groupname.setText(getTreeItem().getValue());
            grid.add(new Label("Group name:"), 0, 0);
            grid.add(groupname, 1, 0);

            ListSelectionView<String> view = new ListSelectionView<>();
            view.getSourceItems().addAll("One", "Two", "Three");
            view.getTargetItems().addAll("Four", "Five");
            grid.add(view, 1, 1);

            dialog.getDialogPane().setContent(grid);

            dialog.showAndWait();


        }
    }

}
