package ganttchart;

import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;


/**
 * Created by gwszymanowski on 2017-05-02.
 */
@Component
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
//        final AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(MainConfig.class);
//        FXMLLoader loader = ctx.getBean(FXMLLoaderProvider.class).getLoader("/main.fxml");
//        Parent root = loader.load();
//
//
//        primaryStage.setScene(new Scene(root, 400, 400));
//        primaryStage.setTitle("Gantt chart");
//        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
