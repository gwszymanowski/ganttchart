package gui.gui.general;

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
public class SignupView extends GridPane{

    public SignupView() {
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

        int row = 0;

        Text scenetitle = new Text("Create account");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        add(scenetitle, 0, row, 2, 1);

        row++;

        Label firstname = new Label("Firstname:");
        add(firstname, 0, row);

        TextField firstnameField = new TextField();
        add(firstnameField, 1, row);

        row++;

        Label lastname = new Label("Lastname:");
        add(lastname, 0, row);

        TextField lastnameField = new TextField();
        add(lastnameField, 1, row);

        row++;

        Label username = new Label("Username:");
        add(username, 0, row);

        TextField userTextField = new TextField();
        add(userTextField, 1, row);

        row++;

        Label pw = new Label("Password:");
        add(pw, 0, row);

        PasswordField pwBox = new PasswordField();
        add(pwBox, 1, row);

        row++;

        Button create = new Button("Create");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().addAll(create);
        add(hbBtn, 1, row);

    }
}
