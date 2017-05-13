import gui.gui.general.LoginView;
import gui.gui.general.PrimaryView;
import gui.gui.general.SignupView;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;


/**
 * Created by gwszymanowski on 2017-05-02.
 */
public class Main extends Application {

    Scene scene1;

    @Override
    public void start(Stage primaryStage) throws Exception {

        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();

        primaryStage.setX(primaryScreenBounds.getMinX());
        primaryStage.setY(primaryScreenBounds.getMinY());
        primaryStage.setWidth(primaryScreenBounds.getWidth());
        primaryStage.setHeight(primaryScreenBounds.getHeight());

        PrimaryView frame = new PrimaryView();

        scene1 = new Scene(frame);

        primaryStage.setScene(scene1);
        primaryStage.setTitle("Gantt chart");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
