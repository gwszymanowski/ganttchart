package ganttchart.serialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;

/**
 * Created by gwszymanowski on 2017-08-03.
 */
public final class JSONSerializator<T> implements SerializeStrategy<T> {

    @Override
    public void to(T object) {

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
            }

        }

    }

    @Override
    public T from() {

        FileChooser chooser = new FileChooser();

        File file = chooser.showOpenDialog(null);

        if(file != null) {
            System.out.println(file.getAbsolutePath());

        }

        return null;
    }


}