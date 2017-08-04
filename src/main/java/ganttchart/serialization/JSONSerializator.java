package ganttchart.serialization;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.reflect.TypeToken;
import ganttchart.gui.elements.alert.AlertFactory;
import ganttchart.gui.elements.alert.AlertReason;
import ganttchart.gui.elements.alert.ElementType;
import ganttchart.gui.elements.alert.OperationType;
import javafx.scene.control.Alert;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by gwszymanowski on 2017-08-03.
 */
public final class JSONSerializator<T> implements SerializeStrategy<T> {

    private Class<T> persistentClass;

    public JSONSerializator(Class persistentClass) {
        this.persistentClass = persistentClass;
    }

    @Override
    public void marshal(T object) {

        DirectoryChooser chooser = new DirectoryChooser();
        chooser.setTitle("Open Resource File");

        File file = chooser.showDialog(null);
        String directory = file.getAbsolutePath();

        if(directory != null) {
            StringBuilder sb = new StringBuilder(directory);
            sb.append("\\person.json");

            ObjectMapper mapper = new ObjectMapper();
            try {
                mapper.writeValue(new File(sb.toString()), object);
            } catch (IOException e) {
                e.printStackTrace();
                Alert error = AlertFactory.getErrorAlert(AlertReason.FILE_WRONG);
                error.showAndWait();
            }
            Alert alert = AlertFactory.getInformationAlert(ElementType.get(object.getClass()), OperationType.EXPORTED);
            alert.showAndWait();
        }

    }

    @Override
    public T unmarshal() {

        FileChooser chooser = new FileChooser();

        File file = chooser.showOpenDialog(null);

        T object = null;

        if(file != null) {
            ObjectMapper mapper = new ObjectMapper();
            try {
                object =  mapper.readValue(new File(file.getAbsolutePath()),  this.persistentClass);
            } catch (Exception e) {
                e.printStackTrace();
                Alert error = AlertFactory.getErrorAlert(AlertReason.FILE_WRONG);
                error.showAndWait();
            }

            Alert alert = AlertFactory.getInformationAlert(ElementType.get(object.getClass()), OperationType.IMPORTED);
            alert.showAndWait();
        }

        return object;
    }



}