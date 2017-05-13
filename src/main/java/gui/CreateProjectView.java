package gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * Created by gwszymanowski on 2017-05-13.
 */
public class CreateProjectView extends GridPane {

    public CreateProjectView() {
        initializeGrid();
        initializeBody();
    }

    private void initializeGrid() {
        setAlignment(Pos.CENTER);
        setHgap(10);
        setVgap(10);
        setPadding(new Insets(25, 25, 25, 25));
    }

    private void initializeBody() {

        Text scenetitle = new Text("Create Project");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        add(scenetitle, 0, 0, 2, 1);

        Label userName = new Label("User Name:");
        add(userName, 0, 1);

        TextField userTextField = new TextField();
        add(userTextField, 1, 1);

        Label pw = new Label("Password:");
        add(pw, 0, 2);

        PasswordField pwBox = new PasswordField();
        add(pwBox, 1, 2);

        Button signup = new Button("Sign up");
        Button login = new Button("Sign in");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().addAll(signup, login);
        add(hbBtn, 1, 4);

    }
}
